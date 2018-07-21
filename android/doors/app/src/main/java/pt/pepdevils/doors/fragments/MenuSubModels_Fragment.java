package pt.pepdevils.doors.fragments;

import android.app.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import pt.pepdevils.doors.R;
import pt.pepdevils.doors.activities.MainActivity;
import pt.pepdevils.doors.adapters.ItemsAdapter;
import pt.pepdevils.doors.util.Helper;
import pt.pepdevils.doors.view.Interface_ReclycerViewOnTounchHack;
import pt.pepdevils.doors.view.RecyclerViewTounchListener;
import pt.pepdevils.doors.util.Elements;


import java.util.ArrayList;


/**
 * Created by pepdevils on 05/07/16.
 */
public class MenuSubModels_Fragment extends Fragment implements Interface_ReclycerViewOnTounchHack {


    private View view;

    private Activity a;

    private static ImageLoader imageLoader;

    private static ArrayList<Elements> elements_list;
    private static ArrayList<Elements> first_ten_doors;
    private ArrayList<Elements> more_doors;

    private RecyclerView rv;

    private ItemsAdapter itemsAdapter;
    private LinearLayoutManager mLayoutManager;
    protected LayoutManagerType mCurrentLayoutManagerType;
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";

/*
    public static String type_thumb = Elements.IMAGE_TYPE_JPG;
    public static String size_thumb = Elements.IMAGE_JPG_280x280;

   public static String type_drag = Elements.IMAGE_TYPE_JPG;
    public static String size_drag = Elements.IMAGE_JPG_370x370;*/

    public static int last_position;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER,
        DEFAULT
    }


    private int fromIndex = 0;
    private int toIndex = 20;
    private final int quantity = 20;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        a = getActivity();
        ReceiveElements();
    }

    private void ReceiveElements() {
        elements_list = MainActivity.elements;
        first_ten_doors = Helper.SplitedArrayListToLoadingProcess(elements_list, fromIndex, toIndex);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

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

        // Configure the RecyclerView
        rv = (RecyclerView) view.findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        //Layout type params
        mLayoutManager = new LinearLayoutManager(a, LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(mLayoutManager);

        mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;

        //creat Adapter
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(a));
        //itemsAdapter = new ItemsAdapter(a, rv, first_ten_doors,imageLoader);   //trocar para carregar 10 de cada vez
        itemsAdapter = new ItemsAdapter(a, rv, elements_list,imageLoader);

        rv.setAdapter(itemsAdapter);
        //rv.setItemAnimator(null/*new DefaultItemAnimator()*/);


        // Add the scroll listener to a adapter
/*        itemsAdapter.setOnLoadMoreListener(new ItemsAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //add progress item
                first_ten_doors.add(null);
                itemsAdapter.notifyItemInserted(first_ten_doors.size() - 1);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //remove progress item
                        first_ten_doors.remove(first_ten_doors.size() - 1);
                        itemsAdapter.notifyItemRemoved(first_ten_doors.size());

                        fromIndex = toIndex;
                        toIndex = toIndex + quantity;

                        if (toIndex >= elements_list.size()) {
                            toIndex = elements_list.size();
                        }

                        more_doors = Helper.SplitedArrayListToLoadingProcess(elements_list, fromIndex, toIndex);


                        //add items one by one
                        for (int i = 0; i < more_doors.size(); i++) {
                            first_ten_doors.add(more_doors.get(i));
                            itemsAdapter.notifyItemInserted(first_ten_doors.size());
                        }


                        itemsAdapter.notifyItemInserted(first_ten_doors.size());
                        itemsAdapter.notifyDataSetChanged();

                        itemsAdapter.setLoaded();
                        //or you can add all at once but do not forget to call mAdapter.notifyDataSetChanged();

                    }
                }, 1000);


            }
        });*/

        rv.addOnItemTouchListener(new RecyclerViewTounchListener(getActivity(), rv, this));

    }

    private static Bundle mBundleRecyclerViewState;
    private final String KEY_RECYCLER_STATE = "recycler_state";


    @Override
    public void onPause() {
        super.onPause();

        // save RecyclerView state
        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = ((LinearLayoutManager)rv.getLayoutManager()).onSaveInstanceState();

        mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, listState);

    }

    @Override
    public void onResume() {
        super.onResume();

        // restore RecyclerView state
        if (mBundleRecyclerViewState != null) {
            Parcelable listState = mBundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
            ((LinearLayoutManager)rv.getLayoutManager()).onRestoreInstanceState(listState);
        }
    }



    @Override
    public void onClickListener(View view, int position) {
        InsertOnclick(getActivity(), position);

    }

    public static void InsertOnclick(Activity a, int position) {

        MainActivity.RAL_CHOOSE = "";

        ImageView drag_image_f;
        ImageView drag_item_image;
        drag_image_f = (ImageView) a.findViewById(R.id.drag_image);
        drag_item_image = (ImageView) a.findViewById(R.id.drag_item_image);

        String imagepath = elements_list.get(position).getImage_Door();
        String line = elements_list.get(position).getLine();

        MainActivity.limited = Helper.UpdateUIFragments((AppCompatActivity) a, line, imagepath);
        MenuPallete_Fragment.bt_modelos.performClick();

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

       // drag_image_f.setImageMatrix(MainActivity.ReajustMatrixImage(a, drag_item_image, drag_item_image.getImageMatrix()));
       // drag_image_f.setImageMatrix(drag_item_image.getImageMatrix());

        //drag_item_image.setImageMatrix(drag_image_f.getImageMatrix());

        drag_image_f.setImageMatrix(MainActivity.matrix);
        drag_item_image.setImageMatrix(MainActivity.matrix);


        drag_image_f.setLayoutParams(layoutParams);
        drag_item_image.setLayoutParams(layoutParams);


        Elements.productColor = 0;
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

            //linkImagefinal += "01-370x370.png";
            linkImagefinal += "01-item.png";

            Helper.DoorImageLoader(a, linkImagefinal, itemView, true, true, imageLoader);
            itemView.setTag(linkImagefinal);

        }


    }
}
