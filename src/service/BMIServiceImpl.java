package service;
import Interface.BMIService;
import entity.User;

public class BMIServiceImpl implements BMIService {
    @Override
    public double calculateBMI(User user) {
        return user.getWeight() / (user.getHeight() * user.getHeight());
    }
    
    @Override
    public double calculateBMI(double height, double weight) {
        return height / (weight * weight);
    }


    @Override
    public String interpretBMI(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi <= 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}
