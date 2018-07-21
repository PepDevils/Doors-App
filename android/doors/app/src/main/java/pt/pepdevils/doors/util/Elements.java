package pt.pepdevils.doors.util;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by pepdevils on 12/07/16.
 */
public class Elements implements Parcelable  {

    //          http://www.survivingwithandroid.com/2015/05/android-parcelable-tutorial-list-class-2.html


    /*public static final String IMAGE_TYPE_PNG = ".png";
    public static final String IMAGE_PNG_40x40 = "-40x40.png";
    public static final String IMAGE_PNG_100x100 = "-100x100.png";
    public static final String IMAGE_PNG_280x280 = "-280x280.png";
    public static final String IMAGE_PNG_370x370 = "-370x370.png";
    public static final String IMAGE_PNG_1050x1050 = "-1050x1050.png";*/

    //public static final String IMAGE_JPG_40x40 = "-40x40.jpg";
    //public static final String IMAGE_JPG_100x100 = "-100x100.jpg";

/*    public static final String IMAGE_TYPE_JPG = ".jpg";
    public static final String IMAGE_JPG_280x280 = "-280x280.jpg";
    public static final String IMAGE_JPG_370x370 = "-370x370.jpg";
    public static final String IMAGE_JPG_1050x1050 = "-1050x1050.jpg";*/


    private String id_door;
    private String line;
    private String model;
    private String Image_Door;

    private ArrayList<String> All_Image_Door;

/*
    private ArrayList<String> LineFashion;
    private ArrayList<String> New_line_series;
    private ArrayList<String> Paineis_Estampados;
    private ArrayList<String> Paineis_Retiline;
    private ArrayList<String> Paineis_para_Portas;
*/

    public static int productColor;


    //CONSTRUTORES
    public Elements(){

    }


    public Elements(String id_door, String line, String model, String Image_Door) {
        this.id_door = id_door;
        this.line = line;
        this.model = model;
        this.Image_Door = Image_Door;
    }

    //GETTERS AND SETTERS encapsulamento
    public ArrayList<String> getAll_Image_Door() {
        return All_Image_Door;
    }

    public void setAll_Image_Door(ArrayList<String> all_Image_Door) {
        All_Image_Door = all_Image_Door;
    }



    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImage_Door() {
        return Image_Door;
    }

    public void setImage_Door(String image_Door) {
        Image_Door = image_Door;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id_door);
        dest.writeString(line);
        dest.writeString(model);
        dest.writeString(Image_Door);

    }


    public Elements(Parcel in){
        id_door = in.readString();
        line = in.readString();
        model = in.readString();
        Image_Door = in.readString();
    }

    public static final Parcelable.Creator<Elements>
            CREATOR = new Parcelable.Creator<Elements>() {

        public Elements createFromParcel(Parcel in) {
            return new Elements(in);
        }

        public Elements[] newArray(int size) {
            return new Elements[size];
        }
    };
}
