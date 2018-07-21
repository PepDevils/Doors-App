package pt.pepdevils.doors.util;

import android.app.Activity;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by pepdevils on 05/08/16.
 */
public class Helper_Validator {
    private static String TAG = "Helper Validator";


    public static boolean NullView(Activity a, View pView) {
        boolean flag = false;

        if (pView instanceof EditText) {
            EditText edText = (EditText) pView;
            Editable text = edText.getText();
            if (text != null) {
                String strText = text.toString();
                if (!TextUtils.isEmpty(strText)) {
                    flag = true;
                } else {
                    Log.d(TAG, "NullView: empty");

                    edText.setFocusable(true);
                    edText.requestFocus();

                }
            } else {
                Log.d(TAG, "NullView: text null");
                edText.setFocusable(true);
                edText.requestFocus();
            }


        } else {
            Log.d(TAG, "NullView: " + pView.toString());

        }

        return flag;
    }

    public static boolean containsDigit(String s) {
        boolean containsDigit = false;

        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (containsDigit = Character.isDigit(c)) {
                    containsDigit = true;
                    break;
                }
            }
        }

        return containsDigit;
    }

    public static boolean NameValidator(Activity a, EditText editText) {

        boolean flag = false;

        String text = editText.getText().toString();
        int max_limit = 50;
        int size = text.length();


        if (!containsDigit(text) && size != 0 && size < max_limit) {
            flag = true;
        }


        return flag;
    }

    public static boolean EmailValidator(EditText editText) {

        String txtEmail = editText.getText().toString();

        if (android.util.Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches()) {
            return true;
        }

        return false;
    }

    public static Date DateConverter(EditText editData) {
        //http://pt.stackoverflow.com/questions/31328/validar-data-entrada-pelo-edittext
        //VARIAVEL DA STRING COM O INFO DO EDITTEXT PARA DATA
        String data = editData.getText().toString();
        //INICIALIZAÇAO DA DATA
        Date date = null;

        //VARIAVEL QUE DEFINE O TIPO DE DATA
        String pattern = "dd/MM/yyyy";
        // COLOCAR O SIMPLEDATEFORMATE COM O TIPO NECESSARIO
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        // Configure o SimpleDateFormat no onCreate ou onCreateView

        //A flag lenient sendo true, faz com que o SimpleDateFormat use uma heurística para corrigir dados "errados".
        // Com lenient true, a data 29/02/2014 seria convertida para 01/03/2014. Usando lenient como false, ele gera um erro de parsing.
        sdf.setLenient(false);

        try {
            date = sdf.parse(data);
            // Data formatada corretamente
            return date;
        } catch (ParseException e) {
            // Erro de parsing!!
            e.printStackTrace();
            return null;
        }

        //return date;
    }

    public static boolean TeleValidator(EditText editText) {

        String phone = editText.getText().toString();
        boolean flag = android.util.Patterns.PHONE.matcher(phone).matches();
        return flag;
    }

    public static boolean DateValidator(EditText et_entrega) {

        Date date = DateConverter(et_entrega);

        if (date != null) {
            Date actual_date = Calendar.getInstance().getTime();


            if (date.after(actual_date)) {
                return true;
            }
            //return true;
        }




        return false;
    }
}
