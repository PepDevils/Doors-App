package pt.pepdevils.doors.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by pepdevils on 14/03/16.
 */
public class Helper_Net {

    public static NetworkInfo netInfo;
    public static ConnectivityManager connectivity;

    private static boolean existeConectividade(Activity a) {

        connectivity = (ConnectivityManager) a.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean existeInformacaoDeInternet() {

        netInfo = connectivity.getActiveNetworkInfo();

        if (netInfo != null) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean existeConexao(Activity a) {


        if (existeConectividade(a)) {


            // Se não existe nenhum tipo de conexão retorna false
            if (existeInformacaoDeInternet()) {

                int netType = netInfo.getType();

                // Verifica se a conexão é do tipo WiFi ou Mobile e
                // retorna true se estiver conectado ou false em
                // caso contrário
                if (netType != ConnectivityManager.TYPE_WIFI && netType != ConnectivityManager.TYPE_MOBILE) {

                    FecharAplicação(a);
                    return !netInfo.isConnected(); //DESCONECTADO

                } else {

                    return netInfo.isConnected(); //CONECTADO
                }

            } else {
                FecharAplicação(a);
                return false;
            }

        } else {
            FecharAplicação(a);
            return false;
        }
    }



    public static boolean FecharAplicação(Activity a) {

        Toast.makeText(a.getApplicationContext(), "Precisa de conexão á internet para usar a aplicação", Toast.LENGTH_LONG).show();

        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        a.startActivity(i);

        return false;
    }

}
