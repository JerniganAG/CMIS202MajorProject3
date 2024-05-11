// **********************************************
// Title:  Library Of Reigh
// Author: Alengta Jernigan
// Course Section: CMIS202-ONL1 (Seidel) Spring 2024
// File: Reigh.java
// Description: Help button Library summary
//Small collection of books sort by author's last name classify genere or number of pages
// **********************************************
package Lproject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//Description Class
public class Help {
	
	private static DisplayBL<String> outline = new DisplayBL<String>();//static reference to Display.java
	
	public static String Description() {
		//Summary of program
		String summary = "Welcome to the Library of Reigh!\n\n" + 
				"A digital collection of books sort by author's last name in ABC order " + 
				"to house a small library of books.\n" + 
				"Classify genere and number of pages in the book " + 
				"also write a small description about book.\n\n";
		
		//what does it do
		String functionality = "Library program allows users to enter " + 
				"information about their favorite books to auto sort \nthem by " + 
				"ABC order via the author's last names." + 
				"Record library \nsave to text file.\n\n";
		
		//why use the program and who should use it
		String whyUseThis = "Simple free digital library to auto sort books for Book Lovers!";
		
		//full return
		String fullSummary = summary + functionality + whyUseThis;
		
		return fullSummary;
	}
	
	public static Scene buildHelpScene() throws FileNotFoundException {
		BorderPane bPane = new BorderPane();//Create border pane
		
		//place nodes
		bPane.setCenter(outline.DisplaySmallLabel(Description()));
		bPane.setBottom(EventBox());	
		bPane.setTop(ImageBox());
		Scene helpScene = new Scene(bPane);
		
		return helpScene;
	}
	
	private static HBox EventBox() throws FileNotFoundException {
		//set up hbox
				HBox hBox = new HBox(15);
				hBox.setPadding(new Insets(15, 15, 15, 15));
				hBox.setStyle("-fx-background-color: black");
				EventMain eventManager = new EventMain();
				
				//Back to main menu
				Button btHome = outline.DisplayButton("Home");
				btHome.setOnAction(eventManager.buttonHome);

				//Quit the application
				Button btQuit = outline.DisplayButton("Quit");
				btQuit.setOnAction(eventManager.buttonQuit);
				
				//add buttons
				hBox.getChildren().addAll(btHome, btQuit);
				
				//set alignment
				hBox.setAlignment(Pos.CENTER);
				
				return hBox;
	}
        //create IMAGE for UI
	private static HBox ImageBox() throws FileNotFoundException{
          HBox pane = new HBox();
		Image img = new Image(new FileInputStream("src\\HelpIcon.png"));
                ImageView imageView = new ImageView();
                imageView.setImage(img);
                imageView.setFitHeight(300);
                imageView.setFitWidth(300);
                pane.getChildren().add(imageView);
            return pane;
}
}