package pt.pepdevils.doors.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import pt.pepdevils.doors.R;
import pt.pepdevils.doors.activities.OrcamentoActivity;
import pt.pepdevils.doors.activities.MainActivity;
import pt.pepdevils.doors.util.Helper;
import pt.pepdevils.doors.util.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by pepdevils on 05/07/16.
 */
public class MenuSubInfo_Fragment extends Fragment {

    public static final String TAG = "MenuSubInfo_Fragment";

    /*private String type_final = Elements.IMAGE_TYPE_PNG;
    private String size_final = Elements.IMAGE_PNG_1050x1050;

    private String type_final = Elements.IMAGE_TYPE_JPG;
    private String size_final = Elements.IMAGE_JPG_1050x1050;*/

    private static ImageLoader imageLoader;

    private boolean toORca = false;

    private View view;

    private boolean isProductChoosed = false;
    private boolean isProductItemChoosed = false;
    private Bitmap bitmap_image_product;
    private Bitmap bitmap_item_image_product;

    private ImageView image_main, img_produto, img_produto_item, drag_image, drag_item_image;
    private RelativeLayout ll_main;
    private Button bt_orcamento, bt_guardar, bt_clear;
    private TextView edt_linha, edt_modelo, edt_cor, edt_acabamentos, linha, modelo, cor, acabamentos;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UI_activity();

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));


    }

    @Override
    public void onResume() {
        super.onResume();
        cleanUI(true);
        ScrollLayoutDown();

    }

    private void GetInfoDoor() {

        String tag_image = drag_image.getTag().toString();
        // String tag_image_item = drag_item_image.getTag().toString();

        String[] aux_array = tag_image.split("/");
        String material = aux_array[8];

        if (material.contains(".jpg")) {
            String[] aux = material.split(".jpg");
            material = aux[0];
        }

        edt_linha.setText(" " + Helper.CleanLine2(aux_array[6]));
        edt_modelo.setText(" " + aux_array[7]);
        edt_acabamentos.setText(" " + material);
        edt_cor.setText(" " + MainActivity.RAL_CHOOSE);

        if (MainActivity.limited.equalsIgnoreCase("color")) {
            edt_cor.setText(" pré-definida");
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        cleanUI(false);
    }

    private void UI_activity() {
        image_main = (ImageView) getActivity().findViewById(R.id.image);
        drag_image = (ImageView) getActivity().findViewById(R.id.drag_image);
        drag_item_image = (ImageView) getActivity().findViewById(R.id.drag_item_image);
        bt_clear = (Button) getActivity().findViewById(R.id.bt_clear);
        ll_main = (RelativeLayout) getActivity().findViewById(R.id.ll);
    }

    private void cleanUI(Boolean clean) {

        if (clean) {

            image_main.setVisibility(View.GONE);
            ll_main.setVisibility(View.GONE);
            bt_clear.setVisibility(View.GONE);

        } else {

            if (toORca) {
                image_main.setVisibility(View.GONE);
                ll_main.setVisibility(View.GONE);
                bt_clear.setVisibility(View.GONE);
                toORca = false;

            } else {
                image_main.setVisibility(View.VISIBLE);
                ll_main.setVisibility(View.VISIBLE);
                bt_clear.setVisibility(View.VISIBLE);
            }
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menu_sub_info_fragment, container, false);
        UI_view();
        LayoutChangeWidth();
        SetFont();
        ButtomFunctions();

        InsertImageProduct();
        GetColorProduct();
        //insert Material
        //insert glass


        return view;
    }

/*    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Elements.productColor = 0;
    }*/

    private void GetColorProduct() {
        if (img_produto.getTag() != "no product") {
            if (!MainActivity.limited.equalsIgnoreCase("color")) {
                img_produto.setColorFilter(Elements.productColor);
            }

            GetInfoDoor();
        }
    }

    private void ButtomFunctions() {
        bt_orcamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isProductChoosed) {
                    if (!edt_cor.getText().toString().equalsIgnoreCase(" ")) {
                        //conversão para passar por intent
                        //http://stackoverflow.com/questions/11010386/send-bitmap-using-intent-android

                        //Bitmap b = MergeBitmaps( bitmap_item_image_product, bitmap_image_product);
                        //Log.d("hitler", "onClick: "+b);
                        //byte[] byteArray = Helper.ConvertBitmapToByteArray(b);

                        try {

                            Intent i = new Intent(getActivity(), OrcamentoActivity.class);

/*                            byte[] byteArray = Helper.ConvertBitmapToByteArray(bitmap_image_product, 95);
                            i.putExtra("image_byte_array", byteArray);

                            if (isProductItemChoosed) {
                                byte[] byteArray2 = Helper.ConvertBitmapToByteArray(bitmap_item_image_product, 95);
                                i.putExtra("image_byte_array2", byteArray2);
                            }*/

                            i.putExtra("linha", edt_linha.getText().toString());
                            i.putExtra("modelo", edt_modelo.getText().toString());
                            i.putExtra("cor", edt_cor.getText().toString());
                            i.putExtra("acabamentos", edt_acabamentos.getText().toString());
                            i.putExtra("tag_link_product", drag_image.getTag().toString());
                            if(drag_item_image.getDrawable() != null){
                                i.putExtra("tag_link_item", drag_item_image.getTag().toString());
                            }else{
                                drag_item_image.setTag("");
                            }

                            toORca = true;
                            startActivity(i);

                        } catch (Exception e) {
                            e.printStackTrace();
                            toORca = false;

                            Intent i2 = new Intent(getActivity(), OrcamentoActivity.class);
                            if (isProductItemChoosed) {
                                byte[] byteArray2 = Helper.ConvertBitmapToByteArray(bitmap_item_image_product, 100);
                                i2.putExtra("image_byte_array2", byteArray2);
                            }

                            i2.putExtra("linha", edt_linha.getText().toString());
                            i2.putExtra("modelo", edt_modelo.getText().toString());
                            i2.putExtra("cor", edt_cor.getText().toString());
                            i2.putExtra("acabamentos", edt_acabamentos.getText().toString());

                            toORca = true;
                            MainActivity.removeBitmapFromMemCache("door");
                            MainActivity.addBitmapToMemoryCache("door", bitmap_image_product);

                            try {
                                startActivity(i2);
                            } catch (Exception e1) {
                                e1.printStackTrace();
                                toORca = false;
                            }
                        }

                    } else {
                        Toast.makeText(getActivity(), "Por favor escolha uma cor", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "Por favor escolha um produto", Toast.LENGTH_LONG).show();
                }
            }
        });
        bt_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MergeAndSaveImageView();
            }
        });
    }

    private void MergeAndSaveImageView() {
        String DirectoryName = "Doors";
        File rootPath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), DirectoryName);
        if (!rootPath.exists()) {
            rootPath.mkdirs();
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault()).format(new Date());
        String NameFile = "editada_" + timeStamp + ".png";
        File dataFile = new File(rootPath.getPath() + File.separator + NameFile);
        image_main.setDrawingCacheEnabled(true);
        drag_image.setDrawingCacheEnabled(true);
        Bitmap b1 = Bitmap.createScaledBitmap(image_main.getDrawingCache(), image_main.getMeasuredWidth(), image_main.getMeasuredHeight(), true);
        Bitmap b2 = Bitmap.createScaledBitmap(drag_image.getDrawingCache(), drag_image.getMeasuredWidth(), drag_image.getMeasuredHeight(), true);
        Canvas canvas = new Canvas(b1);
        canvas.drawBitmap(b2, 0f, 0f, null);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        try {
            FileOutputStream out = new FileOutputStream(dataFile, false);
            b1.compress(Bitmap.CompressFormat.PNG, 90, out);
            Toast.makeText(getActivity(), "A sua edição foi gravada com sucesso", Toast.LENGTH_LONG).show();
            ScanGallery sg = new ScanGallery(getActivity(), dataFile);
            sg.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    private void LayoutChangeWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int widthPixels = metrics.widthPixels;
        if (widthPixels <= 480) {
            LinearLayout ll_t_1 = (LinearLayout) view.findViewById(R.id.ll_t_1);
            LinearLayout ll_t_2 = (LinearLayout) view.findViewById(R.id.ll_t_2);
            LinearLayout ll_t_3 = (LinearLayout) view.findViewById(R.id.ll_t_3);
            LinearLayout ll_t_4 = (LinearLayout) view.findViewById(R.id.ll_t_4);
            ll_t_1.setOrientation(LinearLayout.VERTICAL);
            ll_t_2.setOrientation(LinearLayout.VERTICAL);
            ll_t_3.setOrientation(LinearLayout.VERTICAL);
            ll_t_4.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(2, 5, 2, 2);
            ll_t_1.setLayoutParams(layoutParams);
            ll_t_2.setLayoutParams(layoutParams);
            ll_t_3.setLayoutParams(layoutParams);
            ll_t_4.setLayoutParams(layoutParams);
            View v_up = view.findViewById(R.id.v_up);
            View v_down = view.findViewById(R.id.v_down);
            v_up.setVisibility(View.VISIBLE);
            v_down.setVisibility(View.VISIBLE);
        }
    }

    private void UI_view() {
        bt_orcamento = (Button) view.findViewById(R.id.bt_orcamento);
        bt_guardar = (Button) view.findViewById(R.id.bt_guardar);
        img_produto = (ImageView) view.findViewById(R.id.img_produto);
        img_produto_item = (ImageView) view.findViewById(R.id.img_produto_item);
        edt_linha = (TextView) view.findViewById(R.id.edt_linha);
        edt_modelo = (TextView) view.findViewById(R.id.edt_modelo);
        edt_cor = (TextView) view.findViewById(R.id.edt_cor);
        edt_acabamentos = (TextView) view.findViewById(R.id.edt_acabamentos);
        linha = (TextView) view.findViewById(R.id.linha);
        modelo = (TextView) view.findViewById(R.id.modelo);
        cor = (TextView) view.findViewById(R.id.cor);
        acabamentos = (TextView) view.findViewById(R.id.acabamentos);
    }

    private void SetFont() {
        ArrayList<TextView> textViewArrayList = new ArrayList<>();
        textViewArrayList.add(bt_clear);
        textViewArrayList.add(bt_orcamento);
        textViewArrayList.add(bt_guardar);
        textViewArrayList.add(modelo);
        textViewArrayList.add(linha);
        textViewArrayList.add(cor);
        textViewArrayList.add(acabamentos);
        Helper.AplicarFontTextual(getActivity(), "Titillium-Bold", textViewArrayList);
        textViewArrayList = new ArrayList<>();
        textViewArrayList.add(edt_modelo);
        textViewArrayList.add(edt_linha);
        textViewArrayList.add(edt_cor);
        textViewArrayList.add(edt_acabamentos);
        Helper.AplicarFontTextual(getActivity(), "Titillium-Regular", textViewArrayList);
    }

    private void InsertImageProduct() {
        if (drag_image != null) {
            String link_tag_imagepath = String.valueOf(drag_image.getTag());
            // String imagepath_clean = Helper.ImageSwicthTypeAndSize(link_tag_imagepath, MenuSubModels_Fragment.type_thumb, type_final, MenuSubModels_Fragment.size_thumb, size_final);
            String imagepath_clean = link_tag_imagepath;

            Drawable d = drag_image.getDrawable();
            if (!imagepath_clean.equalsIgnoreCase("null") && d != null) {
                Helper.DoorImageLoader(getActivity(), imagepath_clean, img_produto, false, false, imageLoader);
                d = drag_image.getDrawable();
                bitmap_image_product = ((BitmapDrawable) d).getBitmap();
                bitmap_image_product = Bitmap.createBitmap(bitmap_image_product, 0, 0, bitmap_image_product.getWidth(), bitmap_image_product.getHeight());
                img_produto.setTag("product");
                isProductChoosed = true;
            } else {
                int imageresource = getResources().getIdentifier("@drawable/miss_product", "drawable", getActivity().getPackageName());
                img_produto.setImageResource(imageresource);
                img_produto.setTag("no product");
            }
        }

        if (drag_image != null) {
            String link_tag_imagepath = String.valueOf(drag_item_image.getTag());
            Drawable d = drag_item_image.getDrawable();
            if (link_tag_imagepath != null && d != null) {
                Helper.DoorImageLoader(getActivity(), link_tag_imagepath, img_produto_item, false, false, imageLoader);
                img_produto_item.setTag("product_item");
                d = drag_item_image.getDrawable();
                bitmap_item_image_product = ((BitmapDrawable) d).getBitmap();
                bitmap_item_image_product = Bitmap.createBitmap(bitmap_item_image_product, 0, 0, bitmap_item_image_product.getWidth(), bitmap_item_image_product.getHeight());
                isProductItemChoosed = true;
            } else {
                Log.d("info", "porta sem item");
            }
        }
    }

    private void ScrollLayoutDown() {
        final ScrollView sv = (ScrollView) view.findViewById(R.id.scv);
        sv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                sv.post(new Runnable() {
                    public void run() {
                        sv.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        });
    }

}


class ScanGallery extends AsyncTask<Void, Void, Void> {
    private Activity a;
    private File f;

    public ScanGallery(Activity a, File f) {
        this.a = a;
        this.f = f;
    }

    @Override
    protected Void doInBackground(Void... params) {
        MediaScannerConnection.scanFile(a, new String[]{
                        f.getAbsolutePath()},
                null, new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        //Toast.makeText(a, "onScanCompleted", Toast.LENGTH_LONG).show();
                    }
                });
        return null;
    }
}
