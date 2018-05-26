package peticion3.com.peticion3;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PeticionVolley {

    private RequestQueue queue;
    private JSONObject json;
    private String url ;
    private String respuesta;
    private String[] res;

    public PeticionVolley(Context context){
        this.json = new JSONObject();
        this.queue = Volley.newRequestQueue(context);
        this.respuesta = "";
        this.url = "";
        this.res = new String[6];
    }

    public void limpiar_var(){
        this.respuesta = "";
        this.url = "http://192.168.0.13:8000/restaurant/api/v1.0/";
    }

    public String getRespuesta(){
        return this.respuesta;
    }

    public interface VolleyCallback{
        void onSuccessResponse(String response);
    }

    public void peticion_login(String number, String password, final VolleyCallback callback){
        limpiar_var();
        this.url = this.url+"login";
        try {
            this.json.put("number",number);
            this.json.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, json, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("###############################################################" + response.toString());
                            if (response.getString("status").equals("200")){
                                JSONObject data = response.getJSONObject("data");
                                respuesta = data.getString("_id");
                                System.out.println(respuesta);
                            }else{
                                respuesta = "Login incorrecto";
                                System.out.println(respuesta);
                            }
                        }catch (Exception e){
                            System.out.println("Error JSON");
                        }
                        callback.onSuccessResponse(respuesta);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        respuesta = "Error en la peticion";
                        System.out.println("----------------------------------------------------------------------"+respuesta);
                    }
                });
        queue.add(jsonObjectRequest);
        this.json.remove("number");
        this.json.remove("password");
        //return respuesta;
    }

    public String peticion_new_user(String number, String password){
        limpiar_var();
        this.url = this.url+"new_user";
        try {
            this.json.put("number",number);
            this.json.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, json, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("###############################################################" + response.toString());
                            if (response.getString("status").equals("200")){
                                respuesta = response.getString("data");
                                System.out.println(respuesta);
                            }else{
                                respuesta = "Error en el registro de nuevo usuario";
                                System.out.println(respuesta);
                            }
                        }catch (Exception e){
                            System.out.println("Error JSON");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        respuesta = "Error en la peticion";
                        System.out.println("----------------------------------------------------------------------"+respuesta);
                    }
                });
        queue.add(jsonObjectRequest);
        this.json.remove("number");
        this.json.remove("password");
        return respuesta;
    }

    public void peticion_tables_state(){
        limpiar_var();
        this.url = this.url+"tables_state";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("###############################################################" + response.toString());
                            if (response.getString("status").equals("200")){
                                JSONArray data = response.getJSONArray("data");
                                for(int i = 0; i < data.length(); i++){
                                    res[i] = data.getJSONObject(i).getString("available");
                                    //System.out.println(res[i]);
                                }
                                System.out.println(res[2]);
                            }else{
                                respuesta = "Error en el registro de nuevo usuario";
                                System.out.println(respuesta);
                            }
                        }catch (Exception e){
                            System.out.println("Error JSON");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        respuesta = "Error en la peticion";
                        System.out.println("----------------------------------------------------------------------"+respuesta);
                    }
                });
        queue.add(jsonObjectRequest);
        //return res;
    }

    public void peticion_orden(String id, String table, JSONObject order, float amount){
        limpiar_var();
        this.url = this.url+"";// falta agregar <--------------------------------------
        try {
            this.json.put("_id", id);
            this.json.put("table", table);
            this.json.put("order", order);
            this.json.put("amount", amount);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, json, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("###############################################################" + response.toString());

                        }catch (Exception e){
                            System.out.println("Error JSON");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        respuesta = "Error en la peticion";
                        System.out.println("----------------------------------------------------------------------"+respuesta);
                    }
                });
        queue.add(jsonObjectRequest);
        this.json.remove("_id");
        this.json.remove("table");
        this.json.remove("order");
        this.json.remove("amount");
    }

    public String get_mesa(int num_mesa){
        if (num_mesa < 7){
            return res[num_mesa-1];
        }else {
            return "Error";
        }
    }

    public void peticion_calis(final VolleyCallback callback){
        limpiar_var();
        this.url = "https://www.w3schools.com/angular/customers.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("###############################################################" + response.toString());
                        callback.onSuccessResponse(response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        respuesta = "Error en la peticion";
                        System.out.println("----------------------------------------------------------------------"+respuesta);
                    }
                });
        queue.add(jsonObjectRequest);
    }
}
