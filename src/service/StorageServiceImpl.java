package service;

import java.util.Scanner;

import Dao.MediumDao;
import Dao.UserDao;
import Dao.VerleihDao;

public class StorageServiceImpl implements StorageService {
    private final UserDao userDao;
    private final VerleihDao verleihDao;
    private final MediumDao mediumDao;
    private final Scanner scanner;
	
	public StorageServiceImpl(Scanner scanner, UserDao userDao, VerleihDao verleihDao, MediumDao mediumDao) {
        this.userDao = userDao;
        this.verleihDao = verleihDao;
        this.mediumDao = mediumDao;
        this.scanner = scanner;
	}

	@Override
	public int Search(boolean disaplyOnlyOpenMediums) {
		System.out.println("Type: ");
		var searchTerm = scanner.nextLine().trim();
		System.out.println("====================================");
		
		var mediums = mediumDao.findMediumsByType(searchTerm);
		for (var medium : mediums) {
			
			var isActive = verleihDao.isActiveVerleihByMediumId(medium.getMediumId());
			if (!disaplyOnlyOpenMediums || isActive) { // acticve means it has been set to a dude
				System.out.println("Nummer:" + medium.getMediumId() + "- Title: " + medium.getTitle() + " - Beschreibung: " + medium.getDescription() + (isActive? " - nicht vergeben" : " - vergeben"));	
			}
        }
		System.out.println("====================================");
		
		System.out.println("Medium selektieren, Kundenummer: ");
		var mediumNumber = scanner.nextLine().trim();
		if (isStringInt(mediumNumber)) {
			var mediumId = Integer.parseInt(mediumNumber);
			var medium = mediumDao.getMediumByMediumId(mediumId);
			if (medium != null 
					&& medium.getMediumId() == mediumId)
				return mediumId;
			System.out.println("Keine gültige eingabe!");
			return Search(disaplyOnlyOpenMediums);
		}
		else {
			System.out.println("Keine gültige eingabe!");
			return Search(disaplyOnlyOpenMediums);
		}
	}

	@Override
	public void AddMedium() {
		System.out.println("Type: ");
		var mediaType = scanner.nextLine().trim();
		
		System.out.println("Title: ");
		var mediaTitle = scanner.nextLine().trim();
		
		System.out.println("Beschreibung: ");
		var mediaDescription = scanner.nextLine().trim();
		
		System.out.println("====================================");
		
		var mediumId = mediumDao.addMedium(mediaType, mediaTitle, mediaDescription);
		System.out.println("Mediumnummer: " + mediumId);
		
	}

	@Override
	public void DeleteMedium() {
		System.out.println("Mediumnummer: ");
		var mediumId = scanner.nextLine().trim();

		if (!isStringInt(mediumId)) {
			System.out.println("Keine gültige eingabe!");
			return;
		}
		
		System.out.println("Bestätige die Löschung mittels 'JA': ");
		
		var approvement = scanner.nextLine().trim();
		if (approvement.equals("JA")) {
			mediumDao.DeletMediumByUser(mediumDao.getMediumByMediumId(Integer.parseInt(mediumId)));
		}
		System.out.println("Abbrechen...");
		
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
}
