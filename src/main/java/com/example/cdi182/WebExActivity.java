package com.example.cdi182;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cdi182.utils.OkHttpUtils;

public class WebExActivity extends AppCompatActivity {

    private TextView tv;
    private EditText et;
    private WebView wv;

    //outils
    MonAT monAt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_ex);
        tv = findViewById(R.id.tv);
        et = findViewById(R.id.et);
        wv = findViewById(R.id.wv);

        //réglages
        wv.setWebViewClient(new WebViewClient());
        wv.getSettings().setJavaScriptEnabled(true);
    }

    public void onClick(View view) {
        //je donne l'url à la webView
        wv.loadUrl(et.getText().toString());

        //Lancer l'AsyncTask
        if (monAt == null || monAt.getStatus() == AsyncTask.Status.FINISHED) {
            monAt = new MonAT();
            monAt.execute();
        }

    }

    public class MonAT extends AsyncTask {

        String resultat;
        Exception exception;


        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                resultat = OkHttpUtils.sendGetOkHttpRequest(et.getText().toString());
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

            if (exception != null) {
                Toast.makeText(WebExActivity.this, "Une erreur est survenue : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
            else {
                tv.setText(resultat);
            }
        }
    }
}
