package pt.pepdevils.doors.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import org.json.JSONException;
import org.json.JSONObject;
import pt.pepdevils.doors.R;
import pt.pepdevils.doors.util.Helper;
import pt.pepdevils.doors.util.Helper_Api;
import pt.pepdevils.doors.util.Helper_Validator;
import pt.pepdevils.doors.view.PepeDialogBuilder;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static android.view.KeyEvent.KEYCODE_DEL;

public class OrcamentoActivity extends AppCompatActivity {

    private ImageView img_product, img_produto_item;
    private TextView title, morada, tel, tel_num, fax, fax_num, email, title_orc;
    private EditText et_nome, et_localidade, et_email, et_telefone, et_entrega, et_mensage;
    private Button bt_enviar_orc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width_p = metrics.widthPixels;


        float density = metrics.density;
        int width_total = (int) (width_p / density);


      /*  if (width_total <= 320) {*/
            setContentView(R.layout.activity_orcamento2);
       /* } else {
            setContentView(R.layout.activity_orcamento);
        }*/


        UI();
        SetFont();
        InsertImageProduct();
        AbrirSemTeclado();
        TextReajust();
        ButtonFunction();

        DownLoadInfo();


    }

    private void DownLoadInfo() {
        SharedPreferences preferences = this.getSharedPreferences("SHARED_PREFS_FILE", Context.MODE_PRIVATE);
        String res_info = preferences.getString("res_info", "DEFAULT");
        if (!res_info.equalsIgnoreCase("DEFAULT")) {
            try {
                JSONObject obj = new JSONObject(res_info);
                String morada_ = obj.getString("morada");
                String tel_ = obj.getString("tel");
                String fax_ = obj.getString("fax");
                String email_ = obj.getString("email");
                morada.setText(morada_);
                tel_num.setText(tel_);
                fax_num.setText(fax_);
                email.setText(email_);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void TextReajust() {
        String m = morada.getText().toString();
        m = m.replace("[p]", "\n");
        morada.setText(m);
    }

    private void UI() {
        img_product = (ImageView) findViewById(R.id.img_product);
        img_produto_item = (ImageView) findViewById(R.id.img_produto_item);

        title = (TextView) findViewById(R.id.title);
        morada = (TextView) findViewById(R.id.morada);
        tel = (TextView) findViewById(R.id.tel);
        tel_num = (TextView) findViewById(R.id.tel_num);
        fax = (TextView) findViewById(R.id.fax);
        fax_num = (TextView) findViewById(R.id.fax_num);
        email = (TextView) findViewById(R.id.email);
        title_orc = (TextView) findViewById(R.id.title_orc);

        et_nome = (EditText) findViewById(R.id.et_nome);
        et_localidade = (EditText) findViewById(R.id.et_localidade);
        et_email = (EditText) findViewById(R.id.et_email);
        et_telefone = (EditText) findViewById(R.id.et_telefone);
        et_entrega = (EditText) findViewById(R.id.et_entrega);
        et_mensage = (EditText) findViewById(R.id.et_mensage);

        bt_enviar_orc = (Button) findViewById(R.id.bt_enviar_orc);


        et_entrega.setOnKeyListener(new EditText.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.d("OnKeyListener", keyCode + " character(code) to send" + event);
                if(keyCode == KEYCODE_DEL){
                    et_entrega.setText("");
                }
                return false;
            }
        });

        et_entrega.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() != 3 || s.length() != 6) {
                    if (s.length() == 2) {
                        et_entrega.getText().append("/");
                    } else if (s.length() == 5) {
                        et_entrega.getText().append("/");
                    } else if (s.length() == 10) {
                        //et_mensage.requestFocus();
                        et_mensage.clearFocus();
                    }else if (s.length() > 10) {
                        et_entrega.getText().delete(s.length()-1, s.length());
                        //et_mensage.requestFocus();
                        et_mensage.clearFocus();

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void SetFont() {

        ArrayList<TextView> textViewArrayList = new ArrayList<>();
        textViewArrayList.add(title);
        textViewArrayList.add(title_orc);
        textViewArrayList.add(bt_enviar_orc);
        textViewArrayList.add(tel);
        textViewArrayList.add(fax);
        Helper.AplicarFontTextual(OrcamentoActivity.this, "Titillium-Bold", textViewArrayList);

        textViewArrayList = new ArrayList<>();
        textViewArrayList.add(morada);
        textViewArrayList.add(tel_num);
        textViewArrayList.add(fax_num);
        textViewArrayList.add(email);
        textViewArrayList.add(et_nome);
        textViewArrayList.add(et_localidade);
        textViewArrayList.add(et_email);
        textViewArrayList.add(et_telefone);
        textViewArrayList.add(et_entrega);
        textViewArrayList.add(et_mensage);
        Helper.AplicarFontTextual(OrcamentoActivity.this, "Titillium-Regular", textViewArrayList);

    }

    private void InsertImageProduct() {

        if (getIntent() != null) {

/*            Bitmap bmp = null;
            byte[] byteArray = getIntent().getByteArrayExtra("image_byte_array");
            if(byteArray == null){
                //se não foi passado por intente então está guardado em cache
                img_product.setImageBitmap(MainActivity.getBitmapFromMemCache("door"));
            }else{
                bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                img_product.setImageBitmap(bmp);
            }


            byteArray = getIntent().getByteArrayExtra("image_byte_array2");
            if (byteArray != null) {
                bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                img_produto_item.setImageBitmap(bmp);
            }*/


            String tag_product = getIntent().getStringExtra("tag_link_product");
            String tag_item_ = getIntent().getStringExtra("tag_link_item");

            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.init(ImageLoaderConfiguration.createDefault(this));
            Helper.DoorImageLoader(this, tag_product, img_product, false, false, imageLoader);
            if (tag_item_ != null) {
                Helper.DoorImageLoader(this, tag_item_, img_produto_item, false, false, imageLoader);
            }

            String cor = getIntent().getStringExtra("cor");
            int color = GetIntColorByRAl(cor);
            color = Helper.adjustAlpha(color, 0.5f);
            img_product.setColorFilter(color);
            // img_produto_item.setColorFilter(color);

        } else {
            this.finish();
        }
    }

    private int GetIntColorByRAl(String cor) {
        int res = 0;
        cor = cor.replaceFirst(" ", "");
        try {
            JSONObject aux = (JSONObject) MainActivity.COLORS_JSON.get(cor);
            res = Integer.parseInt((String) aux.get("android_int_color"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }

    private void ButtonFunction() {
        bt_enviar_orc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidateFields()[0] && ValidateFields()[1]) {
                    SendEmail();
                }
            }
        });
    }

    private void SendEmail() {

        Bundle b = getIntent().getExtras();

        if (b != null) {

            String linha = b.get("linha").toString();
            String modelo = b.get("modelo").toString();
            String cor = b.get("cor").toString();
            String acabamentos = b.get("acabamentos").toString();

            String informação_porta = "Orçamento para a porta com: \nlinha:" + linha + "\nmodelo:" + modelo + "\ncor:" + cor + "\nacabamentos:" + acabamentos + "\n \n";

            String informação_pessoal = "Informação do cliente: \nnome: " + et_nome.getText() + "\nlocalidade: " + et_localidade.getText() + "\nemail: " + et_email.getText()
                    + "\ncontacto telefónico: " + et_telefone.getText() + "\ndata de entrega: " + et_entrega.getText();

            if (!et_mensage.getText().toString().equalsIgnoreCase("")) {
                informação_pessoal += "\nmais informações: " + et_mensage.getText() + "\n \n";
            }

            String email = "Orçamento enviado pela aplicação móvel \n \n" + informação_porta + informação_pessoal;


            Calendar now = Calendar.getInstance();
            int mes_int = now.get(Calendar.MONTH) + 1;
            int ano_int = now.get(Calendar.YEAR);
            String mes = MakeMesByInt(mes_int);

            String[] list_adress = new String[]{"pepe@pepdevils.xx", "avelino@pepdevils.xx"};
            String subjet = "Doors - Orçamento - " + mes + " " + ano_int;

            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("message/rfc822");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, list_adress);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subjet);
            emailIntent.putExtra(Intent.EXTRA_TEXT, email);
            emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);


            try {
                Intent chooser = Intent.createChooser(emailIntent, "Enviar por:");
                List<LabeledIntent> intentList = new ArrayList<>();
                PackageManager pm = getPackageManager();
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
                for (int j = 0; j < resInfo.size(); j++) {
                    ResolveInfo ri = resInfo.get(j);
                    String packageName = ri.activityInfo.packageName;

                    //serviço obrigatorio
                    if (packageName.contains("android.email")) {
                        emailIntent.setPackage(packageName);
                    }
                    //serviços opcionais "EXTRA_INITIAL_INTENTS"
                    //GMAIL
                    //BLUEMAIL
                    else if (packageName.contains("android.gm") || packageName.contains("me.bluemail.mail") || packageName.contains("com.microsoft.office.outlook")) {
                        Intent intent = new Intent();
                        intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
                        intent.setType("message/rfc822");
                        intent.putExtra(Intent.EXTRA_EMAIL, list_adress);
                        intent.putExtra(Intent.EXTRA_SUBJECT, subjet);
                        intent.putExtra(Intent.EXTRA_TEXT, email);
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        intent.setAction(Intent.ACTION_SEND);

 /*                       //GMAIL
                        if(packageName.contains("android.gm")) {


                        }
                        //BLUEMAIL
                        else if (packageName.contains("me.bluemail.mail")){
                            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"pepe@pepdevils.xx", "avelino@pepdevils.xx"});
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Doors - Orçamento - " + mes + " " + ano_int);
                            intent.setType("message/rfc822");
                        }*/

                        //OUTLOOK
/*                        if (packageName.contains("com.microsoft.office.outlook")) {
                            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"pepe@pepdevils.xx", "avelino@pepdevils.xx"});
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Doors - Orçamento - " + mes + " " + ano_int);
                            intent.setType("message/rfc822");
                        }*/

                        intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
                    }


                }


                LabeledIntent[] extraIntents = intentList.toArray(new LabeledIntent[intentList.size()]);
                chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
                startActivity(chooser);
            } catch (Exception ex) {
                Toast.makeText(this, "Não tem aplicações instaladas para enviar email ", Toast.LENGTH_SHORT).show();
            }


        }


    }


    private String MakeMesByInt(int mes_int) {
        String mes = "";
        switch (mes_int) {
            case 1:
                mes = "janeiro";
                break;
            case 2:
                mes = "fevereiro";
                break;
            case 3:
                mes = "março";
                break;
            case 4:
                mes = "abril";
                break;
            case 5:
                mes = "maio";
                break;
            case 6:
                mes = "junho";
                break;
            case 7:
                mes = "julho";
                break;
            case 8:
                mes = "agosto";
                break;
            case 9:
                mes = "setembro";
                break;
            case 10:
                mes = "outubro";
                break;
            case 11:
                mes = "novembro";
                break;
            case 12:
                mes = "dezembro";
                break;
        }

        return mes;
    }

    private boolean[] ValidateFields() {

        boolean[] flag = {false, false};

        Helper_Validator.NullView(this, et_nome);
        Helper_Validator.NullView(this, et_localidade);
        Helper_Validator.NullView(this, et_email);
        Helper_Validator.NullView(this, et_telefone);
        Helper_Validator.NullView(this, et_entrega);
        Helper_Validator.NullView(this, et_mensage);


        if (Helper_Validator.NullView(this, et_nome) && Helper_Validator.NullView(this, et_entrega)) {
            if (Helper_Validator.NullView(this, et_telefone) || Helper_Validator.NullView(this, et_email)) {

                if (Helper_Validator.NullView(this, et_telefone)) {


                    if (Helper_Validator.TeleValidator(et_telefone)) {
                        flag[0] = true;
                        flag[1] = true;
                    } else {
                        et_telefone.setText("");
                        Toast.makeText(this, "Verifique o número de telemóvel", Toast.LENGTH_SHORT).show();
                        flag[0] = false;
                    }

                } else if (Helper_Validator.NullView(this, et_email)) {


                    if (Helper_Validator.EmailValidator(et_email)) {
                        flag[0] = true;
                    } else {
                        et_email.setText("");
                        Toast.makeText(this, "Verifique o email", Toast.LENGTH_SHORT).show();
                        flag[0] = false;
                    }


                }


            } else {
                Toast.makeText(this, "Obrigatório telefone ou email", Toast.LENGTH_SHORT).show();
            }


            if (Helper_Validator.DateValidator(et_entrega)) {
                flag[0] = true;
            } else {
                et_entrega.setText("");
                Toast.makeText(this, "Verifique a data", Toast.LENGTH_SHORT).show();
                flag[0] = false;
            }


            if (Helper_Validator.NameValidator(this, et_nome)) {
                flag[0] = true;
            } else {
                et_nome.setText("");
                Toast.makeText(this, "O nome tem no máximo 50 caracters, sem números", Toast.LENGTH_SHORT).show();
                flag[0] = false;
            }


        } else {
            Toast.makeText(this, "Campos por preencher", Toast.LENGTH_SHORT).show();
        }


        return flag;

    }

    private void AbrirSemTeclado() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


}
