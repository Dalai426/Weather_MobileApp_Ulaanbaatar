package weather;


import com.example.wheaterub.R;

public class IconIm {
    public int get(String code){
        if("01d".compareTo(code)==0){
            return R.drawable.img_1;
        }
        else if("02d".compareTo(code)==0){
            return R.drawable.img_2;
        }
        else if("03d".compareTo(code)==0){
            return R.drawable.img_3;
        }
        else if("04d".compareTo(code)==0){
            return R.drawable.img_4;
        }
        else if("09d".compareTo(code)==0){
            return R.drawable.img_5;
        }
        else if("10d".compareTo(code)==0){
            return R.drawable.img_6;
        }
        else if("11d".compareTo(code)==0){
            return R.drawable.img_8;
        }
        else if("13d".compareTo(code)==0){
            return R.drawable.img_9;
        }
        else if("50d".compareTo(code)==0){
            return R.drawable.img_10;
        }

        else if("01n".compareTo(code)==0){
            return R.drawable.img_11;
        }
        else if("02n".compareTo(code)==0){
            return R.drawable.img_12;
        }
        else if("03n".compareTo(code)==0){
            return R.drawable.img_13;
        }
        else if("04n".compareTo(code)==0){
            return R.drawable.img_14;
        }
        else if("09n".compareTo(code)==0){
            return R.drawable.img_15;
        }
        else if("10n".compareTo(code)==0){
            return R.drawable.img_16;
        }
        else if("11n".compareTo(code)==0){
            return R.drawable.img_17;
        }
        else if("13n".compareTo(code)==0){
            return R.drawable.img_18;
        }
        else {
            return R.drawable.img_19;
        }
    }
}
