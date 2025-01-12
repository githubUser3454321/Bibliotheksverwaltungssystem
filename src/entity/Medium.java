package entity;

public class Medium {
    private int mediaId;
    private String type;
    private String title;
    private String description;

    
    public Medium() {}
    public Medium(int mediaId, String type, String title, String description) {
        this.mediaId = mediaId;
    	this.type = type;
        this.title = title;
        this.description = description;
    }
    
    public int getMediumId() {
        return mediaId;
    }


    public String getType() {
        return type;
    }


    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }

}
