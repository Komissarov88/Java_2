package hw3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        String[] words = { "Создать", "массив", "набором", "слов", "слов", "должны", "встречаться", "повторяющиеся",
                "Найти", "вывести", "список", "уникальных", "слов", "из", "которых", "Найти", "массив", "дубликаты",
                "Найти", "Создать", "Создать", "сколько", "раз", "Создать", "каждое", "Создать"};
        Map<String, Integer> uniqueWords = getWordsWithFrequencyFromArray(words);
        for (Map.Entry<String, Integer> entry: uniqueWords.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        PhoneBook book = new PhoneBook();
        book.addNumber("Black", 89991111111L);
        book.addNumber("Black", 89991111112L);
        book.addNumber("White", 89991111113L);
        book.addNumber("Johnson", 89991111114L);
        book.addNumber("Johnson", 89991111115L);
        book.addNumber("Patrik", 89991111116L);
        book.addNumber("Pink", 89991111117L);

        System.out.println(Arrays.toString(book.getPhonesFor("Johnson")));
        System.out.println(Arrays.toString(book.getPhonesFor("White")));
        System.out.println(Arrays.toString(book.getPhonesFor("Who")));
    }

    public static Map<String, Integer> getWordsWithFrequencyFromArray(String[] array) {
        Map<String, Integer> map = new HashMap<>();
        for (String s: array) {
            int value = map.getOrDefault(s, 0);
            map.put(s, value + 1);
        }
        return map;
    }
}
