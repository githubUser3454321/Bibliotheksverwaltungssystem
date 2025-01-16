package Dao;

import Storage.BaseCsvStorage;

import entity.Verleih;

import java.util.List;

public class VerleihDaoImpl extends BaseCsvStorage<Verleih> implements VerleihDao {

    private static final String FILE_PATH = "C:\\Users\\moberholzer\\Documents\\GitHub\\Bibliotheksverwaltungssystem\\src\\Storage\\Verleih.csv";

    public VerleihDaoImpl() {
        super(FILE_PATH, Verleih.class);
        addColumn("userId", int.class);
        addColumn("name", String.class);
        addColumn("address", String.class);
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
    public void addVerleih(Verleih verleih) {
        addValue(verleih);
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
    
    boolean isActiveVerleihByMediumId(int mediumId) {
    	
    	return true;
    }
}
