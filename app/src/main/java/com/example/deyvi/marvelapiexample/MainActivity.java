package com.example.deyvi.marvelapiexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deyvi.marvelapiexample.MarvelAPI.Constants;
import com.example.deyvi.marvelapiexample.MarvelAPI.RestApiAdapter;
import com.example.deyvi.marvelapiexample.MarvelAPI.Service;
import com.example.deyvi.marvelapiexample.Model.Comic;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    public TextView textView;
    public Button button;

    public ArrayList<Comic> comics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         button     = (Button) findViewById(R.id.button);
         textView   = (TextView) findViewById(R.id.tv);
         comics = new ArrayList<>();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Service service = restApiAdapter.getComicService();
        retrofit2.Call<JsonObject> call  = service.getDataComics(Constants.APIKEY,Constants.TS,Constants.HASH);

        call.enqueue(new retrofit2.Callback<JsonObject>() {
            @Override
            public void onResponse(retrofit2.Call<JsonObject> call, retrofit2.Response<JsonObject> response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject("data").toString());
                    JSONArray jsonArray     = jsonObject.getJSONArray("results");
                    parseComic(jsonArray);
                    textView.setText(comics.get(0).getImageComic());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<JsonObject> call, Throwable t) {}
        });



    }


    /*
    * Método que recive un JSONArray cómic y parsea cada elemnto a un objeto de tipo comic
    * */
    public void parseComic(JSONArray jsonArray) throws JSONException {
        for (int i = 0 ; i< jsonArray.length(); i++){
            JSONObject comic = jsonArray.getJSONObject(i);
            String title                = comic.getString("title");
            String variantDescription   = comic.getString("variantDescription");
            String description = "";

            if(comic.isNull("description")){
                description = "Description not available";
            }else{
                description = comic.getString("description");
            }
            JSONArray image         = comic.getJSONArray("images");

            String path      = "";
            String extension = "";

            if(image.length() !=0 ){
                JSONObject objectImage = image.getJSONObject(0);
                path         = objectImage.getString("path");
                extension   = objectImage.getString( "extension");
            }else{
                path      = "Image";
                extension = "not available";
            }

            comics.add(new Comic(title,variantDescription,description,path + "." + extension));
        }
    }




}
