import java.util.HashMap;

public class PhoneBook {
    HashMap<Integer, String> phoneBook = new HashMap<>();

    {
        phoneBook.put(2221, "Anton");
    }

    public void add(int phone, String name) {
        phoneBook.put(phone, name);
    }

    public String get(String phone) {
        if (phoneBook.containsKey(phone)) {
            return phoneBook.get(phone);
        }

        return phone;
    }
}