package view;
	
import java.io.IOException;

import interpeter.Lexer;
import interpeter.Parset;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Model;
import model.MyModel;
import view.WindowController;
import view_model.ViewModel;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		Model m=new MyModel(); // Model
		ViewModel vm=new ViewModel(m); // ViewModel
		FXMLLoader fxl=new FXMLLoader();
		try {
			
			BorderPane root = fxl.load(getClass().getResource("Window7.fxml").openStream());
			root.setStyle("-fx-background-image: url(\"/files/Presentation1.jpg\");"+
					"-fx-background-size: cover;");
			WindowController wc=fxl.getController(); // View
			wc.setViewModel(vm);
			
			Scene scene = new Scene(root,800,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}


	
	public static void main(String[] args) {
		launch(args);
	}
}
