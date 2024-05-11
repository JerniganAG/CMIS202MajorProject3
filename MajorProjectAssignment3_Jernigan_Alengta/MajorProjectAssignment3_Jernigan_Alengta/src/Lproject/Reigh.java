// **********************************************
// Title:  Library Of Reigh
// Author: Alengta Jernigan
// Course Section: CMIS202-ONL1 (Seidel) Spring 2024
// File: Reigh.java
// Description: Main class-Main screen
//Small collection of books sort by author's last name classify genere or number of pages(main screen)
// **********************************************
//main screen javfx with image link string to template

package Lproject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

//main class 
public class Reigh extends Application{
	
	private static DisplayBL<String> outline = new DisplayBL<String>();//static reference to Display.java
	public static Stage stage = new Stage();//stage for event handling
	
	//main class
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		BorderPane bPane = new BorderPane();//Create border pane
		
		//place nodes
		bPane.setCenter(outline.DisplayMainLabel("Digital Library", "black"));
		bPane.setBottom(eventBox());
                bPane.setTop(ImageBox());
                
              
                        
		//create scene and set it to the stage
		Scene scene = new Scene(bPane);
		primaryStage.setResizable(false);
		primaryStage.setHeight(800);
		primaryStage.setWidth(800);
		primaryStage.setTitle("Library of Reigh");
		primaryStage.setScene(scene);
		stage = primaryStage;
		primaryStage.show();
	}
	
	//create buttons for UI
	private static HBox eventBox() throws FileNotFoundException {
		//set up hbox
		HBox hBox = new HBox(15);
		hBox.setPadding(new Insets(15, 15, 15, 15));
		hBox.setStyle("-fx-background-color: black");
		EventMain eventManager = new EventMain();
		
		//create buttons
		Button btView = outline.DisplayButton("Library");
		btView.setOnAction(eventManager.buttonView);
		
		//add a new book Window
		Button btAdd = outline.DisplayButton("New Book ");
		btAdd.setOnAction(eventManager.buttonAdd);
		
		//Open the description
		Button btHelp = outline.DisplayButton("Help");
		btHelp.setOnAction(eventManager.buttonHelp);
		
		//Quit the application
		Button btQuit = outline.DisplayButton("Quit");
		btQuit.setOnAction(eventManager.buttonQuit);
		
		//add buttons
		hBox.getChildren().addAll(btView, btAdd, btHelp, btQuit);
		
		//set alignment
		hBox.setAlignment(Pos.CENTER);
		
		return hBox;
	}
        //create IMAGE for UI
	private static HBox ImageBox() throws FileNotFoundException{
          HBox pane = new HBox();
		Image img = new Image(new FileInputStream("src\\MainImage.png"));
                ImageView imageView = new ImageView();
                imageView.setImage(img);
                imageView.setFitHeight(500);
                imageView.setFitWidth(500);
                pane.getChildren().add(imageView);
            return pane;
            
}
	
	//method for other classes to build the main menu
	public static Scene buildStartScene() throws FileNotFoundException {
		BorderPane bPane = new BorderPane();//Create border pane
		
		//place nodes
		bPane.setCenter(outline.DisplayMainLabel("Digital Library", ""));
		bPane.setBottom(eventBox());	
		 bPane.setTop(ImageBox());
                 
		Scene startScene = new Scene(bPane);
		
		return startScene;
	}
	
	//launch class
	public static void main(String[] args) {
		Application.launch(args);
	}
}