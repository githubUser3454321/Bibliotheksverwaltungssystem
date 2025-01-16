package Dao;
import Storage.BaseCsvStorage;
import entity.Medium;
import entity.User;
import entity.Verleih;
import java.util.List;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class VerleihDaoImpl extends BaseCsvStorage<Verleih> implements VerleihDao {

    private static final String FILE_PATH = "C:\\Users\\moberholzer\\Documents\\GitHub\\Bibliotheksverwaltungssystem\\src\\Storage\\Verleih.csv";

    public VerleihDaoImpl() {
        super(FILE_PATH, Verleih.class);
        addColumn("verleihId", int.class);
        addColumn("userId", int.class);
        addColumn("mediaId", int.class);
        addColumn("verleihDatum", String.class);
        addColumn("rueckgabeDatum", String.class);
        initializeColumns();
    }

    @Override
    protected Verleih parseLine(String line) {
        return parseLineWithDynamicColumns(line);
    }

    @Override
    protected String formatObjectT(Verleih verleih) {
        return super.formatObject(verleih);
    }

    @Override
    public int addVerleih(int userId, int mediaId, String verleihDatum, String rueckgabeDatum) {
        
    	int maxId = 0;
        for (var verleih : getAllValues()) {
            if (verleih.getVerleihId() > maxId) {
                maxId = verleih.getVerleihId();
            }
        }
        var newVerleihId = maxId + 1;
    	
    	var newVerleih = new Verleih(newVerleihId, userId, mediaId, verleihDatum, rueckgabeDatum);
    	addValue(newVerleih);
    	return newVerleihId;
    }

    @Override
    public List<Verleih> getAllVerleihs() {
        return getAllValues();
    }

    @Override
    public Verleih getVerleihByVerleihId(int verleihId) {
        return getAllValues().stream()
                .filter(verleih -> verleih.getVerleihId() == verleihId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Verleih> findVerleihsByUserId(int userId) {
        return getAllValues().stream()
                .filter(verleih -> verleih.getUserId() == userId).toList();
    }
    
    
    public boolean isActiveVerleihByVerleihId(int verleihId) {
    	 SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
         String currentDateStr = sdf.format(new Date());
         LocalDate currentDate = LocalDate.parse(currentDateStr, DateTimeFormatter.ofPattern("ddMMyyyy"));

         var medium = getAllValues().stream()
             .filter(verleih -> verleih.getVerleihId() == verleihId)
             .filter(verleih -> {
            	 if(verleih.getRueckgabeDatum().equals("00000000"))
            		 return true;
            	 return false;
             })
             .filter(verleih -> {
                 LocalDate verleihDatum = LocalDate.parse(
                     verleih.getVerleihDatum(),
                     DateTimeFormatter.ofPattern("ddMMyyyy")
                 );
                 // verleihDatum >= currentDate
                 return verleihDatum.compareTo(currentDate) >= 0;
             })
             .findFirst()
             .orElse(null);

         return (medium == null);
    }
    
    
    public boolean isActiveVerleihByMediumId(int mediumId) {

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        String currentDateStr = sdf.format(new Date());
        LocalDate currentDate = LocalDate.parse(currentDateStr, DateTimeFormatter.ofPattern("ddMMyyyy"));

        var medium = getAllValues().stream()
            .filter(verleih -> verleih.getMediaId() == mediumId)
            .filter(verleih -> {
           	 	if(verleih.getRueckgabeDatum().equals("00000000"))
           		 	return true;
        	 
           	 	return false;
            })
            .filter(verleih -> {
                LocalDate verleihDatum = LocalDate.parse(
                    verleih.getVerleihDatum(),
                    DateTimeFormatter.ofPattern("ddMMyyyy")
                );
                // verleihDatum >= currentDate
                return verleihDatum.compareTo(currentDate) >= 0;
            })
            .findFirst()
            .orElse(null);

        return (medium == null);
    }
    
    public void updateRueckgabeDatumByVerleihId(int verleihId, String rueckgabeDatumDate) {
    	var verleih = getVerleihByVerleihId(verleihId);
    	var userId = verleih.getUserId();
    	var mediaId = verleih.getMediaId();
    	var verleihDatum = verleih.getVerleihDatum();
    	DeletVerleihByVerleihId(verleihId);
    	var newVerleih = new Verleih(verleihId,userId,mediaId,verleihDatum,rueckgabeDatumDate);
    	addValue(newVerleih);
    }
    
    public void DeletVerleihByVerleihId(int verleihToDeleteId) {
        for (var verleih : getAllVerleihs()) 
            if (verleih.getVerleihId() == verleihToDeleteId) {
            	values.remove(verleih);
            }
    }
}
