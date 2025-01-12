package application;
import ui.ConsoleUI;

import java.util.Scanner;

import Dao.UserDao;
import Dao.UserDaoImpl;
import service.Service;
import service.ServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        Service bmiService = new ServiceImpl();
        Scanner scanner = new Scanner(System.in); // Create Scanner instance once
        ConsoleUI ui = new ConsoleUI(userDao, bmiService, scanner); // Pass it to the UI
        
        ui.displayStartUpMenu();
        
        while (true) {
            ui.displayMenu();

            if (!scanner.hasNextLine()) {
                System.out.println("Ung�ltige Eingabe. Bitte eine Zahl eingeben.");
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
                    default -> System.out.println("Ung�ltige Auswahl.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ung�ltige Eingabe. Bitte geben Sie eine g�ltige Zahl ein.");
            }
        }
    }
}
