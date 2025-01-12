package Dao;
import java.util.List;
import entity.Medium;

public interface MediumDao {
	void addMedium(Medium medium);
	List<Medium> getAllMediums();
    Medium getMediumByMediumId(int mediumId);
    List<Medium> findMediumsByType(String type);
}



