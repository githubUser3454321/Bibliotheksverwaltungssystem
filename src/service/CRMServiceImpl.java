package service;
import java.util.Scanner;

import Dao.MediumDao;
import Dao.UserDao;
import Dao.VerleihDao;

public class CRMServiceImpl implements CRMService {
    
    private final UserDao userDao;
    private final VerleihDao verleihDao;
    private final MediumDao mediumDao;
    private final Scanner scanner;
    private final StorageService storageService;
    private final VerleihService verleiheService;
	
	public CRMServiceImpl(Scanner scanner, UserDao userDao, VerleihDao verleihDao, MediumDao mediumDao, StorageService storageService, VerleihService verleiheService) {
        this.userDao = userDao;
        this.verleihDao = verleihDao;
        this.mediumDao = mediumDao;
        this.scanner = scanner;
        this.storageService = storageService;
        this.verleiheService = verleiheService;
	}
	@Override
	public void Search() {
		System.out.println("Name: ");
		var searchTerm = scanner.nextLine().trim();
		
		System.out.println("====================================");
		var users = userDao.findUsersByName(searchTerm);
		for (var user : users) {
            System.out.println("Kundennummer:" + user.getUserId() + "- Name: " + user.getName() + " - Adresse: " + user.getAddress());
        }
		System.out.println("====================================");
		
		System.out.println("Kontakt selektieren, Kundenummer: ");
		var customerNumber = scanner.nextLine().trim();
		if (isStringInt(customerNumber)) {
			EditContact(Integer.parseInt(customerNumber));
		}
		else {
			System.out.println("Zurück zu CRM");
		}
		
		return;
	}
	
	private void EditContact(int customerNumber) {
		var user = userDao.getUserByUserId(customerNumber);
		System.out.println("geöffneter kontakt: " + user.getName());
		System.out.println("Aktionen: ");
		System.out.println("1. Verleihe ansehen");
		System.out.println("2. Neuer verleih");
		System.out.println("3. zurück zu CRM");
		
		var selectedOptionString = scanner.nextLine().trim();

		var selectedOption = Integer.parseInt(selectedOptionString);
        try {
            switch (selectedOption) {
                case 1 -> {
                	for (var verleih : verleihDao.findVerleihsByUserId(customerNumber)) {
                		var medium = mediumDao.getMediumByMediumId(verleih.getMediaId());
                        System.out.println("VerleihStart:" + verleih.getVerleihDatum() + "- Rückgabedatum: " + verleih.getRueckgabeDatum() + " - Medium " + medium.getTitle());
                    }
                }
                case 2 -> verleiheService.NewVerleih(customerNumber);
                case 3 -> {
                    return;
                }
                default -> {
                	System.out.println("Ungültige Auswahl.");
                	EditContact(customerNumber);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Eingabe. Bitte geben Sie eine gültige Zahl ein.");
            EditContact(customerNumber);
        }
		
	}

	private boolean isStringInt(String s)
	{
		if (s == "") 
			return false;
	    try
	    {
	        Integer.parseInt(s);
	        return true;
	    } catch (NumberFormatException ex)
	    {
	        return false;
	    }
	}
	public void DeleteContact() {
		System.out.println("Kundenummer:");
		var selectedOptionString = scanner.nextLine().trim();
		
		if (isStringInt(selectedOptionString)) {
			
			var user = userDao.getUserByUserId(Integer.parseInt(selectedOptionString));
			System.out.println("geöffneter kontakt: " + user.getName());
			System.out.println("Möchten Sie den Kontakt wirklich löschen, Bestätigen Sie mit 'JA':");
			var approvement = scanner.nextLine().trim();
			if (approvement.equals("JA"))
				return;
			userDao.DeletUserByUser(user);
		}
		else {
			System.out.println("Zurück zur auswahl...");
		}
	}

	public void AddContact() {
		System.out.println("Neuen Kontakt erfassen:");
		System.out.println("Name:");
		var selectedName = scanner.nextLine().trim();
		System.out.println("Adresse:");
		var selectedAddress = scanner.nextLine().trim();
		
		if (selectedName == "" || selectedAddress == "") {
			System.out.println("Leere eingabe erkannt, neustart!:");
			AddContact();
		}
		
		var customerNumber = userDao.addUser(selectedName, selectedAddress);
		EditContact(customerNumber);
		
		
	}
	
}
