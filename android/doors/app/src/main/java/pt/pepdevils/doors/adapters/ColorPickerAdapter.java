package pt.pepdevils.doors.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import java.util.ArrayList;

import pt.pepdevils.doors.fragments.MenuSubColors_Fragment;

/**
 * Created by pepdevils on 09/01/2017.
 */

public class ColorPickerAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> arrayList;
    private int colorGridColumnWidth = 27; //50
    private float scale = 1;

    public ColorPickerAdapter(Context c, ArrayList<String> arrayList, float scale) {
        this.context = c;
        this.arrayList = arrayList;
        this.scale = scale;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        // can we reuse a view?
        if (convertView == null) {
            imageView = new ImageView(context);
            // set the width of each color square

            //colorGridColumnWidth = (int) (colorGridColumnWidth * scale);

            if(scale == 3.0){
                colorGridColumnWidth = 80;
            }else if (scale == 2.0) {
                colorGridColumnWidth = 50;
            }else if (scale == 1.5) {
                colorGridColumnWidth = 80;
            }else if (scale == 1) {
                colorGridColumnWidth = 27;
            }else if (scale == 0.75) {
                colorGridColumnWidth = 20;
            }else {
                colorGridColumnWidth = ((int)(26.6666667 * scale));
            }

            imageView.setLayoutParams(new GridView.LayoutParams(colorGridColumnWidth, colorGridColumnWidth));

        } else {
            imageView = (ImageView) convertView;
        }


        int cor = MenuSubColors_Fragment.ReturnColorFromPosition(position);

        imageView.setBackgroundColor(cor);
        imageView.setId(position);

        return imageView;
    }

    public int getCount() {
        return arrayList.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }


}
