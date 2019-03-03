package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Scribo");
        Screen primary = Screen.getPrimary();
        Scene scene = new Scene(root, primary.getVisualBounds().getWidth(), primary.getVisualBounds().getHeight());
        scene.getStylesheets().add("sample/style.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setMaximized(true);

        primaryStage.show();
        root.requestFocus();

        WebView webView = (WebView) scene.lookup("#web_view");
        System.out.println(webView);
        WebEngine webEngine = webView.getEngine();

        String html_content;

        try (BufferedReader br = new BufferedReader(new FileReader("src/main_webview/index.html"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            html_content = sb.toString();
        }

        webEngine.loadContent(html_content, "text/html");
        System.out.println(getClass().getResource("../main_webview/style.css").toString());
        webEngine.setUserStyleSheetLocation(getClass().getResource("../main_webview/style.css").toString());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
