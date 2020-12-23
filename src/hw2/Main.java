package hw2;

public class Main {
    public static void main(String[] args) {
        String[][] array1 = {
                {"1", "2" , "3", "4"},
                {"5", "0" , "7", "8"},
                {"9", "1" , "2"},
                {"4", "5" , "6", "7"}
        };

        String[][] array2 = {
                {"1", "2" , "3", "4"},
                {"5", "O" , "7", "8"},
                {"9", "1" , "2", "3"},
                {"4", "5" , "6", "7"}
        };

        String[][] array3 = {
                {"1", "2" , "3", "4"},
                {"5", "0" , "7", "8"},
                {"9", "1" , "2", "3"},
                {"4", "5" , "6", "7"}
        };

        printSumOfArray(array1);
        printSumOfArray(array2);
        printSumOfArray(array3);
        printSumOfArray(null);
    }

    public static void printSumOfArray(String[][] array) {
        try {
            System.out.println("Sum of 4x4 array elements is " + sumOfArray(array));

        } catch (ArraySizeException e) {
            System.out.println(e.getMessage());

        } catch (ArrayDataException e) {
            System.out.println("Wrong data in [" + e.getRow() + ", " + e.getCol() + "]");
            System.out.println("data: " + e.getMessage());
        }
    }

    public static int sumOfArray(String[][] array) {
        if (array == null || array.length != 4) {
            throw new ArraySizeException("Array must be 4 by 4 elements");
        }
        for (int i = 0; i < 4; i++) {
            if (array[i].length != 4) {
                throw new ArraySizeException("Array must be 4 by 4 elements");
            }
        }

        int sum = 0;
        int i = 0;
        int j = 0;
        try {
            for (i = 0; i < 4; i++) {
                for (j = 0; j < 4; j++) {
                    sum += Integer.parseInt(array[i][j]);
                }
            }
        } catch (NumberFormatException e) {
            throw new ArrayDataException(array[i][j], i, j);
        }
        return sum;
    }
}
