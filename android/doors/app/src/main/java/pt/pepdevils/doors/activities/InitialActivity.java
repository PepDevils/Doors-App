package pt.pepdevils.doors.activities;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import pt.pepdevils.doors.R;
import pt.pepdevils.doors.util.Helper;
import pt.pepdevils.doors.util.Elements;

import net.grobas.view.MovingImageView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by pepdevils on 01/07/16.
 */
public class InitialActivity extends AppCompatActivity {

    private Button bt_instrut, bt_iniciar;
    private ImageView top_image;
    private LinearLayout bottom_layout;
    private MovingImageView movingImageView;

    public static ArrayList<Elements> elements;
    public static ArrayList<Elements> all_elements = new ArrayList<>();

    public static ArrayList<String> alldoors = new ArrayList<>();

    //VERIFICAR
    /*
    *
    * *#*#2846579#*#*
    *
    * and a hidden menu is shown. Go to "Background Setting" -> "Log setting" and enable the log levels.
    *
    *
    *
    *
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        UI();
        SetFont();
        BackgroundImageAnim();
        ButtonFunctions();
        DownloadFromApi();
        AjustSizeLayout();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // AjustSizeLayout();
    }

    private void AjustSizeLayout() {

        //altura da imagem relacionada com o tamanho do ecran
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int height = (int) (size.y * 0.99);

        //movingImageView.getLayoutParams().height = (int) (height * 0.7);
        movingImageView.getLayoutParams().height = (int) (height * 0.65);


        top_image.getLayoutParams().height = ((int) (height * 0.15));

/*        int h = top_image.getHeight();

        if (h > 100) {
            //top_image.getLayoutParams().height = 100;
            top_image.getLayoutParams().height = 20;
        }

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) bottom_layout.getLayoutParams();
        params.height = (int) (height * 0.25);
        bottom_layout.setLayoutParams(params);

        h = bottom_layout.getHeight();

        //int hr = 16 + 10 + 30 + 10 + 30 + 16 + 10;
        int hr = 16 ;

        if (h > hr) {
            params.height = hr;
            bottom_layout.setLayoutParams(params);
        }*/


    }

    private void BackgroundImageAnim() {
        //link https://android-arsenal.com/details/1/1850
        movingImageView.getMovingAnimator().setInterpolator(new BounceInterpolator());
        movingImageView.getMovingAnimator().addCustomMovement().
                //addDiagonalMoveToDownRight().
                        //addHorizontalMoveToLeft().
                        //addDiagonalMoveToUpRight().
                        //addVerticalMoveToDown().
                        //addHorizontalMoveToLeft().
                        //addVerticalMoveToUp().
                        start();
    }

    private void ButtonFunctions() {
        assert bt_iniciar != null;
        bt_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InitialActivity.this, PhotoGalleryActivity.class);
                i.putParcelableArrayListExtra("elements", elements);
                i.putParcelableArrayListExtra("all_elements", all_elements);
                startActivity(i);
            }
        });


        assert bt_instrut != null;
        bt_instrut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InitialActivity.this, InstrucoesActivity.class);
                i.putParcelableArrayListExtra("elements", elements);
                i.putParcelableArrayListExtra("all_elements", all_elements);
                startActivity(i);
            }
        });
    }

    private void SetFont() {
        ArrayList<TextView> textViewArrayList = new ArrayList<>();
        textViewArrayList.add(bt_instrut);
        textViewArrayList.add(bt_iniciar);
        Helper.AplicarFontTextual(this, "Titillium-Bold", textViewArrayList);
    }

    private void UI() {
        bt_instrut = (Button) findViewById(R.id.bt_instrut);
        bt_iniciar = (Button) findViewById(R.id.bt_iniciar);
        movingImageView = (MovingImageView) findViewById(R.id.image);
        top_image = (ImageView) findViewById(R.id.top_image);
        bottom_layout = (LinearLayout) findViewById(R.id.bottom_layout);
    }

    private void DownloadFromApi() {
        // ArrayList<String> alldoors = new ArrayList<>();
        ArrayList<String> alldoors_LineFashion = new ArrayList<>();
        ArrayList<String> alldoors_New_line_series = new ArrayList<>();
        ArrayList<String> alldoors_Paineis_Estampados = new ArrayList<>();
        ArrayList<String> alldoors_Paineis_Retiline = new ArrayList<>();
        ArrayList<String> alldoors_Paineis_para_Portas = new ArrayList<>();


        alldoors = Loading.element_geral.getAll_Image_Door();
        Elements[] all = ArrayToElements(alldoors);
        ArrayList<Elements[]> aux = new ArrayList<>();
        aux.add(all);
        all_elements = new ArrayList<Elements>(Arrays.asList(Helper.ConcatArrays(aux)));

        alldoors_LineFashion = FilterArrayListStrings(alldoors, "Line_Fashion");
        alldoors_LineFashion = Helper.CleanAnOrderList(alldoors_LineFashion,"LF_", 7);

        alldoors_New_line_series = FilterArrayListStrings(alldoors, "New_line_series");
        alldoors_New_line_series = JustOriginalDoor(alldoors_New_line_series, "_2/");
        alldoors_New_line_series = Helper.CleanAnOrderList(alldoors_New_line_series,"NL", 7);

        alldoors_Paineis_Estampados = FilterArrayListStrings(alldoors, "Paineis_Estampados");
        alldoors_Paineis_Estampados = JustOriginalDoor(alldoors_Paineis_Estampados, "/2/");

        alldoors_Paineis_Retiline = FilterArrayListStrings(alldoors, "Paineis_Retiline");
        alldoors_Paineis_Retiline = JustOriginalDoorByCounting(alldoors_Paineis_Retiline, 2);
        alldoors_Paineis_Retiline = Helper.CleanAnOrderList(alldoors_Paineis_Retiline,"NL", 7);

        alldoors_Paineis_para_Portas = FilterArrayListStrings(alldoors, "Paineis_para_Portas");
        alldoors_Paineis_para_Portas = JustOriginalDoor(alldoors_Paineis_para_Portas, "/2/");

        elements = new ArrayList<>();

        Elements[] elements_1 = ArrayToElements(alldoors_LineFashion);
        Elements[] elements_2 = ArrayToElements(alldoors_New_line_series);
        Elements[] elements_3 = ArrayToElements(alldoors_Paineis_Estampados);
        Elements[] elements_4 = ArrayToElements(alldoors_Paineis_Retiline);
        Elements[] elements_5 = ArrayToElements(alldoors_Paineis_para_Portas);

        //juntar tudo a ordem de adicionar é como ele é exposto
        ArrayList<Elements[]> allelements = new ArrayList<>();
        allelements.add(elements_3); //Paineis_Estampados
        allelements.add(elements_4); //Retiline
        allelements.add(elements_5); //Paineis para Portas
        allelements.add(elements_2); //New_line_series
        allelements.add(elements_1); //Line_Fashion

        elements = new ArrayList<Elements>(Arrays.asList(Helper.ConcatArrays(allelements)));

    }

    private ArrayList<String> JustOriginalDoorByCounting(ArrayList<String> arraylist, int countChars) {
        ArrayList<String> response = new ArrayList<>();
        for (int i = 0; i < arraylist.size(); i++) {
            String[] aux = arraylist.get(i).split("/");
            String aux_ = aux[8];
            if (aux_.length() == countChars) {

                response.add(arraylist.get(i));
            }
        }
        return response;
    }

    private ArrayList<String> JustOriginalDoor(ArrayList<String> arraylist, String original_tag) {
        ArrayList<String> response = new ArrayList<>();
        // String t = "http://xxxx.xxxx/image/data/produtos/New_line_series/NL02/NL2_2/01.jpg";
        for (int i = 0; i < arraylist.size(); i++) {
            String aux = arraylist.get(i);
            if (aux.contains(original_tag)) {
                response.add(aux);
            }
        }
        return response;
    }

    public static Elements[] ArrayToElements(ArrayList<String> elements) {
        Elements[] response = new Elements[elements.size()];
        for (int i = 0; i < elements.size(); i++) {
            String image_door = elements.get(i);
            String line = ExtratcModel(image_door, 6);
            String model = ExtratcModel(image_door, 7);
            String id_door = line + "_" + model + "_" + i;
            response[i] = new Elements(id_door, line, model, image_door);
        }
        return response;
    }

    public static String ExtratcModel(String image_link, int position) {
        String response;
        String aux[] = image_link.split("/");
        response = aux[position];  // 7 model 6 line
        return response;
    }


    public static ArrayList<String> FilterArrayListStrings(ArrayList<String> initial, String filter) {
        ArrayList<String> response = new ArrayList<>();
        for (int i = 0; i < initial.size(); i++) {
            if (initial.get(i).contains(filter)) {
                response.add(initial.get(i));
            }
        }
        return response;
    }
}
