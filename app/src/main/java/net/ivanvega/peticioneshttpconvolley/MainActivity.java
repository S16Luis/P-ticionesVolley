package net.ivanvega.peticioneshttpconvolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    AdaptadorHeroes adaptadorheroes;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.reciclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        adaptadorheroes = new AdaptadorHeroes(this);
        recyclerView.setAdapter(adaptadorheroes);
    }
}

//Códigos para otras peticiones

/*String url ="http://www.google.com";
        StringRequest stringRequest = new StringRequest(url,
                response -> {
                    Log.d("VOLL", "Exito \n" + response);},
                error -> {
                    Log.d("VOLL","Error \n" + error.toString());}
                );*/


                   /*try {
                        JSONArray jsonArray2  = response.getJSONArray("heroes");
                        String job="Exito: ";

                        for(int i=0; i<jsonArray2.length();i++)
                        {
                            JSONObject jsonObject = jsonArray2.getJSONObject(i);
                            job +=
                                    jsonObject.getString("name");
                            job += "\n" + jsonObject.getString("imageurl")+" ";
                            Log.d("VOLL",  job);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/