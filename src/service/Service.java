package service;
import entity.User;

public interface Service {
    double calculateBMI(User user);
    double calculateBMI(double height, double weight);
    String interpretBMI(double bmi);
}