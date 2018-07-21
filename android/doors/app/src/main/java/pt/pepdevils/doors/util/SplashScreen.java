package pt.pepdevils.doors.util;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import pt.pepdevils.doors.activities.InitialActivity;

/**
 * Created by Pedro Fonseca on 19/01/2016.
 */
public class SplashScreen extends AsyncTask<Object, Integer, Object> {

    private Activity activity;
    private Intent i;

    public SplashScreen(Activity activity) {
        this.activity = activity;
    }


    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        i = new Intent(activity, InitialActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(i);
    }

    @Override
    protected Object doInBackground(Object... arg0) {

       // Helper_Api.SecondCAll(activity);

        processamentoPrincipal();

        return null;
    }


    public void processamentoPrincipal() {

        try {
            Thread.sleep(1000); // 1 segundo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
