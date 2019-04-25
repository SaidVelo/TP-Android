package com.example.cdi182.model.ws;

import android.os.SystemClock;

import com.example.cdi182.model.bean.CityBean;
import com.example.cdi182.model.bean.EleveBean;
import com.example.cdi182.model.bean.ResultBean;
import com.example.cdi182.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;

public class WSUtils {

    private final static String URL_WS = "http://www.citysearch-api.com/fr/city?login=webserviceexemple&apikey=sof940dd26cf107eabf8bf6827f87c3ca8e8d82546&cp=";


    public static EleveBean loadEleveFromWeb() throws Exception {
        SystemClock.sleep(3000); //Attente de 5 secondes

        return new EleveBean("Toto", "Tata");
    }


    public static ArrayList<CityBean> getCities(String cp) throws Exception {
        //Faire la requete
        String json = OkHttpUtils.sendGetOkHttpRequest(URL_WS + cp);
        //Parser le json avec GSON
        ResultBean resultBean = new Gson().fromJson(json, ResultBean.class);
        //verifier si erreur
        if (resultBean.getErrors() != null) {
            throw new Exception(resultBean.getErrors().getMessage());
        }
        else {
            //retourner resultat
            return resultBean.getResults();
        }
    }

}
