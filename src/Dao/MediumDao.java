package Dao;
import java.util.List;
import entity.Medium;
import entity.User;

public interface MediumDao {
	int addMedium(String mediumType, String mediaTitle, String mediaDescription);
	List<Medium> getAllMediums();
    Medium getMediumByMediumId(int mediumId);
    List<Medium> findMediumsByType(String type);
    void DeletMediumByUser(Medium user);
}



