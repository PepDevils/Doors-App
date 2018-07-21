package pt.pepdevils.doors.fragments;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import pt.pepdevils.doors.R;

import java.util.Calendar;

/**
 * Created by pepdevils on 05/07/16.
 */
public class MenuSubOptions_Fragment extends Fragment {

    private View view;

    private ImageView drag_image_f, drag_item_image;
    private int increment_rot, increment_rot_x, increment_rot_y;
    private Button bt_rotator, bt_rotator_, bt_rotator_x, bt_rotator_x_, bt_rotator_y, bt_rotator_y_, bt_rotator_clear, bt_mirror;

    private boolean longClickActive = false;
    private boolean isInitialStateNotMirror = true;

    private int Limit_x_y_grades_max = 30;
    private int Limit_x_y_grades_minor = -Limit_x_y_grades_max;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.menu_sub_options_fragment, container, false);

        drag_image_f = (ImageView) getActivity().findViewById(R.id.drag_image);
        drag_item_image = (ImageView) getActivity().findViewById(R.id.drag_item_image);
        bt_rotator = (Button) view.findViewById(R.id.bt_rotator);
        bt_rotator_ = (Button) view.findViewById(R.id.bt_rotator_);
        bt_rotator_x = (Button) view.findViewById(R.id.bt_rotator_x);
        bt_rotator_x_ = (Button) view.findViewById(R.id.bt_rotator_x_);
        bt_rotator_y = (Button) view.findViewById(R.id.bt_rotator_y);
        bt_rotator_y_ = (Button) view.findViewById(R.id.bt_rotator_y_);
        bt_rotator_clear = (Button) view.findViewById(R.id.bt_rotator_clear);
        bt_mirror = (Button) view.findViewById(R.id.bt_mirror);

        increment_rot = 0;
        increment_rot_x = 0;
        increment_rot_y = 0;

        isInitialStateNotMirror = true;

        ButtomsFunctions();
        ButtonFunctionLong();
        return view;
    }


    private void ButtonFunctionLong() {

        bt_rotator.setOnTouchListener(new View.OnTouchListener() {


            private static final int MIN_CLICK_DURATION = 500; //meio segundo
            private long startClickTime;


            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    //entra só quando o dedo sai do botão
                    case MotionEvent.ACTION_UP:
                        bt_rotator.setPressed(false);
                        RotationView(true);
                        longClickActive = false;
                        break;
                    //entra só quando o botão é clicado
                    case MotionEvent.ACTION_DOWN:
                        bt_rotator.setPressed(true);
                        if (longClickActive == false) {
                            longClickActive = true;
                            startClickTime = Calendar.getInstance().getTimeInMillis();
                        }
                        break;
                    //entra sempre seja qual for o movimento
                    case MotionEvent.ACTION_MOVE:
                        if (longClickActive == true) {

                            long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                            if (clickDuration >= MIN_CLICK_DURATION) {

                                RotationView(true);
                                //colocando o longClickActive, este vai repetir sempre esta acção enquanto o botão estiver selecionado
                                longClickActive = true;
                            }
                        }
                        break;
                    default:
                        bt_rotator.setPressed(false);
                        break;

                }


                return true;
            }
        });

        bt_rotator_.setOnTouchListener(new View.OnTouchListener() {


            private static final int MIN_CLICK_DURATION = 500; //meio segundo
            private long startClickTime;

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    //entra só quando o dedo sai do botão
                    case MotionEvent.ACTION_UP:
                        bt_rotator_.setPressed(false);
                        RotationView(false);

                        longClickActive = false;
                        break;
                    //entra só quando o botão é clicado
                    case MotionEvent.ACTION_DOWN:
                        bt_rotator_.setPressed(true);
                        if (longClickActive == false) {
                            longClickActive = true;
                            startClickTime = Calendar.getInstance().getTimeInMillis();
                        }
                        break;
                    //entra sempre seja qual for o movimento
                    case MotionEvent.ACTION_MOVE:
                        if (longClickActive == true) {
                            long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                            if (clickDuration >= MIN_CLICK_DURATION) {

                                RotationView(false);

                                //colocando o longClickActive, este vai repetir sempre esta acção enquanto o botão estiver selecionado
                                longClickActive = true;
                            }
                        }
                        break;
                    default:
                        bt_rotator_.setPressed(false);
                        break;
                }
                return true;
            }
        });

        bt_rotator_x.setOnTouchListener(new View.OnTouchListener() {


            private static final int MIN_CLICK_DURATION = 500; //meio segundo
            private long startClickTime;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    //entra só quando o dedo sai do botão
                    case MotionEvent.ACTION_UP:
                        bt_rotator_x.setPressed(false);
                        RotationView_X(true);

                        longClickActive = false;
                        break;
                    //entra só quando o botão é clicado
                    case MotionEvent.ACTION_DOWN:
                        bt_rotator_x.setPressed(true);
                        if (longClickActive == false) {
                            longClickActive = true;
                            startClickTime = Calendar.getInstance().getTimeInMillis();
                        }
                        break;
                    //entra sempre seja qual for o movimento
                    case MotionEvent.ACTION_MOVE:
                        if (longClickActive == true) {
                            long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                            if (clickDuration >= MIN_CLICK_DURATION) {

                                RotationView_X(true);

                                //colocando o longClickActive, este vai repetir sempre esta acção enquanto o botão estiver selecionado
                                longClickActive = true;
                            }
                        }
                        break;
                    default:
                        bt_rotator_x.setPressed(false);
                        break;
                }
                return true;
            }
        });

        bt_rotator_x_.setOnTouchListener(new View.OnTouchListener() {


            private static final int MIN_CLICK_DURATION = 500; //meio segundo
            private long startClickTime;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    //entra só quando o dedo sai do botão
                    case MotionEvent.ACTION_UP:
                        bt_rotator_x_.setPressed(false);

                        RotationView_X(false);

                        longClickActive = false;
                        break;
                    //entra só quando o botão é clicado
                    case MotionEvent.ACTION_DOWN:
                        bt_rotator_x_.setPressed(true);
                        if (longClickActive == false) {
                            longClickActive = true;
                            startClickTime = Calendar.getInstance().getTimeInMillis();
                        }
                        break;
                    //entra sempre seja qual for o movimento
                    case MotionEvent.ACTION_MOVE:
                        if (longClickActive == true) {
                            long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                            if (clickDuration >= MIN_CLICK_DURATION) {

                                RotationView_X(false);

                                //colocando o longClickActive, este vai repetir sempre esta acção enquanto o botão estiver selecionado
                                longClickActive = true;
                            }
                        }
                        break;
                    default:
                        bt_rotator_x_.setPressed(false);
                        break;
                }
                return true;
            }
        });

        bt_rotator_y.setOnTouchListener(new View.OnTouchListener() {


            private static final int MIN_CLICK_DURATION = 500; //meio segundo
            private long startClickTime;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    //entra só quando o dedo sai do botão
                    case MotionEvent.ACTION_UP:
                        bt_rotator_y.setPressed(false);
                        RotationView_Y(true);

                        longClickActive = false;
                        break;
                    //entra só quando o botão é clicado
                    case MotionEvent.ACTION_DOWN:
                        bt_rotator_y.setPressed(true);
                        if (longClickActive == false) {
                            longClickActive = true;
                            startClickTime = Calendar.getInstance().getTimeInMillis();
                        }
                        break;
                    //entra sempre seja qual for o movimento
                    case MotionEvent.ACTION_MOVE:
                        if (longClickActive == true) {
                            long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                            if (clickDuration >= MIN_CLICK_DURATION) {

                                RotationView_Y(true);

                                //colocando o longClickActive, este vai repetir sempre esta acção enquanto o botão estiver selecionado
                                longClickActive = true;
                            }
                        }
                        break;
                    default:
                        bt_rotator_y.setPressed(false);
                        break;
                }
                return true;
            }
        });

        bt_rotator_y_.setOnTouchListener(new View.OnTouchListener() {


            private static final int MIN_CLICK_DURATION = 500; //meio segundo
            private long startClickTime;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    //entra só quando o dedo sai do botão
                    case MotionEvent.ACTION_UP:
                        bt_rotator_y_.setPressed(false);
                        RotationView_Y(false);

                        longClickActive = false;
                        break;
                    //entra só quando o botão é clicado
                    case MotionEvent.ACTION_DOWN:
                        bt_rotator_y_.setPressed(true);
                        if (longClickActive == false) {
                            longClickActive = true;
                            startClickTime = Calendar.getInstance().getTimeInMillis();
                        }
                        break;
                    //entra sempre seja qual for o movimento
                    case MotionEvent.ACTION_MOVE:
                        if (longClickActive == true) {
                            long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                            if (clickDuration >= MIN_CLICK_DURATION) {

                                RotationView_Y(false);

                                //colocando o longClickActive, este vai repetir sempre esta acção enquanto o botão estiver selecionado
                                longClickActive = true;
                            }
                        }
                        break;
                    default:
                        bt_rotator_y_.setPressed(false);
                        break;
                }
                return true;
            }
        });


    }

    private void ButtomsFunctions() {

        bt_mirror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MirrorView();
            }
        });

        bt_rotator_clear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Clear_RotationView();

                if (!isInitialStateNotMirror) {

                    Drawable d = drag_image_f.getDrawable();
                    Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
                    Matrix matrix = new Matrix();
                    matrix.preScale(-1.0f, 1.0f);
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                    drag_image_f.setImageBitmap(bitmap);

                    if (drag_item_image.getDrawable() != null) {

                        Drawable d2 = drag_item_image.getDrawable();
                        Bitmap bitmap2 = ((BitmapDrawable) d2).getBitmap();
                        Matrix matrix2 = new Matrix();
                        matrix2.preScale(-1.0f, 1.0f);
                        bitmap = Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), bitmap2.getHeight(), matrix2, true);
                        drag_item_image.setImageBitmap(bitmap);

                    }


                    isInitialStateNotMirror = true;
                }
            }
        });


    }

    private void RotationView(boolean positive) {


        if (increment_rot <= Limit_x_y_grades_max || increment_rot >= Limit_x_y_grades_minor) {


            if (positive) {
                if (increment_rot < Limit_x_y_grades_max - 1) {
                    increment_rot++;
                } else {
                    increment_rot = Limit_x_y_grades_max - 1;
                }
            } else {

                if (increment_rot > Limit_x_y_grades_minor + 1) {
                    increment_rot--;

                } else {

                    increment_rot = Limit_x_y_grades_minor + 1;
                }

            }

            drag_image_f.setRotation(increment_rot);
            drag_item_image.setRotation(increment_rot);


        }


    }

    private void RotationView_X(boolean positive) {

        if (increment_rot_x <= Limit_x_y_grades_max || increment_rot_x >= Limit_x_y_grades_minor) {

            if (positive) {

                if (increment_rot_x < Limit_x_y_grades_max - 1) {
                    increment_rot_x++;
                } else {
                    increment_rot_x = Limit_x_y_grades_max - 1;
                }

            } else {

                if (increment_rot_x > Limit_x_y_grades_minor + 1) {
                    increment_rot_x--;

                } else {

                    increment_rot_x = Limit_x_y_grades_minor + 1;
                }

            }

            drag_image_f.setRotationX(increment_rot_x);
            drag_item_image.setRotationX(increment_rot_x);

        }


    }

    private void RotationView_Y(boolean positive) {

        if (increment_rot_y <= Limit_x_y_grades_max || increment_rot_y >= Limit_x_y_grades_minor) {

            if (positive) {

                if (increment_rot_y < Limit_x_y_grades_max - 1) {
                    increment_rot_y++;
                } else {
                    increment_rot_y = Limit_x_y_grades_max - 1;
                }

            } else {
                if (increment_rot_y > Limit_x_y_grades_minor + 1) {
                    increment_rot_y--;
                } else {
                    increment_rot_y = Limit_x_y_grades_minor + 1;
                }

            }

            drag_image_f.setRotationY(increment_rot_y);
            drag_item_image.setRotationY(increment_rot_y);

        }


    }

    private void Clear_RotationView() {
        drag_image_f.setRotation(0);
        drag_image_f.setRotationX(0);
        drag_image_f.setRotationY(0);
        drag_item_image.setRotation(0);
        drag_item_image.setRotationX(0);
        drag_item_image.setRotationY(0);
        increment_rot = 0;
        increment_rot_x = 0;
        increment_rot_y = 0;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        drag_image_f.setLayoutParams(layoutParams);
        drag_item_image.setLayoutParams(layoutParams);
    }

    private void MirrorView() {
        if (drag_image_f != null) {
            Drawable d = drag_image_f.getDrawable();
            if (d != null) {
                Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
                Matrix matrix = new Matrix();
                matrix.preScale(-1.0f, 1.0f);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                drag_image_f.setImageBitmap(bitmap);
                if (drag_item_image.getDrawable() != null) {
                    Drawable d2 = drag_item_image.getDrawable();
                    Bitmap bitmap2 = ((BitmapDrawable) d2).getBitmap();
                    Matrix matrix2 = new Matrix();
                    matrix2.preScale(-1.0f, 1.0f);
                    bitmap = Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), bitmap2.getHeight(), matrix2, true);
                    drag_item_image.setImageBitmap(bitmap);
                }


                if (isInitialStateNotMirror) {
                    isInitialStateNotMirror = false;
                } else {
                    isInitialStateNotMirror = true;
                }

            }


        }


    }

}
