package pt.pepdevils.doors.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import pt.pepdevils.doors.R;
import pt.pepdevils.doors.util.Helper;
import pt.pepdevils.doors.util.Elements;

import java.util.ArrayList;

/**
 * Created by pepdevils on 04/07/16.
 */
public class InstrucoesActivity extends AppCompatActivity {

    private Button bt_iniciar;
    private LinearLayout ll;

    private TextView text_1, text_2, text_3, text_4, text_5;
    // private CircularTextView num_1, num_2, num_3, num_4, num_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_instrucoes);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        UI();

        SetFont();


        final ArrayList<Elements> elements =  getIntent().getExtras().getParcelableArrayList("elements");
        final ArrayList<Elements> all_elements =  getIntent().getExtras().getParcelableArrayList("all_elements");



        assert bt_iniciar != null;
        bt_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InstrucoesActivity.this, PhotoGalleryActivity.class);
                i.putParcelableArrayListExtra("elements",elements);
                i.putParcelableArrayListExtra("all_elements",all_elements);
                startActivity(i);
            }
        });


        //procura de edit textes num linear layout  debug...debug...debug..debug..debug...debug..debug
        /*ArrayList<CircularTextView> circularTextViewArrayList = new ArrayList<CircularTextView>();
        circularTextViewArrayList = Helper.EditTextSearcherInLinearLayout(this, ll);*/


    }

    private void UI() {

        bt_iniciar = (Button) findViewById(R.id.bt_iniciar);
        ll = (LinearLayout) findViewById(R.id.ll);

        text_1 = (TextView) findViewById(R.id.text_1);
        text_2 = (TextView) findViewById(R.id.text_2);
        text_3 = (TextView) findViewById(R.id.text_3);
        text_4 = (TextView) findViewById(R.id.text_4);
        text_5 = (TextView) findViewById(R.id.text_5);

        /*num_1 = (CircularTextView) findViewById(R.id.num_1);
        num_2 = (CircularTextView) findViewById(R.id.num_2);
        num_3 = (CircularTextView) findViewById(R.id.num_3);
        num_4 = (CircularTextView) findViewById(R.id.num_4);
        num_5 = (CircularTextView) findViewById(R.id.num_5);*/

    }


    private void SetFont() {

        ArrayList<TextView> textViewArrayList = new ArrayList<>();
        textViewArrayList.add(bt_iniciar);
       /* textViewArrayList.add(num_1);
        textViewArrayList.add(num_2);
        textViewArrayList.add(num_3);
        textViewArrayList.add(num_4);
        textViewArrayList.add(num_5);*/
        Helper.AplicarFontTextual(this, "Titillium-Bold", textViewArrayList);

        textViewArrayList = new ArrayList<>();
        textViewArrayList.add(text_1);
        textViewArrayList.add(text_2);
        textViewArrayList.add(text_3);
        textViewArrayList.add(text_4);
        textViewArrayList.add(text_5);
        Helper.AplicarFontTextual(this, "Titillium-Regular", textViewArrayList);

    }

}
