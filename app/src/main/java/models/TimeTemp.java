package models;



public class TimeTemp {
    int photo;
    String date;
    String temp;


    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp+"°C";
    }
}
