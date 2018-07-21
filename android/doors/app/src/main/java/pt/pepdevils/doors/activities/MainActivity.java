package pt.pepdevils.doors.activities;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Environment;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import pt.pepdevils.doors.R;
import pt.pepdevils.doors.util.Helper;

import pt.pepdevils.doors.fragments.MenuPallete_Fragment;

import pt.pepdevils.doors.util.Elements;
import pt.pepdevils.doors.util.ObjectSerializer;
import pt.pepdevils.doors.util.RALColors;
import pt.pepdevils.doors.view.PepeDialogBuilder;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.microedition.khronos.opengles.GL10;


public class MainActivity extends AppCompatActivity {

    // We can be in one of these 3 states
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;

    //imagem em cache para erro de passar por intent
    private static LruCache<String, Bitmap> mMemoryCache;

    private Matrix matrix_clean;
    public static Matrix matrix = new Matrix();
    public static Matrix savedMatrix = new Matrix();
    private PointF start = new PointF();
    private PointF mid = new PointF();
    private float oldDist = 1f;
    private int mode = NONE;

    public static JSONObject jsonObj_colors = null;
    public static ArrayList<String> keys_store = new ArrayList<>();
    public static int[] array_colors_hex;

    private static final String TAG = "Touch";

    public static String limited = "";

    public static ImageView drag_image, drag_item_image;

    private static RelativeLayout ll;

    private boolean flag_clean;

    private String imagePath, imagePhotoPath;
    private ImageView image;
    private Button bt_clear;


    public static JSONObject COLORS_JSON = null;
    public static ArrayList<String> COLORS_RAL = new ArrayList<>();

    public static String RAL_CHOOSE = "";
    public static int myBarColorValue = 1;

    public static ArrayList<Elements> elements;
    public static ArrayList<Elements> all_elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matrix_clean = new Matrix();
        /*RectF drawableRect = new RectF(0, 0, image_width, image_height);
        RectF viewRect = new RectF(0, 0, screen_width, screen_height);*/
        RectF drawableRect = new RectF(0, 0, 100, 300);
        RectF viewRect = new RectF(0, 0, 200, 400);
        matrix_clean.setRectToRect(drawableRect, viewRect, Matrix.ScaleToFit.CENTER);

        UI();
        SetFont();
        ButtonsFunctions();
        MenuFragment();
        ReceiveIntentInfo();
        SetImageBackground();

        GetRalColors();

        elements = getIntent().getExtras().getParcelableArrayList("elements");
        all_elements = getIntent().getExtras().getParcelableArrayList("all_elements");

        COLOWORKS();
        CACHEWORKS();

    }

    //CACHEWORKS
    private void CACHEWORKS() {
        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    //CACHEWORKS - 2 - adicionar bitmap ao cache com um codigo
    public static void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    //CACHEWORKS - 3 - ir buscar essa bitmap pelo codigo
    public static Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

    //CACHEWORKS - 4 - ir remover bitmap do cache
    public static void removeBitmapFromMemCache(String key) {
        if (mMemoryCache.size() > 0) {
            mMemoryCache.remove(key);
        }
    }

    private void COLOWORKS() {
        String colors = RALColors.getColors();
        String[] array_colors = makeColors(colors);
        array_colors_hex = RGBtoHex(array_colors);

        if (MainActivity.ral_colors_from_net != null) {
            ArrayList<String> aux = MainActivity.ral_colors_from_net;
            array_colors_hex = new int[aux.size()];
            for (int i = 0; i < aux.size(); i++) {
                array_colors_hex[i] = Integer.parseInt((MainActivity.ral_colors_from_net).get(i));
            }

        }

        try {
            MainActivity.COLORS_JSON = AddingIntColor(array_colors_hex);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private int[] RGBtoHex(String[] array_colors) {
        int count = array_colors.length;
        int[] colors = new int[count];
        for (int i = 0; i < count; i++) {
            try {
                String[] aux = array_colors[i].split(",");
                int red = Integer.parseInt(aux[0]);
                int green = Integer.parseInt(aux[1]);
                int blue = Integer.parseInt(aux[2]);
                int RGB = Color.argb(255, red, green, blue);
                colors[i] = RGB;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return colors;
    }

    private String[] makeColors(String complete) {
        String[] array = new String[213];
        try {
            MainActivity.jsonObj_colors = new JSONObject(complete);
            int count = MainActivity.jsonObj_colors.length();
            Iterator<?> keys = MainActivity.jsonObj_colors.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                if (MainActivity.jsonObj_colors.get(key) instanceof JSONObject) {
                    MainActivity.keys_store.add(key);
                }
            }
            for (int i = 0; i < count; i++) {
                String aux = MainActivity.keys_store.get(i);

                JSONObject aux_o = (JSONObject) MainActivity.jsonObj_colors.get(aux);
                array[i] = aux_o.get("rgb").toString();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }

    private JSONObject AddingIntColor(int[] array_colors_hex) {
        JSONObject aux = null;
        try {
            for (int i = 0; i < array_colors_hex.length; i++) {
                String value = String.valueOf(array_colors_hex[i]);
                aux = jsonObj_colors.getJSONObject(keys_store.get(i).toString());
                aux.put("android_int_color", value);
                jsonObj_colors.putOpt(keys_store.get(i), aux);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj_colors;
    }


    public static ArrayList<String> ral_colors_from_net;

    private void GetRalColors() {
        if (null == ral_colors_from_net) {
            ral_colors_from_net = new ArrayList<String>();
        }
        SharedPreferences prefs = getSharedPreferences("SHARED_PREFS_FILE", Context.MODE_PRIVATE);
        try {
            ral_colors_from_net = (ArrayList<String>) ObjectSerializer.deserialize(prefs.getString("ral_colors", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        DialogExit();
    }

    private void SetImageBackground() {
        if (imagePath != null) {
            BitmapFactory.Options options;
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeFile(imagePath);
            } catch (OutOfMemoryError e) {
                try {
                    options = new BitmapFactory.Options();
                    options.inSampleSize = 2;
                    bitmap = BitmapFactory.decodeFile(imagePath, options);
                } catch (Exception ee) {
                    Log.e("erro", ee.toString());
                    e.printStackTrace();
                }
            }
            InsertBitmapToImageView(image, bitmap);
        } else if (imagePhotoPath != null) {
            String DCIM_directory = String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
            imagePath = DCIM_directory + "/Doors/" + imagePhotoPath;
            Uri uri = Uri.parse(imagePath);
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            // Bitmap bitmap2 = uriToBitmap(uri);
            InsertBitmapToImageView(image, bitmap);
            //image.setImageURI(uri);
        }
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }


    private void InsertBitmapToImageView(ImageView imageView, Bitmap bitmap) {

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int dev_w = metrics.widthPixels;
        int dev_h = metrics.heightPixels;

        //corrigir grandes tamanhos de foto
        //                      http://stackoverflow.com/questions/4821488/bad-image-quality-after-resizing-scaling-bitmap
        //                      https://developer.android.com/training/displaying-bitmaps/index.html
        //                      https://developer.android.com/training/displaying-bitmaps/load-bitmap.html
        //                      https://developer.android.com/training/displaying-bitmaps/manage-memory.html
        //                      https://developer.android.com/training/displaying-bitmaps/display-bitmap.html

        int max;
        if (dev_h < dev_w) {
            max = dev_w;
        } else {
            max = dev_h;
        }

        if (max >= GL10.GL_MAX_TEXTURE_SIZE) {
            max = GL10.GL_MAX_TEXTURE_SIZE;
        }


        int h = bitmap.getHeight();
        int w = bitmap.getWidth();
        double racio = (double) h / (double) w;

        int memory = bitmap.getByteCount();
        Log.d("memory", "InsertBitmapToImageView: " + memory);

        Bitmap newBitmap;

        if (h >= max && w >= max) {
            newBitmap = Bitmap.createScaledBitmap(bitmap, max, (int) (max * racio), false);

        } else if (h >= max) {
            imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap, w, max, false));
            newBitmap = Bitmap.createScaledBitmap(bitmap, w, max, false);


        } else if (w >= max) {
            imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap, max, h, false));
            newBitmap = Bitmap.createScaledBitmap(bitmap, max, h, false);

        } else {
            newBitmap = bitmap;

        }

        int nh = newBitmap.getHeight();
        int nw = newBitmap.getWidth();
        int nmemory = newBitmap.getByteCount();
        Log.d("newBitmap", "InsertBitmapToImageView: " + nmemory + ",altura:" + nh + ",largura:" + nw);

        imageView.setImageBitmap(newBitmap);

    }

    private void UI() {
        image = (ImageView) findViewById(R.id.image);
        drag_image = (ImageView) findViewById(R.id.drag_image);
        drag_item_image = (ImageView) findViewById(R.id.drag_item_image);
        drag_image.setOnTouchListener(new MyTouchListener());
        drag_item_image.setOnTouchListener(new MyTouchListener());
        bt_clear = (Button) findViewById(R.id.bt_clear);
        ll = (RelativeLayout) findViewById(R.id.ll);
    }

    private void SetFont() {
        ArrayList<TextView> textViewArrayList = new ArrayList<>();
        textViewArrayList.add(bt_clear);
        Helper.AplicarFontTextual(this, "Titillium-Bold", textViewArrayList);
    }

    private void ButtonsFunctions() {
        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drag_image.animate()
                        .alpha(0.0f)
                        .setDuration(1000)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);


                                drag_image.setRotation(0);
                                drag_image.setRotationX(0);
                                drag_image.setRotationY(0);
                                matrix_clean = new Matrix();
                                //matrix_clean.postTranslate(15, 15);
                                drag_image.setImageMatrix(matrix_clean);
                                drag_image.setImageDrawable(null);
                                drag_image.setVisibility(View.GONE);
                                flag_clean = true;
                                RAL_CHOOSE = "";
                                myBarColorValue = 1;

                                if (getSupportFragmentManager().findFragmentById(R.id.fragment_container_sub) != null) {
                                    //Toast.makeText(MainActivity.this, "fragment_container_sub", Toast.LENGTH_SHORT).show();
                                    getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.fragment_container_sub)).commit();
                                }

                            }
                        });

                drag_item_image.animate()
                        .alpha(0.0f)
                        .setDuration(1000)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                drag_item_image.setRotation(0);
                                drag_item_image.setRotationX(0);
                                drag_item_image.setRotationY(0);
                                matrix_clean = new Matrix();
                                //matrix_clean.postTranslate(15, 15);
                                drag_item_image.setImageMatrix(matrix_clean);
                                drag_item_image.setImageDrawable(null);
                                drag_item_image.setVisibility(View.GONE);
                                flag_clean = true;
                            }
                        });
                Elements.productColor = 0;
            }
        });
    }

    private void ReceiveIntentInfo() {

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            if (extras.containsKey(PhotoGalleryActivity.KEY_IMAGE_PATH)) {
                imagePath = intent.getStringExtra(PhotoGalleryActivity.KEY_IMAGE_PATH);

            } else if (extras.containsKey(PhotoGalleryActivity.KEY_IMAGE_PHOTO_PATH)) {
                imagePhotoPath = intent.getStringExtra(PhotoGalleryActivity.KEY_IMAGE_PHOTO_PATH);
                // bitmapParcelable = (Bitmap) intent.getParcelableExtra(PhotoGalleryActivity.KEY_IMAGE_BITMAP);
            } else {
                Toast.makeText(MainActivity.this, "Erro", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void MenuFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        MenuPallete_Fragment menuPallete_fragment = new MenuPallete_Fragment();
        fragmentTransaction.add(R.id.fragment_container, menuPallete_fragment);
        fragmentTransaction.commit();
    }

    //METODOS PARA ARRASTAR E FAZER SCALE NUM IMAGEVIEW
    private final class MyTouchListener implements View.OnTouchListener {
        //  http://www.vogella.com/tutorials/AndroidDragAndDrop/article.html

        public boolean onTouch(View view, MotionEvent motionEvent) {

            if (flag_clean) {
                matrix = matrix_clean;
            }

            drag_image.bringToFront();
            if(drag_item_image.getDrawable() != null){
                drag_item_image.bringToFront();
            }


            int h_max = 0;
            int h_min = 0;
            float imageHeigh = 0;

            try {
                //PARA LIMITAR O SCALE MAX E MIN
                h_max = drag_image.getDrawable().getIntrinsicHeight();
                h_min = 200;
                float[] values = new float[9];
                matrix.getValues(values);
                imageHeigh = values[4] * drag_image.getDrawable().getIntrinsicHeight();
                //PARA LIMITAR O SCALE MAX E MIN
            } catch (Exception e) {
                e.printStackTrace();
            }

            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    savedMatrix.set(matrix);
                    start.set(motionEvent.getX(), motionEvent.getY());
                    Log.d(TAG, "mode=DRAG");
                    mode = DRAG;
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                    mode = NONE;
                    Log.d(TAG, "mode=NONE");
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (mode == DRAG) {
                        matrix.set(savedMatrix);
                        matrix.postTranslate(motionEvent.getX() - start.x,
                                motionEvent.getY() - start.y);
                    } else if (mode == ZOOM) {
                        float newDist = spacing(motionEvent);
                        Log.d(TAG, "newDist=" + newDist);
                        if (newDist > 10f && imageHeigh <= h_max && imageHeigh >= h_min) {
                            matrix.set(savedMatrix);
                            float scale = newDist / oldDist;
                            matrix.postScale(scale, scale, mid.x, mid.y);
                        } else if (imageHeigh > h_max) {
                            float scale = newDist / oldDist;
                            if (scale <= 1) {
                                matrix.set(savedMatrix);
                                matrix.postScale(scale, scale, mid.x, mid.y);
                            }
                        } else if (imageHeigh < h_min) {
                            float scale = newDist / oldDist;
                            if (scale >= 1) {
                                matrix.set(savedMatrix);
                                matrix.postScale(scale, scale, mid.x, mid.y);
                            }
                        }
                    }
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    oldDist = spacing(motionEvent);
                    Log.d(TAG, "oldDist=" + oldDist);
                    if (oldDist > 10f) {
                        savedMatrix.set(matrix);
                        midPoint(mid, motionEvent);
                        mode = ZOOM;
                        Log.d(TAG, "mode=ZOOM");
                    }
                    break;
            }
            flag_clean = false;

            drag_item_image.setImageMatrix(matrix);
            //drag_image.setImageMatrix(drag_item_image.getImageMatrix());
            drag_image.setImageMatrix(matrix);

            dumpEvent(motionEvent);
            return true;
        }
    }

/*    public static Matrix ReajustMatrixImage(Activity a, ImageView imageView, Matrix initialMatrix) {

        float largura_imagem;
        float altura_imagem;

        float scaled_factor_w = 0.265f;

        float scaled_factor_h = 0f;
        float scaled_factor_w_scale = 1f;
        float scaled_factor_h_scale = 1f;

        Object tag_o = imageView.getTag();
        if (tag_o != null) {
            String tag = tag_o.toString();
            String[] aux_array = tag.split("/");
            String aux = aux_array[6];

            if (aux.equalsIgnoreCase("Paineis_Estampados")) {
                scaled_factor_w = 0.275f;       //translate
                scaled_factor_h = -0.004f;      //translate

                scaled_factor_w_scale = 0.95f;
                scaled_factor_h_scale = 0.96f;
            }
        }


        if (imageView.getDrawable() == null) {
            largura_imagem = 370;
            altura_imagem = 370;
        } else {
            largura_imagem = imageView.getDrawable().getIntrinsicWidth();
            altura_imagem = imageView.getDrawable().getIntrinsicHeight();
        }


        float[] values_initial_matrix = new float[9];
        initialMatrix.getValues(values_initial_matrix);

        float x_scale = values_initial_matrix[0];
        float y_scale = values_initial_matrix[4];

        float with_scaled = largura_imagem * x_scale * scaled_factor_w;
        float hight_scaled = altura_imagem * y_scale * scaled_factor_h;

        float scale_with_scaled = x_scale * scaled_factor_w_scale;
        float scale_hight_scaled = y_scale * scaled_factor_h_scale;

        Matrix finalMatrix = new Matrix();

        float[] values_final_matrix = new float[9];
        values_final_matrix[0] = *//*values_initial_matrix[0] **//* scale_with_scaled;
        values_final_matrix[4] = *//*values_initial_matrix[4] **//* scale_hight_scaled;
        values_final_matrix[2] = values_initial_matrix[2] + with_scaled; //nunca retirar
        values_final_matrix[5] = values_initial_matrix[5] + hight_scaled;
        values_final_matrix[6] = values_initial_matrix[6];
        values_final_matrix[8] = values_initial_matrix[8];

        finalMatrix.setValues(values_final_matrix);

        return finalMatrix;

    }*/

    private void dumpEvent(MotionEvent event) {
        String names[] = {"DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",
                "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?"};
        StringBuilder sb = new StringBuilder();
        int action = event.getAction();
        int actionCode = action & MotionEvent.ACTION_MASK;
        sb.append("event ACTION_").append(names[actionCode]);
        if (actionCode == MotionEvent.ACTION_POINTER_DOWN
                || actionCode == MotionEvent.ACTION_POINTER_UP) {
            sb.append("(pid ").append(
                    action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
            sb.append(")");
        }
        sb.append("[");
        for (int i = 0; i < event.getPointerCount(); i++) {
            sb.append("#").append(i);
            sb.append("(pid ").append(event.getPointerId(i));
            sb.append(")=").append((int) event.getX(i));
            sb.append(",").append((int) event.getY(i));
            if (i + 1 < event.getPointerCount())
                sb.append(";");
        }
        sb.append("]");
        Log.d(TAG, sb.toString());
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    private void DialogExit() {

        //                  https://android-arsenal.com/details/1/772

        final PepeDialogBuilder dialog_builder = PepeDialogBuilder.getInstance(this);

        dialog_builder
                .withTitle("Alerta")
                .withMessage("Tem a certeza que deseja sair ? \n o progresso será perdido!")
                .withMessageCenter(true)
                .withTitleColor("#0d243d")
                .withDividerColor("#737474")
                .withMessageColor("#0d243d")
                .withDialogColor("#FFFFFF")
                .withIcon(getResources().getDrawable(R.mipmap.ic_launcher))
                .isCancelableOnTouchOutside(true)
                .withButton1Text("Sim")
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityAnterior();
                        dialog_builder.dismiss();

                    }
                })
                .withButton2Text("Não")
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_builder.dismiss();

                    }
                });

        dialog_builder.show();
    }

    private void ActivityAnterior() {
        Intent i = new Intent(MainActivity.this, InitialActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
