package net.ivanvega.peticioneshttpconvolley;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import java.lang.reflect.Type;
import java.util.List;

public class AdaptadorHeroes extends RecyclerView.Adapter<AdaptadorHeroes.ViewHolder> {
    LayoutInflater inflater;
    RequestQueue  requestQueue;

    public AdaptadorHeroes(Context context){
        this.inflater=LayoutInflater.from(context);
        requestQueue = Volley.newRequestQueue(context);
    }

    @NonNull
    @Override
    public AdaptadorHeroes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.heroes_lista,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorHeroes.ViewHolder holder, int position) {
        String urlheroes = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";
        JsonRequest jsonRequest  = new JsonObjectRequest(
                urlheroes,
                null,
                response -> {
                    Gson gson = new Gson();
                    try {
                        JSONArray jsonArray  = response.getJSONArray("heroes");
                        Type listType =
                                new TypeToken<List<Heroe>>() {}.getType();
                        List<Heroe> list = gson.fromJson(String.valueOf(jsonArray),
                                listType);
                        holder.tv.setText(list.get(position).getName());
                        ImageRequest imageRequest = new ImageRequest(
                                list.get(position).getImageurl(), // Image URL
                                response2 -> holder.img.setImageBitmap(response2),
                                0, // Image width
                                0, // Image height
                                ImageView.ScaleType.CENTER, // Image scale type
                                Bitmap.Config.RGB_565, //Image decode configuration
                                error -> error.printStackTrace());
                        requestQueue.add(imageRequest);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> error.printStackTrace()
        );
        requestQueue.add(jsonRequest);

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tvheroe);
            img = itemView.findViewById(R.id.imgheroe);
        }
    }
}
