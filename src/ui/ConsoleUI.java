package ui;
import java.util.Scanner;

import Dao.MediumDao;
import Dao.MediumDaoImpl;
import Dao.UserDao;
import Dao.VerleihDao;
import Dao.VerleihDaoImpl;
import entity.User;
import service.CRMService;
import service.StorageService;
import service.VerleihService;

public class ConsoleUI {
    private final CRMService crmService;
    private final Scanner scanner;
    private final StorageService storageService;
    private final VerleihService verleihService;
    
    public ConsoleUI(CRMService crmService, StorageService storageService, VerleihService verleihService, Scanner scanner) {
        this.crmService = crmService;
        this.scanner = scanner;
        this.storageService = storageService;
        this.verleihService = verleihService;

    }

	public void displayStartUpMenu() {
    	System.out.println("Starting up...");
        System.out.println("============================");
    }
    
    public void displayMenu() {
    	System.out.println("");
    	System.out.println("");
    	System.out.println("Hauptmenü:");
        System.out.println("1. CRM");
        System.out.println("2. Lager");
        System.out.println("3. Verleihe");
        System.out.println("4. Beenden");
    }

    public void promptCRM() {
    	
        while (true) {
        	System.out.println("CRM:");
            System.out.println("1. Kontakt suche");
            System.out.println("2. Kontakt erfassen");
            System.out.println("3. Kontakt Löschen (Dsgvo - Datenschutz)");
            System.out.println("4. Zurück / Hauptmenü");

            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Keine Eingabe erkannt. Bitte eine Auswahl treffen.");
                continue;
            }

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1 -> crmService.Search();
                    case 2 -> crmService.AddContact();
                    case 3 -> crmService.DeleteContact();
                    case 4 -> {
                        return;
                    }
                    default -> System.out.println("Ungültige Auswahl.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ungültige Eingabe. Bitte geben Sie eine gültige Zahl ein.");
            }
        }
    }

    public void promtStorage() {
    	while (true) {
        	System.out.println("Lager:");
            System.out.println("1. Medium suche");
            System.out.println("2. Medium erfassen");
            System.out.println("3. Medium Löschen");
            System.out.println("4. Zurück / Hauptmenü");

            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Keine Eingabe erkannt. Bitte eine Auswahl treffen.");
                continue;
            }

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1 -> storageService.Search(false);
                    case 2 -> storageService.AddMedium();
                    case 3 -> storageService.DeleteMedium();
                    case 4 -> {
                        return;
                    }
                    default -> System.out.println("Ungültige Auswahl.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ungültige Eingabe. Bitte geben Sie eine gültige Zahl ein.");
            }
        }

    }

    public void promtLend() {
    	while (true) {
        	System.out.println("Verleih:");
            System.out.println("1. Aktive Verleihe Anzeigen");
            System.out.println("2. Verleih abschliessen");
            System.out.println("3. Zurück / Hauptmenü");

            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Keine Eingabe erkannt. Bitte eine Auswahl treffen.");
                continue;
            }

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1 -> verleihService.DisplayActiveLends();
                    case 2 -> verleihService.CompleteLend();
                    case 3 -> {
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
