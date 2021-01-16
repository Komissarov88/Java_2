package hw5;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        final int size = 10000000;
        float[] arrRef = new float[size];
        float[] arr = new float[size];
        Arrays.fill(arrRef, 1);

        Timer timer = new Timer();

        timer.start();
        singleThreadCalc(arrRef, 0);
        timer.stop();
        System.out.println("from main thread. " + timer);

        for (int i = 1; i <= 8; i++) {
            Arrays.fill(arr, 1);
            timer.start();
            multiThreadedCalc(i, arr);
            timer.stop();
            String check = Arrays.equals(arr, arrRef) ? "OK" : "arr is incorrect";
            System.out.println("threads " + i + ". " + timer + " " + check);
        }
    }

    public static float formula(float n, int i) {
        return (float) (n * Math.sin(0.2f + i / 5f) * Math.cos(0.2f + i / 5f) * Math.cos(0.4f + i / 2f));
    }

    public static void singleThreadCalc(float[] arr, int bias) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = formula(arr[i], i + bias);
        }
    }

    public static void multiThreadedCalc(int threadsNumber, float[] arr) {

        Thread[] threads = new Thread[threadsNumber];

        // Split source array
        int partLength = arr.length / threadsNumber;
        float[][] arrParts = new float[threadsNumber][partLength];
        if (arr.length % threadsNumber != 0) {
            arrParts[threadsNumber - 1] = new float[partLength + arr.length % threadsNumber];
        }
        for (int i = 0; i < threadsNumber; i++) {
            System.arraycopy(arr, i*partLength, arrParts[i], 0, arrParts[i].length);
        }

        // Calc
        for (int i = 0; i < threadsNumber; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> singleThreadCalc(arrParts[finalI], finalI * partLength));
            threads[i].start();
        }

        // Join
        for (int i = 0; i < threadsNumber; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.arraycopy(arrParts[i], 0, arr, i*partLength, arrParts[i].length);
        }
    }
}
