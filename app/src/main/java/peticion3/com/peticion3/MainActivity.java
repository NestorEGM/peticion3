package peticion3.com.peticion3;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView mostrar;
    String[] res = new String[6];
    String respuesta_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mostrar = (TextView) findViewById(R.id.mostrar);

        PeticionVolley p = new PeticionVolley(MainActivity.this);
        //p.peticion_login("449105310", "hola");
        //p.peticion_new_user("5", "password1");
        //p.peticion_tables_state();
        p.peticion_login("449105310", "hola", new PeticionVolley.VolleyCallback() {
            @Override
            public void onSuccessResponse(String response) {
                respuesta_id = new String(response);
                mostrar.setText(respuesta_id);
            }
        });


        /*p.peticion_calis(new PeticionVolley.VolleyCallback() {
            @Override
            public void onSuccessResponse(String response) {
                System.out.println("######################################################");
                System.out.println("id: "+response);
            }
        });*/
    }

}
