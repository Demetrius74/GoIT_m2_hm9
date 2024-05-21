package org.august;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
public class HttpStatusChecker {

    private static final String HTTP_CAT_URL = "https://http.cat/";

    public String getStatusImage(int code) throws HttpStatusException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(HTTP_CAT_URL + code + ".jpg")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.code() == 200) {
                return response.request().url().toString();
            } else {
                throw new HttpStatusException("Image not found for status code " + code);
            }
        } catch (IOException e) {
            throw new HttpStatusException("Error fetching image: " + e.getMessage());
        }
    }
}
