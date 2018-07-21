package pt.pepdevils.doors.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.ColorUtils;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.effects.BaseEffects;

/**
 * Created by pepdevils on 28/07/16.
 */
public class PepeDialogBuilder extends Dialog implements DialogInterface {

    //VARIAVEIS

    private final String defTextColor = "#FFFFFFFF";

    private final String defDividerColor = "#11000000";

    private final String defMsgColor = "#FFFFFFFF";

    private final String defDialogColor = "#FFE74C3C";


    private Effectstype type = null;

    private LinearLayout mLinearLayoutView;

    private RelativeLayout mRelativeLayoutView;

    private LinearLayout mLinearLayoutMsgView;

    private LinearLayout mLinearLayoutTopView;

    private FrameLayout mFrameLayoutCustomView;

    private View mDialogView;

    private View mDivider;

    private TextView mTitle;

    private TextView mMessage;

    private ImageView mIcon;

    private Button mButton1;

    private Button mButton2;

    private int mDuration = -1;

    private static int mOrientation = 1;

    private boolean isCancelable = true;

    private static PepeDialogBuilder instance;

    //CONSTRUTOR
    public PepeDialogBuilder(Context context) {
        super(context);
    }

    public PepeDialogBuilder(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    public static PepeDialogBuilder getInstance(Context context) {
        instance = new PepeDialogBuilder(context, com.gitonway.lee.niftymodaldialogeffects.lib.R.style.dialog_untran);
        return instance;

    }

    //INICIALIZADOR DA UI
    private void init(Context context) {

        mDialogView = View.inflate(context, com.gitonway.lee.niftymodaldialogeffects.lib.R.layout.dialog_layout, null);

        mLinearLayoutView = (LinearLayout) mDialogView.findViewById(com.gitonway.lee.niftymodaldialogeffects.lib.R.id.parentPanel);
        mRelativeLayoutView = (RelativeLayout) mDialogView.findViewById(com.gitonway.lee.niftymodaldialogeffects.lib.R.id.main);
        mLinearLayoutTopView = (LinearLayout) mDialogView.findViewById(com.gitonway.lee.niftymodaldialogeffects.lib.R.id.topPanel);
        mLinearLayoutMsgView = (LinearLayout) mDialogView.findViewById(com.gitonway.lee.niftymodaldialogeffects.lib.R.id.contentPanel);
        mFrameLayoutCustomView = (FrameLayout) mDialogView.findViewById(com.gitonway.lee.niftymodaldialogeffects.lib.R.id.customPanel);

        mTitle = (TextView) mDialogView.findViewById(com.gitonway.lee.niftymodaldialogeffects.lib.R.id.alertTitle);
        mMessage = (TextView) mDialogView.findViewById(com.gitonway.lee.niftymodaldialogeffects.lib.R.id.message);
        mIcon = (ImageView) mDialogView.findViewById(com.gitonway.lee.niftymodaldialogeffects.lib.R.id.icon);
        mDivider = mDialogView.findViewById(com.gitonway.lee.niftymodaldialogeffects.lib.R.id.titleDivider);
        mButton1 = (Button) mDialogView.findViewById(com.gitonway.lee.niftymodaldialogeffects.lib.R.id.button1);
        mButton2 = (Button) mDialogView.findViewById(com.gitonway.lee.niftymodaldialogeffects.lib.R.id.button2);

        setContentView(mDialogView);

        this.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {

                mLinearLayoutView.setVisibility(View.VISIBLE);
                if (type == null) {
                    type = Effectstype.Slidetop;
                }
                start(type);


            }
        });
        mRelativeLayoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCancelable) dismiss();
            }
        });
    }

    public void toDefault() {
        mTitle.setTextColor(Color.parseColor(defTextColor));
        mDivider.setBackgroundColor(Color.parseColor(defDividerColor));
        mMessage.setTextColor(Color.parseColor(defMsgColor));
        mLinearLayoutView.setBackgroundColor(Color.parseColor(defDialogColor));
    }

    public PepeDialogBuilder withDividerColor(String colorString) {
        mDivider.setBackgroundColor(Color.parseColor(colorString));
        return this;
    }

    public PepeDialogBuilder withDividerColor(int color) {
        mDivider.setBackgroundColor(color);
        return this;
    }


    public PepeDialogBuilder withTitle(CharSequence title) {
        toggleView(mLinearLayoutTopView, title);
        mTitle.setText(title);
        return this;
    }

    public PepeDialogBuilder withTitleColor(String colorString) {
        mTitle.setTextColor(Color.parseColor(colorString));
        return this;
    }

    public PepeDialogBuilder withTitleColor(int color) {
        mTitle.setTextColor(color);
        return this;
    }

    public PepeDialogBuilder withMessage(int textResId) {
        toggleView(mLinearLayoutMsgView, textResId);
        mMessage.setText(textResId);
        return this;
    }

    public PepeDialogBuilder withMessage(CharSequence msg) {
        toggleView(mLinearLayoutMsgView, msg);
        mMessage.setText(msg);
        return this;
    }

    public PepeDialogBuilder withMessageCenter(boolean centered) {
        if (centered) {
            mMessage.setGravity(Gravity.CENTER);

        }
        return this;
    }

    public PepeDialogBuilder withMessageColor(String colorString) {
        mMessage.setTextColor(Color.parseColor(colorString));
        return this;
    }

    public PepeDialogBuilder withMessageColor(int color) {
        mMessage.setTextColor(color);
        return this;
    }

    public PepeDialogBuilder withDialogColor(String colorString) {
        mLinearLayoutView.getBackground().setColorFilter(ColorUtils.getColorFilter(Color.parseColor(colorString)));
        return this;
    }

    public PepeDialogBuilder withDialogColor(int color) {
        mLinearLayoutView.getBackground().setColorFilter(ColorUtils.getColorFilter(color));
        return this;
    }

    public PepeDialogBuilder withIcon(int drawableResId) {
        mIcon.setImageResource(drawableResId);
        return this;
    }

    public PepeDialogBuilder withIcon(Drawable icon) {
        mIcon.setImageDrawable(icon);
        return this;
    }

    public PepeDialogBuilder withDuration(int duration) {
        this.mDuration = duration;
        return this;
    }

    public PepeDialogBuilder withEffect(Effectstype type) {
        this.type = type;
        return this;
    }

    public PepeDialogBuilder withButtonDrawable(int resid) {
        mButton1.setBackgroundResource(resid);
        mButton2.setBackgroundResource(resid);
        return this;
    }

    public PepeDialogBuilder withButton1Text(CharSequence text) {
        mButton1.setVisibility(View.VISIBLE);
        mButton1.setText(text);

        return this;
    }

    public PepeDialogBuilder withButton2Text(CharSequence text) {
        mButton2.setVisibility(View.VISIBLE);
        mButton2.setText(text);
        return this;
    }

    public PepeDialogBuilder setButton1Click(View.OnClickListener click) {
        mButton1.setOnClickListener(click);
        return this;
    }

    public PepeDialogBuilder setButton2Click(View.OnClickListener click) {
        mButton2.setOnClickListener(click);
        return this;
    }


    public PepeDialogBuilder setCustomView(int resId, Context context) {
        View customView = View.inflate(context, resId, null);
        if (mFrameLayoutCustomView.getChildCount() > 0) {
            mFrameLayoutCustomView.removeAllViews();
        }
        mFrameLayoutCustomView.addView(customView);
        return this;
    }

    public PepeDialogBuilder setCustomView(View view, Context context) {
        if (mFrameLayoutCustomView.getChildCount() > 0) {
            mFrameLayoutCustomView.removeAllViews();
        }
        mFrameLayoutCustomView.addView(view);

        return this;
    }

    public PepeDialogBuilder isCancelableOnTouchOutside(boolean cancelable) {
        this.isCancelable = cancelable;
        this.setCanceledOnTouchOutside(cancelable);
        return this;
    }

    public PepeDialogBuilder isCancelable(boolean cancelable) {
        this.isCancelable = cancelable;
        this.setCancelable(cancelable);
        return this;
    }

    private void toggleView(View view, Object obj) {
        if (obj == null) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void show() {
        super.show();
    }

    private void start(Effectstype type) {
        BaseEffects animator = type.getAnimator();
        if (mDuration != -1) {
            animator.setDuration(Math.abs(mDuration));
        }
        animator.start(mRelativeLayoutView);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        mButton1.setVisibility(View.GONE);
        mButton2.setVisibility(View.GONE);
    }


}
