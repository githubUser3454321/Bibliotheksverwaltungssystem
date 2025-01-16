package Dao;

import java.util.List;

import Storage.BaseCsvStorage;
import entity.Medium;
import entity.User;


public class MediumDaoImpl extends BaseCsvStorage<Medium> implements MediumDao {
	 private static final String FILE_PATH = "C:\\Users\\moberholzer\\Documents\\GitHub\\Bibliotheksverwaltungssystem\\src\\Storage\\Medium.csv";
	 
	    public MediumDaoImpl() {
	        super(FILE_PATH, Medium.class);
	        addColumn("mediaId", int.class);
	        addColumn("type", String.class);
	        addColumn("title", String.class);
	        addColumn("description", String.class);
	        initializeColumns();
	    }

	    @Override
	    protected Medium parseLine(String line) {
	        return parseLineWithDynamicColumns(line);
	    }

	    @Override
	    protected String formatObjectT(Medium medium) {
	        return super.formatObject(medium);
	    }

		@Override
		public int addMedium(String type, String title, String description) {
			
	    	int maxId = 0;
	        for (Medium medium : getAllValues()) {
	            if (medium.getMediumId() > maxId) {
	                maxId = medium.getMediumId();
	            }
	        }
	        var newMediumrNumber = maxId + 1;
	    	var newMedium = new Medium(newMediumrNumber,type, title, description);
			addValue(newMedium);
			return newMediumrNumber;
		}

		@Override
		public List<Medium> getAllMediums() {
			return getAllValues();
		}

		@Override
		public Medium getMediumByMediumId(int mediumId) {
			return getAllValues().stream()
	                .filter(medium -> medium.getMediumId() == mediumId)
	                .findFirst()
	                .orElse(null);
		}

		@Override
		public List<Medium> findMediumsByType(String type) {
			return getAllValues().stream()
	                .filter(user -> user.getType().equalsIgnoreCase(type)).toList();
		}
		
	    @Override
	    public void DeletMediumByUser(Medium mediumToDelete) {
	        for (var medium : getAllMediums()) 
	            if (medium.getMediumId() == mediumToDelete.getMediumId()) {
	            	values.remove(medium);
	            }
	    }


}
