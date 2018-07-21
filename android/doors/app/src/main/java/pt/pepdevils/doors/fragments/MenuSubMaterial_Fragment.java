package pt.pepdevils.doors.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pt.pepdevils.doors.R;
import pt.pepdevils.doors.activities.MainActivity;
import pt.pepdevils.doors.adapters.ItemsAdapter;
import pt.pepdevils.doors.util.Elements;
import pt.pepdevils.doors.util.Helper;
import pt.pepdevils.doors.view.Interface_ReclycerViewOnTounchHack;
import pt.pepdevils.doors.view.RecyclerViewTounchListener;

import java.util.ArrayList;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by pepdevils on 05/07/16.
 */
public class MenuSubMaterial_Fragment extends Fragment implements Interface_ReclycerViewOnTounchHack {

    private View view;
    private Activity a;
    private static ImageLoader imageLoader;

 /*   public static String type_thumb = Elements.IMAGE_TYPE_JPG;
    public static String size_thumb = Elements.IMAGE_JPG_280x280;
    public static String type_drag = Elements.IMAGE_TYPE_JPG;
    public static String size_drag = Elements.IMAGE_JPG_370x370;*/


    private static ArrayList<Elements> all_elements;
    private static ArrayList<Elements> elements_by_model;
    private ArrayList<Elements> more_models;


    private ItemsAdapter itemsAdapter;
    private RecyclerView rv;
    private LinearLayoutManager linearLayoutManager;
    private int fromIndex = 0;
    private int toIndex = 20;
    private final int quantity = 20;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        a = getActivity();


        all_elements = MainActivity.all_elements;
        elements_by_model = FiltrateArrayListByModel(all_elements);


    }

    private ArrayList<Elements> FiltrateArrayListByModel(ArrayList<Elements> arrayList) {
        ArrayList<Elements> response = new ArrayList<>();

        if (MainActivity.drag_image.getDrawable() != null) {

            try {
                String final_tag = "";
                String tag = MainActivity.drag_image.getTag().toString();
                String[] tag_array = tag.split("/");
                for (int i = 0; i < 8; i++) {
                    final_tag += tag_array[i] + "/";
                }

                for (int i = 0; i < arrayList.size(); i++) {

                    String aux = arrayList.get(i).getImage_Door();

                    if (aux.contains(final_tag)) {
                        response.add(arrayList.get(i));
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
                //fechar fragment
                Toast.makeText(a, "Escolha primeiro um modelo", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
            }

        } else {
            //fechar fragment
            Toast.makeText(a, "Escolha primeiro um modelo", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();

        }

        return response;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.menu_sub_material_fragment, container, false);
            CreatRecyclerView();
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        return view;
    }

    private void CreatRecyclerView() {
        rv = (RecyclerView) view.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(a, LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(linearLayoutManager);
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(a));
        itemsAdapter = new ItemsAdapter(a, rv, elements_by_model,imageLoader);
        rv.setAdapter(itemsAdapter);
        rv.addOnItemTouchListener(new RecyclerViewTounchListener(getActivity(), rv, this));
    }


    @Override
    public void onClickListener(View view, int position) {
        InsertOnclick(a, position);
    }

    public static void InsertOnclick(Activity a, int position) {

        ImageView drag_image_f;
        ImageView drag_item_image;
        drag_image_f = (ImageView) a.findViewById(R.id.drag_image);
        drag_item_image = (ImageView) a.findViewById(R.id.drag_item_image);

        String imagepath = elements_by_model.get(position).getImage_Door();
        String line = elements_by_model.get(position).getLine();

        MainActivity.limited = Helper.UpdateUIFragments((AppCompatActivity) a, line, imagepath);
        MenuPallete_Fragment.bt_material.performClick();


        drag_image_f.setVisibility(View.VISIBLE);
        drag_image_f.setImageResource(0);
        drag_image_f.setColorFilter(0);

        drag_item_image.setVisibility(View.VISIBLE);
        drag_item_image.setImageResource(0);
        drag_item_image.setColorFilter(0);

        //String imagepath_clean = Helper.ImageSwicthTypeAndSize(imagepath, type_thumb, type_drag, size_thumb, size_drag);

        //drag_image_f.setImageDrawable(d);
        Helper.DoorImageLoader(a, imagepath, drag_image_f, true, false, imageLoader);
        SetItemToDoor(a, imagepath, drag_item_image);

        drag_image_f.setTag(imagepath);


        drag_image_f.setAlpha(1.0f);
        drag_item_image.setAlpha(1.0f);


        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        //RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(400, 400);


        //drag_image_f.setImageMatrix(MainActivity.ReajustMatrixImage(a,drag_item_image, drag_item_image.getImageMatrix()));
        //drag_image_f.setImageMatrix(drag_item_image.getImageMatrix());
        //drag_item_image.setImageMatrix(drag_image_f.getImageMatrix());

        drag_image_f.setImageMatrix(MainActivity.matrix);
        drag_item_image.setImageMatrix(MainActivity.matrix);

        drag_image_f.setLayoutParams(layoutParams);
        drag_item_image.setLayoutParams(layoutParams);


        //MenuSubColors_Fragment.productColor = 0;

        if (Elements.productColor != 0 && !MainActivity.limited.equalsIgnoreCase("color")) {
            drag_image_f.setColorFilter(Elements.productColor);

        }

        if (MainActivity.limited.equalsIgnoreCase("color")) {
            drag_item_image.setVisibility(View.GONE);
        }


    }

    public static void SetItemToDoor(Activity a, String imagepath, ImageView itemView) {

        //String aux = originalView.getTag().toString();

        String[] aux_array = imagepath.split("/");
        String aux_ = aux_array[6];

        if (aux_.equalsIgnoreCase("New_line_series") || aux_.equalsIgnoreCase("Paineis_Estampados") ||
                aux_.equalsIgnoreCase("Paineis_Retiline") || aux_.equalsIgnoreCase("Paineis_para_Portas")) {
            //aux_ = aux_array[8];

            String linkImagefinal = "";

            //reted string
            for (int i = 0; i < (aux_array.length - 1); i++) {
                linkImagefinal += aux_array[i] + "/";

            }

            if (aux_.equalsIgnoreCase("Paineis_Estampados")) {
                //linkImagefinal += "02-370x370.png";
                linkImagefinal += "01-item.png";
            } else {
                //linkImagefinal += "01-370x370.png";
                linkImagefinal += "01-item.png";
            }


            Helper.DoorImageLoader(a, linkImagefinal, itemView, true, true, imageLoader);
            itemView.setTag(linkImagefinal);

        }


    }
}
