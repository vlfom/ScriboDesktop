package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("../main_webview/img/ScriboIcon256.png")));

        primaryStage.show();
        root.requestFocus();

        setWebContent(scene, "#content_view", "src/main_webview/index.html", "../main_webview/style.css",
                new String[]{"src/main_webview/jquery.js", "src/main_webview/script.js"});
        setWebContent(scene, "#list_view", "src/listview_webview/index.html", "../listview_webview/style.css", null);
        setWebContent(scene, "#menu_view", "src/menu_webview/index.html", "../menu_webview/style.css", null);
    }

    private String readFromFile(String fileLocation) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        }
    }

    private void setWebContent(Scene scene, String webViewId, String htmlLocation, String cssLocation, String[] jsLocation) throws IOException {
        WebView webView = (WebView) scene.lookup(webViewId);
        WebEngine webEngine = webView.getEngine();

        String html_content = readFromFile(htmlLocation);
        webEngine.loadContent(html_content, "text/html");

        webEngine.setUserStyleSheetLocation(getClass().getResource(cssLocation).toString());

        if (jsLocation != null) {
            for (String jsFile : jsLocation) {
                String js_content = readFromFile(jsFile);
                webEngine.executeScript(js_content);
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
