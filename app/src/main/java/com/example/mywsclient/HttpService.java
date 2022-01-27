package com.example.mywsclient;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class HttpService extends AsyncTask<Void, Void, CEP> {
    private final String cep;

    public HttpService(String cep) {
        this.cep = cep;
    }

    @Override
    protected CEP doInBackground(Void... voids) {
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL("https://viacep.com.br/ws/" + this.cep + "/json/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(5000);
            connection.connect();

            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String out = response.toString();
        return new Gson().fromJson(out, CEP.class);
    }
}
