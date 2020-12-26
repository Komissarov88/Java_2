package hw3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {

    private final Map<String, List<Long>> book;

    public PhoneBook() {
        book = new HashMap<>();
    }

    public void addNumber(String name, long phone) {
        book.putIfAbsent(name, new ArrayList<>());
        book.get(name).add(phone);
    }

    public Long[] getPhonesFor(String name) {
        if (!book.containsKey(name)) {
            System.out.println("No phones for that person");
            return new Long[0];
        }
        return book.get(name).toArray(new Long[0]);
    }
}
