package entity;

public class Verleih {
	private int verleihId;
	private int userId;
	private int mediaId;
	private String verleihDatum;
    private String rueckgabeDatum;
    
    public Verleih() {}
    public Verleih(int verleihId, int userId, int mediaId, String verleihDatum, String rueckgabeDatum) {
        this.verleihId = verleihId;
        this.userId = userId;
        this.mediaId = mediaId;
        this.verleihDatum = verleihDatum;
        this.rueckgabeDatum = rueckgabeDatum;
    }

    public int getVerleihId() {
        return verleihId;
    }
    
    public int getUserId() {
        return userId;
    }

    public int getMediaId() {
        return mediaId;
    }

    public String getVerleihDatum() {
        return verleihDatum;
    }

    public String getRueckgabeDatum() {
        return rueckgabeDatum;
    }

}