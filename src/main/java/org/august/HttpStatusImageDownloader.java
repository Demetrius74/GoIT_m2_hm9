package org.august;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.FileOutputStream;
import java.io.IOException;
public class HttpStatusImageDownloader {

    private final HttpStatusChecker checker;

    public HttpStatusImageDownloader() {
        this.checker = new HttpStatusChecker();
    }

    public void downloadStatusImage(int code) throws HttpStatusException {
        String imageUrl = checker.getStatusImage(code);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(imageUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                try (FileOutputStream fos = new FileOutputStream(code + ".jpg")) {
                    fos.write(response.body().bytes());
                }
            } else {
                throw new HttpStatusException("Image not found for status code " + code);
            }
        } catch (IOException e) {
            throw new HttpStatusException("Error downloading image: " + e.getMessage());
        }
    }
}