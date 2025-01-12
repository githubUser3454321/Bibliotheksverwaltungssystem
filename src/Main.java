import Interface.BMIService;
import Interface.UserDao;
import ui.BMIConsoleUI;

import java.util.Scanner;

import Dao.UserDaoImpl;
import service.BMIServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        BMIService bmiService = new BMIServiceImpl();
        Scanner scanner = new Scanner(System.in); // Create Scanner instance once
        BMIConsoleUI ui = new BMIConsoleUI(userDao, bmiService, scanner); // Pass it to the UI
        
        ui.displayStartUpMenu();
        
        while (true) {
            ui.displayMenu();

            if (!scanner.hasNextLine()) {
                System.out.println("Ungültige Eingabe. Bitte eine Zahl eingeben.");
                continue;
            }

            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Keine Eingabe erkannt. Bitte eine Auswahl treffen.");
                continue;
            }

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1 -> ui.promptUserData();
                    case 2 -> ui.calculateAndDisplayBMI();
                    case 3 -> ui.displayUsers();
                    case 4 -> {
                        System.out.println("Auf Wiedersehen!");
                        return;
                    }
                    default -> System.out.println("Ungültige Auswahl.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ungültige Eingabe. Bitte geben Sie eine gültige Zahl ein.");
            }
        }
    }
}
