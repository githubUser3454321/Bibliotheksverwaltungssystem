package ui;
import java.util.Scanner;

import Dao.UserDao;
import entity.User;
import service.Service;

public class ConsoleUI {
    private final UserDao userDao;
    private final Service bmiService;
    private final Scanner scanner;

    public ConsoleUI(UserDao userDao, Service bmiService, Scanner scanner) {
        this.userDao = userDao;
        this.bmiService = bmiService;
        this.scanner = scanner;
    }

    public void displayStartUpMenu() {
    	System.out.println("Willkommen beim BMI-Rechner!");
        System.out.println("============================");
    }
    
    public void displayMenu() {
    	System.out.println("");
    	System.out.println("");
    	System.out.println("Bitte w�hlen Sie eine Option:");
        System.out.println("1. Daten eingeben (Name, Gewicht, Gr�sse)");
        System.out.println("2. BMI berechnen");
        System.out.println("3. Daten anzeigen");
        System.out.println("4. Beenden");
    }

    public void promptUserData() {
        System.out.println("Geben Sie Ihren Namen ein:");
        String name = scanner.nextLine(); // Use the passed Scanner

        System.out.println("Geben Sie Ihr Alter ein:");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.println("Geben Sie Ihre Gr�sse in Metern ein:");
        double height = Double.parseDouble(scanner.nextLine());

        System.out.println("Geben Sie Ihr Gewicht in Kilogramm ein:");
        double weight = Double.parseDouble(scanner.nextLine());

        // Save data to the user DAO
        User user = new User(name, age, height, weight);
        userDao.addUser(user);

        System.out.println("Daten wurden erfolgreich gespeichert.");
    }

    public void displayUsers() {
        for (User user : userDao.getAllUsers()) {
            System.out.println(user.getName() + " - " + user.getAge() + " Jahre - Gr�sse: " + user.getHeight() + "m - Gewicht: " + user.getWeight() + "kg");
        }
    }

    public void calculateAndDisplayBMI() {
        if (userDao.getAllUsers().isEmpty()) { // Check if user data exists
            System.out.println("Es sind keine Benutzerdaten verf�gbar. Bitte geben Sie zuerst Daten ein.");
            return;
        }

        
        System.out.print("Geben Sie den Namen des Benutzers ein: ");
        String name = scanner.nextLine();

        var user = userDao.findUserByName(name); // Retrieve user by name
        if (user == null) {
            System.out.println("Benutzer nicht gefunden. Bitte geben Sie einen g�ltigen Namen ein.");
        } else {
            double bmi = bmiService.calculateBMI(user.getWeight(), user.getHeight());
            System.out.printf("Der BMI von %s betr�gt: %.2f (%s) %n", user.getName(), bmi, bmiService.interpretBMI(bmi));
        }
    }
}
