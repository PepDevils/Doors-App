package pt.pepdevils.doors.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import pt.android.volley.Request;
import pt.android.volley.RequestQueue;
import pt.android.volley.Response;
import pt.android.volley.VolleyError;
import pt.android.volley.toolbox.Volley;
import pt.pepdevils.doors.volley_custom_request.CustomJsonArrayRequest;
import pt.pepdevils.doors.volley_custom_request.CustomJsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by pepdevils on 12/07/16.
 */
public class Helper_Api {

    public static String baseURL = "http://xxxx.xxx/xxx/mobile_api.php";

    public static String resposta_a_internet = "null";


    //METODOS
    public static void FirstCAll(final Activity a) {

        RequestQueue rq;
        Map<String, String> params;
        rq = Volley.newRequestQueue(a);

        params = new HashMap<>();
        params.put("method", "con");

        CustomJsonObjectRequest request = new CustomJsonObjectRequest(Request.Method.POST, baseURL, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String response_json = response.getString("con");
                            if (response_json.equals("yes")) {
                                resposta_a_internet = "connected";
                                new SplashScreen(a).execute();

                            } else {

                                Helper_Net.FecharAplicação(a);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Log.i("Script", "ERRO: " + error);
                        // Toast.makeText(a, "Erro de coneção com internet", Toast.LENGTH_LONG).show();
                        new CountDownTimer(5, 1) {

                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                Helper_Net.FecharAplicação(a);
                            }
                        }.start();
                    }
                }) {


            @Override
            public Priority getPriority() {
                return super.getPriority();
            }
        };

        request.setTag("tag");
        rq.add(request);
    }

    public static void SecondCAll(final Activity a) {

        RequestQueue rq;
        Map<String, String> params;

        rq = Volley.newRequestQueue(a);

        params = new HashMap<>();
        params.put("method", "sampli");

        CustomJsonObjectRequest request = new CustomJsonObjectRequest(Request.Method.POST, baseURL, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Script", "SUCCESS: " + response);

                        try {
                            JSONObject json_object_students = response.getJSONObject("students");

                            String firstName = json_object_students.getString("firstname");
                            String lastName = json_object_students.getString("lastname");
                            String age = json_object_students.getString("age");

                            Toast.makeText(a, "Nome: " + firstName + " " + lastName + " \n idade: " + age, Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(a, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        request.setTag("tag");
        rq.add(request);


    }

    public static Elements GetAllImageDoors(final Activity a) {

        final Elements element = new Elements();
        final String url_base = "http://xxxxxx.xxxx/image/";

        RequestQueue rq;
        Map<String, String> params;

        rq = Volley.newRequestQueue(a);

        params = new HashMap<>();
        params.put("method", "all_doors");

        CustomJsonArrayRequest request = new CustomJsonArrayRequest(Request.Method.POST, baseURL, params,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("Script", "SUCCESS: " + response);

                        ArrayList<String> listdata = new ArrayList<>();

                        if (response != null) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    String aux = (String) response.getJSONObject(i).get("image");
                                    aux = url_base + aux;
                                    listdata.add(aux);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }


                        element.setAll_Image_Door(listdata);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(a, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        request.setTag("tag");
        rq.add(request);


        return element;
    }

    private static ArrayList<String> listcolors = new ArrayList<>();

    public static void GetColors(final Activity a) {

        RequestQueue rq;
        Map<String, String> params;

        rq = Volley.newRequestQueue(a);

        params = new HashMap<>();
        params.put("method", "ral_colors");

        CustomJsonArrayRequest request = new CustomJsonArrayRequest(Request.Method.POST, baseURL, params,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("Script", "SUCCESS: " + response);

                        //ArrayList<String> listdata = new ArrayList<>();

                        String res = null;
                        JSONObject m_jobj = null;
                        try {
                            res = (String) response.get(0);
                            m_jobj = new JSONObject(res);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Iterator x = m_jobj.keys();
                        JSONArray jsonArray = new JSONArray();

                        while (x.hasNext()) {
                            String key = (String) x.next();
                            try {
                                jsonArray.put(m_jobj.get(key));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        if (m_jobj != null) {
                            int count = m_jobj.length();
                            for (int i = 0; i < count; i++) {
                                try {
                                    JSONObject color = (JSONObject) jsonArray.get(i);
                                    String color_android = (String) color.get("android_int_color");
                                    listcolors.add(color_android);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            SharedPreferences prefs = a.getSharedPreferences("SHARED_PREFS_FILE", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();

                            try {
                                editor.putString("ral_colors", ObjectSerializer.serialize(listcolors));
                                editor.apply();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(a, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        request.setTag("tag");
        rq.add(request);

        //return listcolors;
    }


    public static void GetInfo(final Activity a) {

        RequestQueue rq;
        Map<String, String> params;

        rq = Volley.newRequestQueue(a);

        params = new HashMap<>();
        params.put("method", "Doors_info");

        CustomJsonArrayRequest request = new CustomJsonArrayRequest(Request.Method.POST, baseURL, params,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("Script", "SUCCESS: " + response);

                        try {
                            String res_info = response.get(0).toString();

                            SharedPreferences prefs = a.getSharedPreferences("SHARED_PREFS_FILE", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("res_info", res_info);
                            editor.apply();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(a, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        request.setTag("tag");
        rq.add(request);

    }


}
