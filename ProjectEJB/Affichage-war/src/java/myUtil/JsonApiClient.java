/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myUtil;

/**
 *
 * @author RazOnTheFloor
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Date;
import warEntity.Plainte;

public class JsonApiClient {

    Gson gson;

    public JsonApiClient() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializerSerializer());
        this.gson = gsonBuilder.create();

    }

    public String getJsonDataFromUrl(String url) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet getRequest = new HttpGet(url);

        HttpResponse httpResponse = httpClient.execute(getRequest);

        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            String jsonResult = EntityUtils.toString(httpResponse.getEntity());
            return jsonResult;
        } else {
            throw new IOException("Erreur HTTP : " + httpResponse.getStatusLine().getStatusCode());
        }
    }

    public Plainte getPlainte(String url) throws IOException {
        return this.gson.fromJson(this.getJsonDataFromUrl(url), Plainte.class);
    }

    public Plainte[] getPlainteList(String url) throws IOException {
        return gson.fromJson(getJsonDataFromUrl(url), Plainte[].class);
    }

}
