package entity;

public class User {
    private String name;
    private int age;
    private double height;
    private double weight;

    public User() {}
    // Constructors, Getters, and Setters
    public User(String name, int age, double height, double weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }
}