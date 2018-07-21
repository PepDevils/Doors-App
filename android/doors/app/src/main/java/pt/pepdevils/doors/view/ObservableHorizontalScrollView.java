package pt.pepdevils.doors.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by pepdevils on 22/07/16.
 */
public class ObservableHorizontalScrollView extends HorizontalScrollView {

    private HorizontalScrollViewListener horizontalScrollViewListener = null;

    public ObservableHorizontalScrollView(Context context) {
        super(context);
    }

    public ObservableHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setHorizontalScrollViewListener(HorizontalScrollViewListener horizontalScrollViewListener)
    {
        this.horizontalScrollViewListener = horizontalScrollViewListener;
    }

/*    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {

        if(clampedX && scrollX != 0){

            if (horizontalScrollViewListener != null) {
                horizontalScrollViewListener.onScrollEnded(this,  scrollX,  scrollY,  clampedX,  clampedY);
            }

        }

        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }*/

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {

        if (horizontalScrollViewListener != null) {
            horizontalScrollViewListener.onScrollChanged(this,  l,  t,  oldl,  oldt);
        }

    }

}
