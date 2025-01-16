package Dao;
import java.util.List;
import entity.Verleih;

public interface VerleihDao {
	int addVerleih(int userId, int mediaId, String verleihDatum, String rueckgabeDatum);
	List<Verleih> getAllVerleihs();
	Verleih getVerleihByVerleihId(int verleihId);
    List<Verleih> findVerleihsByUserId(int userId);
    boolean isActiveVerleihByMediumId(int mediumId);
    boolean isActiveVerleihByVerleihId(int verleihId);
    void updateRueckgabeDatumByVerleihId(int verleihId, String currentDate);
}