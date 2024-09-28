
public class Main {
    public static void main(String[] args) {

        // ~~ЗАДАНИЕ №2~~
        Employee[] empArr = new Employee[5];

            empArr[0] = new Employee("Lenin Vladimir Ilyich", "CEO KPSS", "len1n@kpss.su", "88005553535", 30000, 53);
            empArr[1] = new Employee("Stalin Iosif Vissarionovich", "General Secretary", "koba1878@kpss.su", "88005554545", 25000, 74);
            empArr[2] = new Employee("Hruschev Nikita Sergeevich", "Governor", "kuzkinamatb@kpss.su", "88005556565", 14000, 77);
            empArr[3] = new Employee("Brezhnev Leonid Ilyich", "Head of Prezidium", "sweetlips@kpss.su", "88005557575", 20000, 75);
            empArr[4] = new Employee("Andropov Yury Vladimirovich", "Head of KGB", "gulagtours@kpss.su", "88005558585", 18000, 69);

        for (int i = 0; i < empArr.length; i++) {
            empArr[i].info();
        }

        // ~~ЗАДАНИЕ №3~~
        Park.Attractions kacheli = new Park.Attractions("Качели", "24/7", 500);

        System.out.println("\nИнформация об аттракционе:");
        System.out.println("Название: " + kacheli.getName());
        System.out.println("Часы работы: " + kacheli.getWorkingHours());
        System.out.println("Цена: " + kacheli.getPrice() + " казахских долларов");
    }
}