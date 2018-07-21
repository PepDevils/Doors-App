package pt.pepdevils.doors.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;

import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pt.pepdevils.doors.R;
import pt.pepdevils.doors.fragments.Limited_menu_color_fragment;
import pt.pepdevils.doors.fragments.Limited_menu_pallete_fragment;
import pt.pepdevils.doors.fragments.MenuPallete_Fragment;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pepdevils on 04/07/16.
 */
public class Helper {

    public static String mCurrentPhotoPath;

/*    public static ArrayList<CircularTextView> EditTextSearcherInLinearLayout(Context c, LinearLayout linearLayout) {

        ArrayList<CircularTextView> editTextArrayList = new ArrayList<CircularTextView>();

        editTextArrayList = null;
        try {
            //para toda a contagem de "filhos" dentro do linear layout
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                //se os "filhos" do layout são edit text então acrescenta-se ao arrayList
                if (linearLayout.getChildAt(i) instanceof EditText) {
                    editTextArrayList.add((CircularTextView) linearLayout.getChildAt(i));
                }//fim if
            }//fim for
        } catch (Exception e) {
            e.printStackTrace();
            editTextArrayList = null;
        }

        if (editTextArrayList == null) {
            Toast toast = Toast.makeText(c, "O valor de" + editTextArrayList + "é null", Toast.LENGTH_LONG);
            toast.show();
        }

        return editTextArrayList;
    }*/

    public static ArrayList<String> CleanAnOrderList(ArrayList<String> desorder, String cut, int pos) {


        Collections.sort(desorder);
        return desorder;

        /*ArrayList<Integer> auxi = new ArrayList<>();
        for (int i = 0; i < desorder.size(); i++) {
            String item = desorder.get(i);
            if (item.contains(cut)) {
                String aux = ((item.split("/"))[pos]);
                aux = aux.replaceAll(cut, "");
                int converted = Integer.valueOf(aux);
                auxi.add(i,converted);
            }
        }

        ArrayList<String> total = new ArrayList<>();
        for(int i = 0; i < auxi.size(); i++)  {
            total.add("");
        }

        for(int i = 0; i < auxi.size(); i++)  {
            total.add(auxi.get(i),desorder.get(i));
        }
        total.removeAll(Arrays.asList(null,""));
        Collections.sort(total);
        return total;*/
    }


    public static void AplicarFontTextual(Context context, String fontName, ArrayList<TextView> textViewArrayList) {

        //os edittext e buttom são chidls do TextView, logo tem herança das suas propriedades

        if (fontName == null) {
            fontName = "Titillium-Regular";
        }


        Typeface typeFace = Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName + ".otf");

        for (int i = 0; i < textViewArrayList.size(); i++) {
            textViewArrayList.get(i).setTypeface(typeFace);
        }


    }

   /* public static void InfoDrawable(ImageView imageView){


        String TAG = "" + imageView.getId();

        Drawable drawable = imageView.getDrawable();

        if(drawable == null){

            drawable = imageView.getBackground();
        }


        Rect imageBounds = drawable.getBounds();


        //original height and width of the bitmap
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int intrinsicWidth = drawable.getIntrinsicWidth();

        //height and width of the visible (scaled) image
        int scaledHeight = imageBounds.height();
        int scaledWidth = imageBounds.width();

        //Find the ratio of the original image to the scaled image
        //Should normally be equal unless a disproportionate scaling
        //(e.g. fitXY) is used.
        float heightRatio = intrinsicHeight / scaledHeight;
        float widthRatio = intrinsicWidth / scaledWidth;

        Log.d(TAG,"intrinsicHeight" + intrinsicHeight);
        Log.d(TAG,"intrinsicWidth" + intrinsicWidth);
        Log.d(TAG,"scaledHeight" + scaledHeight);
        Log.d(TAG,"scaledWidth" + scaledWidth);
        Log.d(TAG,"heightRatio" + heightRatio);
        Log.d(TAG,"widthRatio" + widthRatio);

        //do whatever magic to get your touch point
        //MotionEvent event;

        //get the distance from the left and top of the image bounds
        //int scaledImageOffsetX = event.getX() - imageBounds.left;
        //int scaledImageOffsetY = event.getY() - imageBounds.top;

        //scale these distances according to the ratio of your scaling
        //For example, if the original image is 1.5x the size of the scaled
        //image, and your offset is (10, 20), your original image offset
        //values should be (15, 30).
        //int originalImageOffsetX = scaledImageOffsetX * widthRatio;
        //int originalImageOffsetY = scaledImageOffsetY * heightRatio;


    }*/


    public static void DoorImageLoader(Activity a, String imageUri, final ImageView image, boolean sized, boolean item, final ImageLoader imageLoader) {
        //https://android-arsenal.com/details/1/210#description

        int DoorHeight;
        int DoorWidth;

        if (!item) {
            //porta
            DoorHeight = (int)(1047 * 0.7);       //370
            DoorWidth = (int)(720*0.7);         //173

        } else {
            //items
            DoorHeight = (int)(1047 * 0.7);       //370
           // DoorWidth = 370;         //370
            DoorWidth = (int)(720*0.7);         //173
        }

        final ImageSize targetSize = new ImageSize(DoorWidth, DoorHeight);


/*        if(imageLoader == null){
            imageLoader = ImageLoader.getInstance();
            imageLoader.init(ImageLoaderConfiguration.createDefault(a));
        }*/


        final DisplayImageOptions options = new DisplayImageOptions
                .Builder()
                /*.showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.drawable.empty)
                .showImageOnFail(R.drawable.empty)*/
                .imageScaleType(ImageScaleType.EXACTLY)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .resetViewBeforeLoading(true)
                .build();


/*        if (!sized) {
            imageLoader.displayImage(imageUri, image, options);

        } else {
            imageLoader.displayImage(imageUri, image, targetSize);
        }*/

        imageLoader.displayImage(imageUri, image, options);
        imageLoader.displayImage(imageUri, image, targetSize);


        try {

            //imageLoader.loadImageSync(imageUri, targetSize, options);

/*            imageLoader.loadImage(imageUri, new SimpleImageLoadingListener(){
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    super.onLoadingComplete(imageUri, view, loadedImage);
                    imageLoader.displayImage(imageUri, image, options);
                    imageLoader.displayImage(imageUri, image, targetSize);
                }
            });*/

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static byte[] ConvertBitmapToByteArray(Bitmap bmp, int percentage) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, percentage, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;

    }

/*    public static void CreateDirectoryAndSaveFile(Bitmap imageToSave, String fileName) {

        String DirectoryName = "pt.pepdevils.doors";
         fileName = fileName + ".jpeg";

        File direct = new File(Environment.getExternalStorageDirectory().toString() + "/Captures/" + DirectoryName + "/");

        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/" + DirectoryName + "/");
            wallpaperDirectory.mkdirs();
        }


        File file = new File(new File("/sdcard/" + DirectoryName + "/"), fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

/*    public static File createImageFile(Activity a) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = a.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  *//* prefix *//*
                ".jpg",         *//* suffix *//*
                storageDir      *//* directory *//*
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }*/

/*    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }*/

/*    //METODO PARA RETIRAR OS ASSENTOS DAS STRINGS
    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }*/

/*    public static String ImageSwicthTypeAndSize(String received, String type, String type_new, String size, String size_new) {

        String response;
        response = received.replace(type, type_new);
        response = response.replace(size, size_new);
        return response;

    }*/

    public static ArrayList<String> ChooseDoorsFromList(ArrayList<String> a, String type, String size) {
        ArrayList<String> response = new ArrayList<>();

        for (int i = 0; i < a.size(); i++) {
            String aux = a.get(i);
            if (aux.contains(type)) {
                if (aux.contains(size)) {
                    response.add(aux);
                }
            }
        }


        return response;
    }

    public static ArrayList<Elements> SplitedArrayListToLoadingProcess(ArrayList<Elements> total, int fromIndex, int toIndex) {

        List<Elements> splited;
        ArrayList<Elements> splited_final = new ArrayList<>();

        splited = total.subList(fromIndex, toIndex);

        splited_final.addAll(splited);

        return splited_final;
    }

    public static Elements[] ConcatArrays(ArrayList<Elements[]> elements) {

        Collection<Elements> collection = new ArrayList<Elements>();

        for (int i = 0; i < elements.size(); i++) {
            collection.addAll(Arrays.asList(elements.get(i)));
        }


        Elements[] result = collection.toArray(new Elements[]{});

        return result;
    }

    public static int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    public static String CleanLine(String line) {
        String response;
        switch (line) {
            case "Line_Fashion":
                response = "Line\n Fashion";
                break;
            case "New_line_series":
                response = "New Line\n Series";
                break;
            case "Paineis_Retiline":
                response = "Painéis\n Retiline";
                break;
            case "Paineis_para_Portas":
                response = "Painéis Para\n  Portas";
                break;
            case "Paineis_Estampados":
                response = "Painéis\n  Estampados";
                break;
            case "Paineis_para_Estampados":
                response = "Painéis Para\n Estampados";
                break;
            default:
                response = "modelos\n ";
                break;
        }
        return response;
    }

    public static String CleanLine2(String line) {
        String response;
        switch (line) {
            case "Line_Fashion":
                response = "Line Fashion";
                break;
            case "New_line_series":
                response = "New Line Series";
                break;
            case "Paineis_Retiline":
                response = "Painéis Retiline";
                break;
            case "Paineis_para_Portas":
                response = "Painéis para Portas";
                break;
            case "Paineis_Estampados":
                response = "Painéis Estampados";
                break;
            case "Paineis_para_Estampados":
                response = "Painéis para Estampados";
                break;
            default:
                response = " ";
                break;
        }
        return response;
    }

    public static String UpdateUIFragments(AppCompatActivity a, String line, String imagepath) {

        String res = "";

        String[] aux_array = imagepath.split("/");
        String material = aux_array[8];

        if (line.equalsIgnoreCase("Line_Fashion")) {
            res = "color";
            Limited_menu_pallete_fragment menu = new Limited_menu_pallete_fragment();
            FragmentTransaction fragmentManager = a.getSupportFragmentManager().beginTransaction();
            fragmentManager.replace(R.id.fragment_container, menu);
            fragmentManager.commit();
            return res;
        } else if (material.contains("I") && line.equalsIgnoreCase("Paineis_Retiline")) {
            res = "color";
            Limited_menu_color_fragment menu = new Limited_menu_color_fragment();
            FragmentTransaction fragmentManager = a.getSupportFragmentManager().beginTransaction();
            fragmentManager.replace(R.id.fragment_container, menu);
            fragmentManager.commit();
            return res;
        } else {

            MenuPallete_Fragment menu = new MenuPallete_Fragment();
            FragmentTransaction fragmentManager = a.getSupportFragmentManager().beginTransaction();
            fragmentManager.replace(R.id.fragment_container, menu);
            fragmentManager.commit();
            return res;
        }


    }

/*    public static void removeSubMenuFragment(AppCompatActivity a,android.support.v4.app.Fragment fragment) {

        a.getSupportFragmentManager().beginTransaction()
                .remove(fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .commit();


    }*/

/*    public static void FecharTodosOsFragments(AppCompatActivity a) {

        ArrayList<android.support.v4.app.Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new MenuSubColors_Fragment());
        fragmentArrayList.add(new MenuSubInfo_Fragment());
        fragmentArrayList.add(new MenuSubMaterial_Fragment());
        fragmentArrayList.add(new MenuSubModels_Fragment());
        fragmentArrayList.add(new MenuSubOptions_Fragment());

        for (int i = 0; i < fragmentArrayList.size(); i++) {

            if (fragmentArrayList.get(i) != null) {
                removeSubMenuFragment(a,fragmentArrayList.get(i));
            }
        }


    }*/


}
