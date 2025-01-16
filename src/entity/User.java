package entity;

public class User {
	private int userId;
	private String name;
    private String address;
    
    public User() {}
    public User(int userId, String name, String address) {
        this.userId = userId;
    	this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }
    public int getUserId() {
        return userId;
    }

    public String getAddress() {
        return address;
    }
}