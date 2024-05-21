package org.august;

import java.util.Scanner;

public class HttpImageStatusCli {

    private final HttpStatusChecker checker;
    private final HttpStatusImageDownloader downloader;

    public HttpImageStatusCli() {
        this.checker = new HttpStatusChecker();
        this.downloader = new HttpStatusImageDownloader();
    }

    public void askStatus() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter HTTP status code: ");
            String input = scanner.nextLine();

            try {
                int code = Integer.parseInt(input);
                String imageUrl = checker.getStatusImage(code);
                downloader.downloadStatusImage(code);
                System.out.println("Image downloaded successfully.");
            } catch (HttpStatusException e) {
                System.out.println("There is no image for HTTP status " + input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
