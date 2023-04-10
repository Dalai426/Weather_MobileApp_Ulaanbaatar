package models;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class DayTemp {
    String date;

    String min_temp;
    String max_temp;
    String humid;
    String speed;
    int img;

    public String getDate() {
        return date;
    }

    public void setDate(LocalDateTime date, int i) {
        LocalDateTime local= null;


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O && i==1) {
            DayOfWeek dow = date.getDayOfWeek();
            this.date =dow.toString().substring(0,3);
        }else{
            this.date="Today";
        }
    }



    public String getHumid() {
        return humid;
    }

    public void setHumid(String humid) {
        this.humid = humid;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(String min_temp) {
        this.min_temp = min_temp+"°C";
    }

    public String getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(String max_temp) {
        this.max_temp = max_temp+"°C";
    }
}
