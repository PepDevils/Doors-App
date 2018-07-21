package pt.pepdevils.doors.fragments;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import pt.pepdevils.doors.R;
import pt.pepdevils.doors.activities.MainActivity;
import pt.pepdevils.doors.adapters.ColorPickerAdapter;
import pt.pepdevils.doors.util.Helper;
import pt.pepdevils.doors.util.RALColors;
import pt.pepdevils.doors.util.Elements;

import com.rtugeek.android.colorseekbar.ColorSeekBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by pepdevils on 05/07/16.
 */
public class MenuSubColors_Fragment extends Fragment {

    private static String MODE = "2"; // 2-cores em gride ou 1-cor em lista


    private View view;
    private ImageView drag_image;
    private float alphaFactor = 0.5f;
    private TextView tv_ral;

    //private ImageView drag_item_image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //COLOR PICKERS
        //                      https://android-arsenal.com/details/1/3143
        //                      https://android-arsenal.com/details/1/1882

        drag_image = (ImageView) getActivity().findViewById(R.id.drag_image);
       // tv_ral = (TextView) getActivity().findViewById(R.id.ral_id);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        if(MODE.equalsIgnoreCase("2")){
            view = inflater.inflate(R.layout.menu_sub_colors_fragment_gride, container, false);
            tv_ral = (TextView) view.findViewById(R.id.ral_id);
            ColorGridePicker(view);

        }else{
            view = inflater.inflate(R.layout.menu_sub_colors_fragment, container, false);
            tv_ral = (TextView) view.findViewById(R.id.ral_id);
            ColorPicker();
        }


        return view;
    }



    private void ColorPicker(){
        //                      https://android-arsenal.com/details/1/3118

        //calcular largura do dispositivo
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width_hardware = (int) (size.x * 1.01);


        //opções do colorpickup
        ColorSeekBar colorSeekBar = (ColorSeekBar) view.findViewById(R.id.colorSlider);
        colorSeekBar.setMinimumWidth(width_hardware);
        colorSeekBar.setMaxValue((MainActivity.array_colors_hex.length - 1));
        colorSeekBar.setColors(MainActivity.array_colors_hex);
        colorSeekBar.setColorBarValue(MainActivity.myBarColorValue); //0 - maxValue 100
        colorSeekBar.setAlphaBarValue(225); //0-255     180   85    95
        colorSeekBar.setShowAlphaBar(false);
        colorSeekBar.setBarHeight(70); //5dpi


        MainActivity.COLORS_RAL = MainActivity.keys_store;

        colorSeekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int colorBarValue, int alphaBarValue, int color) {

                MainActivity.myBarColorValue = colorBarValue;
                Elements.productColor = Helper.adjustAlpha(color,alphaFactor);
                drag_image.setColorFilter(Elements.productColor);
                String ral_choose = ReturnRAlfromColor(color);

                if(ral_choose == null){
                    tv_ral.setText("");
                }else{
                    tv_ral.setText(ral_choose);
                }

                MainActivity.RAL_CHOOSE = ral_choose;

            }
        });

        if(tv_ral != null){
            tv_ral.setText(MainActivity.RAL_CHOOSE);
        }

    }

    private String ReturnRAlfromColor(int color) {
        String ral = null;

        for (int i = 0; i < MainActivity.COLORS_RAL.size(); i++) {
            try {
                JSONObject aux = (JSONObject) MainActivity.COLORS_JSON.get(MainActivity.COLORS_RAL.get(i));
                String coloers = (String) aux.get("android_int_color");

                if(coloers.equalsIgnoreCase(""+color)){
                    ral = MainActivity.COLORS_RAL.get(i);
                    break;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return ral;
    }






    private void ColorGridePicker(View v) {

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width_hardware = (int) (size.x * 1.01);

        String colors = RALColors.getColors();
        /*String[] array_colors = makeColors(colors);
        int[] array_colors_hex = RGBtoHex(array_colors);*/

        if(MainActivity.ral_colors_from_net != null){
            ArrayList<String> aux = MainActivity.ral_colors_from_net;
            MainActivity.array_colors_hex = new int[aux.size()];
            for (int i = 0; i < aux.size(); i++) {
                MainActivity.array_colors_hex[i] = Integer.parseInt((MainActivity.ral_colors_from_net).get(i));
            }

        }

/*        try {
            MainActivity.COLORS_JSON = AddingIntColor(array_colors_hex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        MainActivity.COLORS_RAL = MainActivity.keys_store;


        GridView gridViewColors = (GridView) v.findViewById(R.id.gridViewColors);
        //gridViewColors.setAdapter(new ColorPickerAdapter(getContext(), colorGrideList));


/*        ArrayList<Integer> newList = new ArrayList<>(MainActivity.COLORS_RAL.size()) ;
        for (String myInt : MainActivity.COLORS_RAL)
        {
            newList.add(Integer.valueOf(myInt));
        }*/
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float scale = metrics.scaledDensity;
        gridViewColors.setAdapter(new ColorPickerAdapter(getContext(), MainActivity.COLORS_RAL, scale));
        gridViewColors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int color = ReturnColorFromPosition(position);
               // MainActivity.myBarColorValue = colorBarValue;
                Elements.productColor = Helper.adjustAlpha(color,alphaFactor);
                if(Elements.productColor != 0){
                    drag_image.setColorFilter(Elements.productColor);
                }

                String ral_choose = ReturnRAlfromColor(color);

                if(ral_choose == null){
                    tv_ral.setText("");
                }else{
                    tv_ral.setText(ral_choose);
                }

                MainActivity.RAL_CHOOSE = ral_choose;

            }
        });

        if(tv_ral != null){
            tv_ral.setText(MainActivity.RAL_CHOOSE);
        }

    }

    public static int ReturnColorFromPosition(int position) {
        int color = 0;
        try {
            JSONObject objs = (JSONObject) (MainActivity.COLORS_JSON.get( MainActivity.COLORS_RAL.get(position)));
            color = Integer.parseInt(objs.get("android_int_color").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return color;

    }


}
