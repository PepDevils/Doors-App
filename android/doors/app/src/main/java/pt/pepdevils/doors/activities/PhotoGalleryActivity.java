package pt.pepdevils.doors.activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import pt.pepdevils.doors.R;
import pt.pepdevils.doors.util.Helper;
import pt.pepdevils.doors.util.Elements;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PhotoGalleryActivity extends AppCompatActivity {

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    private TextView txt_bottom, tx_tirar_foto, tx_galeria;
    private ImageView bt_tirar_foto, bt_galeria;

    private NiftyDialogBuilder dialog_builder;
    private static Uri fileUri;
    private boolean isMemoryAvailable = false;

    private static String NameFile;

    private static int RESULT_LOAD_PHOTO = 0;
    private static int RESULT_LOAD_IMG_GALLERY = 1;

    private ArrayList<Elements> elements;
    private ArrayList<Elements> all_elements;


    public static String KEY_IMAGE_PATH = "KEY_IMAGE_PATH";
    public static String KEY_IMAGE_PHOTO_PATH = "KEY_IMAGE_PHOTO_PATH";
    String imgDecodableString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery_);

        Intent i = getIntent();
        elements = i.getExtras().getParcelableArrayList("elements");
        all_elements = i.getExtras().getParcelableArrayList("all_elements");


        UI();
        //Dialog();
        SetFont();
        VerificacaoDeMemoria();
        ButtonsFunctions();

        View v = findViewById(R.id.bt_clear);
        v.setVisibility(View.INVISIBLE);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {

            //GALERIA
            if (requestCode == RESULT_LOAD_IMG_GALLERY && resultCode == RESULT_OK && null != data) {

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();


                //iniciar a proxima activity e enviar a imagem por intente
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra(KEY_IMAGE_PATH, imgDecodableString);
                i.putParcelableArrayListExtra("elements", elements);
                i.putParcelableArrayListExtra("all_elements", all_elements);
                startActivity(i);


                //FOTO
            } else if (requestCode == RESULT_LOAD_PHOTO && resultCode == RESULT_OK) {

                Log.d("foto", "data: " + data);

                IniciarProximaActividade();

                //Dialog();


            } else {
                Toast.makeText(this, "Sem nenhuma foto", Toast.LENGTH_LONG).show();

            }
        } catch (
                Exception e
                )

        {
            Toast.makeText(this, "Aconteceu algum erro", Toast.LENGTH_LONG).show();
        }


    }

    private void UI() {
        txt_bottom = (TextView) findViewById(R.id.txt_bottom);
        tx_tirar_foto = (TextView) findViewById(R.id.tx_tirar_foto);
        tx_galeria = (TextView) findViewById(R.id.tx_galeria);
        bt_tirar_foto = (ImageView) findViewById(R.id.bt_tirar_foto);
        bt_galeria = (ImageView) findViewById(R.id.bt_galeria);
    }

    private void SetFont() {

        ArrayList<TextView> textViewArrayList = new ArrayList<>();
        textViewArrayList.add(txt_bottom);
        Helper.AplicarFontTextual(this, "Titillium-Bold", textViewArrayList);

        textViewArrayList = new ArrayList<>();
        textViewArrayList.add(tx_tirar_foto);
        textViewArrayList.add(tx_galeria);
        Helper.AplicarFontTextual(this, "Titillium-Regular", textViewArrayList);

    }

    private void startphoto(){
        if (isMemoryAvailable) {
            try {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                fileUri = getOutputMediaFileUri();
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(intent, RESULT_LOAD_PHOTO);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(PhotoGalleryActivity.this, "Impossibilidade na criação do ficheiro", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(PhotoGalleryActivity.this, "Não tem memória disponivel", Toast.LENGTH_SHORT).show();
        }

    }


    private void ButtonsFunctions() {

        bt_tirar_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.CAMERA);
                    if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[] {Manifest.permission.CAMERA},
                                REQUEST_CODE_ASK_PERMISSIONS);
                        return;
                    }
                    insertDummyImageWrapper2();
                } else {
                    startphoto();
                }



            }
        });


        bt_galeria.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int hasWriteContactsPermission = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                REQUEST_CODE_ASK_PERMISSIONS);
                        return;
                    }
                    insertDummyImageWrapper();
                }else{
                    insertDummyImageWrapper();
                }
            }
        });


    }

    private void insertDummyImageWrapper() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int hasWriteContactsPermission = 0;

            hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    showMessageOKCancel("You need to allow access to SdCard",
                            new DialogInterface.OnClickListener() {
                                @TargetApi(Build.VERSION_CODES.M)
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);
                                }
                            });
                    return;
                }
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
            loadImagefromGallery();

        } else {
            loadImagefromGallery();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void insertDummyImageWrapper2() {

            int hasWriteContactsPermission = 0;
            hasWriteContactsPermission = checkSelfPermission(Manifest.permission.CAMERA);

            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    showMessageOKCancel("You need to allow access to Camera",
                            new DialogInterface.OnClickListener() {
                                @TargetApi(Build.VERSION_CODES.M)
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_ASK_PERMISSIONS);
                                }
                            });
                    return;
                }
                requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
            startphoto();


    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(PhotoGalleryActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }



    private void loadImagefromGallery(){

        Uri uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, uri);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG_GALLERY);

    }

    private void IniciarProximaActividade() {
        Intent i = new Intent(PhotoGalleryActivity.this, MainActivity.class);
        i.putExtra(KEY_IMAGE_PHOTO_PATH, NameFile);
        i.putParcelableArrayListExtra("elements", elements);
        i.putParcelableArrayListExtra("all_elements", all_elements);
        startActivity(i);
    }

    private void VerificacaoDeMemoria() {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        long availableMegs = mi.availMem / 1048576L;
        if (availableMegs >= 10) {
            isMemoryAvailable = true;
        }
    }


    private static Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    /**
     * Create a File for saving an image or video
     */
    private static File getOutputMediaFile() {
        String DirectoryName = "Doors";
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), DirectoryName);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("Doors-foto", "failed to create directory in public storage");
                mediaStorageDir = new File("/sdcard/" + DirectoryName + "/");
                if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
                    Log.d("Doors-foto", "failed to create directory in sdcard storage");
                }
                return null;
            }

        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault()).format(new Date());
        NameFile = "foto_" + timeStamp + ".jpg";

        return new File(mediaStorageDir.getPath() + File.separator + NameFile);
    }

}
