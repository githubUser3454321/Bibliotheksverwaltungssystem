package service;

import java.util.Scanner;

import Dao.MediumDao;
import Dao.UserDao;
import Dao.VerleihDao;
import service.StorageService;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VerleihServiceImpl implements VerleihService{
    private final UserDao userDao;
    private final VerleihDao verleihDao;
    private final MediumDao mediumDao;
    private final Scanner scanner;
    private final StorageService storageService;
	
	public VerleihServiceImpl(Scanner scanner, UserDao userDao, VerleihDao verleihDao, MediumDao mediumDao, StorageService storageService ) {
        this.userDao = userDao;
        this.verleihDao = verleihDao;
        this.mediumDao = mediumDao;
        this.scanner = scanner;
        this.storageService = storageService;
	}
	
	public void DisplayActiveLends() {
		var allVerleihs = verleihDao.getAllVerleihs();
		System.out.println("==========================================");
		for (var verleih : allVerleihs) {
			var isActive = verleihDao.isActiveVerleihByVerleihId(verleih.getVerleihId());
			if (!isActive) {
				System.out.println("Nummer:" + verleih.getVerleihId() + "- Kundennummer: " + verleih.getUserId() + " - Verleih von: " + verleih.getVerleihDatum() +" - Verleih bis: " + verleih.getRueckgabeDatum());	
			}
				
        }
		System.out.println("==========================================");
		System.out.println("Drücke Enter für um zurück zu kehren");
		scanner.nextLine().trim();
	}
	
	
	public void CompleteLend() {
		System.out.println("verleih nummer: ");
		var verleihIdString = scanner.nextLine().trim();
		
		if (!isStringInt(verleihIdString)) {
			System.out.println("Keine gültige eingabe!");
			CompleteLend();
			return;
		}
		var verleihId =Integer.parseInt(verleihIdString);
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
	    String currentDate = sdf.format(new Date());
	    
		verleihDao.updateRueckgabeDatumByVerleihId(verleihId, currentDate);
				
		System.out.println("Verleih wurde abgeschlossen");
	}

	public void NewVerleih(int customerNumber) {
		var mediumId = storageService.Search(true);
		
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
	    String currentDate = sdf.format(new Date());
		verleihDao.addVerleih(customerNumber,mediumId,currentDate,"00000000");
		System.out.println("Verleih erfasst!");
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
