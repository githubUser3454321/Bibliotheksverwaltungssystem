package Dao;
import java.util.List;
import entity.Verleih;

public interface VerleihDao {
	void addVerleih(Verleih verleih);
	List<Verleih> getAllVerleihs();
	Verleih getVerleihByVerleihId(int verleihId);
    List<Verleih> findVerleihsByUserId(int userId);
}