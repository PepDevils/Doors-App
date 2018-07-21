package pt.pepdevils.doors.view;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by pepdevils on 27/07/16.
 */
public class CustomRecyclerView extends RecyclerView {

    private int customState;

    //CONSTRUTORES
    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //METODOS OVERRRIDE

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.state = customState;
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {

        if(!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }


        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setCustomState(ss.state);

    }


    //METODOS



    //CLASSES
    static class SavedState extends BaseSavedState {
        int state;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            state = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);

            out.writeInt(state);


        }

        //required field that makes Parcelables from a Parcel
        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.Creator<SavedState>() {


            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }




    //GETTERS AND SETTERS
    public void setCustomState(int customState) {
        this.customState = customState;
    }


}
