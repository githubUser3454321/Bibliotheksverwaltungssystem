package application;
import ui.ConsoleUI;

import java.util.Scanner;

import Dao.UserDao;
import Dao.UserDaoImpl;
import Dao.VerleihDao;
import Dao.VerleihDaoImpl;
import Dao.MediumDao;
import Dao.MediumDaoImpl;
import service.CRMService;
import service.CRMServiceImpl;
import service.StorageService;
import service.StorageServiceImpl;


public class Main {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in); // Create Scanner instance once, its ugly, but it works
    	UserDao userDao = new UserDaoImpl();
        VerleihDao verleihDao = new VerleihDaoImpl();
        MediumDao mediumDao = new MediumDaoImpl();

        CRMService crmService = new CRMServiceImpl(scanner, userDao, verleihDao, mediumDao);
        StorageService storageService = new StorageServiceImpl(scanner, userDao, verleihDao, mediumDao);
        
        ConsoleUI ui = new ConsoleUI(userDao, verleihDao, mediumDao, crmService, storageService, scanner);
        
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
                    case 1 -> ui.promptCRM();
                    case 2 -> ui.promtStorage();
                    case 3 -> ui.calculateAndDisplayBMI();
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
