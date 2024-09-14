import java.util.*;

import static java.util.Collections.frequency;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> spisokPokupok = new ArrayList<>();
        spisokPokupok.add("Хлеб");
        spisokPokupok.add("Масло");
        spisokPokupok.add("Макароны");
        spisokPokupok.add("Молоко");
        spisokPokupok.add("Сырок");
        spisokPokupok.add("Сырок");
        spisokPokupok.add("Сыр");
        spisokPokupok.add("Мороженое");
        spisokPokupok.add("Мороженое");
        spisokPokupok.add("Курица");
        spisokPokupok.add("Колбаса");
        spisokPokupok.add("Пиво"); //больше не смог придумать продуктов
        spisokPokupok.add("Пиво");
        spisokPokupok.add("Пиво");
        spisokPokupok.add("Пиво");

        HashSet<String> unique = new HashSet<>(spisokPokupok);
        System.out.println("Список продуктов и их количество:");
        for (String i : unique) {
            int count = Collections.frequency(spisokPokupok, i);
            System.out.println(i + " " + count);
        }

    }
}