package pt.pepdevils.doors.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import pt.pepdevils.doors.R;
import pt.pepdevils.doors.util.Elements;
import pt.pepdevils.doors.util.Helper_Api;
import pt.pepdevils.doors.util.Helper_Net;
import pt.pepdevils.doors.util.ObjectSerializer;

import java.util.ArrayList;

/**
 * Created by pepdevils on 11/07/16.
 */
public class Loading extends FragmentActivity {

    protected ImageView image;
    protected Animation fade;
    protected boolean flag_connectivity = false;

    public static Elements element_geral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity_loading);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        DownloadsFromApi();

    }

    private void  DownloadsFromApi(){
        Helper_Api.GetInfo(this);
        Helper_Api.GetColors(this);
        element_geral = Helper_Api.GetAllImageDoors(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        image = (ImageView) findViewById(R.id.image);

        fade = new AlphaAnimation(0.00f, 1.00f);
        fade.setDuration(1000);
        fade.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {


                // saber se existe conectividade com a internet
                if (Helper_Net.existeConexao(Loading.this)) {
                    flag_connectivity = true;

                    // saber se existe conectividade com a api
                    Helper_Api.FirstCAll(Loading.this);

                }


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        image.startAnimation(fade);


    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }


}
