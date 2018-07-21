package pt.pepdevils.doors.volley_custom_request;

import pt.android.volley.AuthFailureError;
import pt.android.volley.NetworkResponse;
import pt.android.volley.Request;
import pt.android.volley.Response;
import pt.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class CustomJsonArrayRequest extends Request<JSONArray> {

    private Response.Listener<JSONArray> response;
    private Map<String, String> params;


    public CustomJsonArrayRequest(int method, String url, Map<String, String> params, Response.Listener<JSONArray> response, Response.ErrorListener listener) {
        super(method, url, (Response.ErrorListener) listener);
        this.params = params;
        this.response = response;
        // TODO Auto-generated constructor stub
    }
    public CustomJsonArrayRequest(String url, Map<String, String> params, Response.Listener<JSONArray> response, Response.ErrorListener listener) {
        super(Method.GET, url, (Response.ErrorListener) listener);
        this.params = params;
        this.response = response;
        // TODO Auto-generated constructor stub
    }



    public Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> header = new HashMap<String, String>();
        header.put("apiKey", "Essa e minha API KEY: json array");

        return(header);
    }

    public Priority getPriority(){
        return(Priority.NORMAL);
    }

    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        try {
            //String js = new String(response.data, HttpHeaderParser.parseCharset(response.headers, "utf-8"));
            // JSONObject jsonResponse = new JSONObject(js);
            //jsonResponse.put("headers", new JSONObject(response.headers));

            String js = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return(Response.success(new JSONArray(js), HttpHeaderParser.parseCacheHeaders(response)));


        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void deliverResponse(JSONArray response) {
        this.response.onResponse(response);
    }

}
