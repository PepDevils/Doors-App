package pt.pepdevils.doors.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by pepdevils on 27/07/16.
 */
public class RecyclerViewTounchListener implements RecyclerView.OnItemTouchListener {

    //VARIAVEIS
    private Context mc;
    private GestureDetector mgd;
    private Interface_ReclycerViewOnTounchHack mirvoth;


    //CONSTRUTOR
    public RecyclerViewTounchListener(Context c, final RecyclerView rv, Interface_ReclycerViewOnTounchHack irvoth) {
        mc = c;
        mirvoth = irvoth;

        mgd = new GestureDetector(mc, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Log.d("RecyclerView", "Item Clicked onSingleTapUp");


                View cv = rv.findChildViewUnder(e.getX(), e.getY());

                if (cv != null && mirvoth != null) {

                    mirvoth.onClickListener(cv, rv.getChildAdapterPosition(cv));

                }


                return true;

            }
        });
    }



    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mgd.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
