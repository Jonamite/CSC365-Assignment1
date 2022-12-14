package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Home extends Application    {
	@Override
	public void start(Stage primaryStage) {
		try {
			//Program start
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("HOMEPAGE.fxml"));

						Scene scene = new Scene(root,800,400);
					
						scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						primaryStage.setScene(scene);
						primaryStage.setResizable(false);
						
						primaryStage.setMinWidth(500);
						primaryStage.setMinHeight(530);
						
						
						primaryStage.setMaxWidth(970);
						primaryStage.setMaxHeight(680);
						
						primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		launch(args);
	}
}
