package com.example.cdi182;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cdi182.adapter.CityAdapter;
import com.example.cdi182.model.bean.CityBean;
import com.example.cdi182.model.ws.WSUtils;

import java.util.ArrayList;

public class CodePostalActivity extends AppCompatActivity {

    //Composants graphiques
    private EditText et;
    private ProgressBar pb;
    private RecyclerView rv;


    //Données
    private ArrayList<CityBean> cities;

    //Outils
    MonAT monAT;
    CityAdapter cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_postal);
        et = findViewById(R.id.et);
        pb = findViewById(R.id.pb);
        rv = findViewById(R.id.rv);

        pb.setVisibility(View.GONE);

        cities = new ArrayList<>();
        cityAdapter = new CityAdapter(cities);
        rv.setAdapter(cityAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    public void onClick(View view) {

        //Si l'AT est nulle ou si elle est terminée, j'en relance une autre
        if (monAT == null || monAT.getStatus() == AsyncTask.Status.FINISHED) {
            monAT = new MonAT();
            monAT.execute();
        }
        else {
            Toast.makeText(this, "Tache déjà en cours", Toast.LENGTH_SHORT).show();
        }

    }



       /* -------------------
    // AsyncTask
    --------------------- */

    public class MonAT extends AsyncTask {

        ArrayList<CityBean> resultat;
        Exception exception;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                //Je fais la requete dans un thread séparé.
                resultat = WSUtils.getCities(et.getText().toString());
            }
            catch (Exception e) {
                e.printStackTrace();
                exception = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            pb.setVisibility(View.GONE);


            if (exception != null) {
                Toast.makeText(CodePostalActivity.this, "Une erreur est survenue : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
            else {

                //Ne surtout pas faire ca :
                //cities = resultat;

                //Je copie les adresses des données d'une liste à une autre
                cities.clear();
                cities.addAll(resultat);
                cityAdapter.notifyDataSetChanged();
            }
        }
    }
}
