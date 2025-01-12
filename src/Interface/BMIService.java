package Interface;
import entity.User;

public interface BMIService {
    double calculateBMI(User user);
    double calculateBMI(double height, double weight);
    String interpretBMI(double bmi);
}