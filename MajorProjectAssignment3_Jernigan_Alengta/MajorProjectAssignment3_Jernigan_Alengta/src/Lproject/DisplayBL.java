// **********************************************
// Title:  Library Of Reigh
// Author: Alengta Jernigan
// Course Section: CMIS202-ONL1 (Seidel) Spring 2024
// File: Display.java
// Description: Makes use of generics so that certain methods can take in a number of variable types in order to create labels and buttons.
//Small collection of books sort by author's last name classify genere or number of pages
// **********************************************
package Lproject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.*;


//Makes use of generics
public class DisplayBL <D> {
	EventMain eventManager = new EventMain();
	//Display a button
	public Button DisplayButton(D name) throws FileNotFoundException {
		
              //create a input stream
		FileInputStream input = new FileInputStream("src\\Libraryicon40.png"); 
             //create a image 
                Image i = new Image(input);
             //create a imageview 
             ImageView iw = new ImageView(i);
             //create button
             Button bt = new Button(name.toString(), iw);
             bt.setStyle("-fx-border-color: lightgray; -fx-background-color: slategray; "
				+ "-fx-text-fill: white");
		bt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
		return bt;
	}
	
	//Display a button from any integer, double, etc...
	public Button DisplayNumButton(D name) throws FileNotFoundException {
            //create a input stream
		FileInputStream input = new FileInputStream("src\\Bookicon40.png"); 
             //create a image 
                Image i = new Image(input);
             //create a imageview 
             ImageView ij = new ImageView(i);
             //create button
             Button bt = new Button(name.toString(), ij);
		
		bt.setStyle("-fx-border-color: lightgray; -fx-background-color: slategray; "
				+ "-fx-text-fill: white");
		bt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
		
		return bt;
	}
	
	//Display a big label
	public Label DisplayMainLabel(D name, String color) {
		Label lbl = new Label(name.toString());
		lbl.setStyle("-fx-text-fill: blue" );
		lbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
		
		return lbl;
	}
        //Display a huge label
	public Label DisplayHugeLabel(D name) {
		Label lbl = new Label(name.toString());
		lbl.setStyle("-fx-text-fill: black");
		lbl.setFont(Font.font("Times New Roman", 36));
		lbl.setWrapText(true);
		
		return lbl;
	}
        //Display a big white label
	public Label DisplayBigWhiteLabel(D name) {
		Label lbl = new Label(name.toString());
		lbl.setStyle("-fx-text-fill: white");
		lbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
		
		return lbl;
        }
        //Display a big black label
	public Label DisplayBigLabel(D name) {
		Label lbl = new Label(name.toString());
		lbl.setStyle("-fx-text-fill: black");
		lbl.setFont(Font.font("Times New Roman", 25));
		lbl.setWrapText(true);
			
		return lbl;

        }
	//Display big label from integer, double, etc...
	public Label DisplayNumMainLabel(D name, String color) {
		Label lbl = new Label(String.valueOf(name));
		lbl.setStyle("-fx-text-fill: blue" );
		lbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
		
		return lbl;
	}
	
	//Display a smaller label
	public Label DisplaySmallLabel(D name) {
		Label lbl = new Label(name.toString());
		lbl.setStyle("-fx-text-fill: black");
		lbl.setFont(Font.font("Times New Roman", 18));
		
		return lbl;
	}
	
	//Display a small label from an integer, double, etc...
	public Label DisplayNumSmallLabel(D count) {
		String lblName = String.valueOf(count);
		
		Label lbl = new Label(lblName);
		lbl.setStyle("-fx-text-fill: black");
		lbl.setFont(Font.font("Times New Roman", 18));
		
		return lbl;
	}
        //Display big label from integer, double, etc...
		public Label DisplayNumBigLabel(D name) {
		Label lbl = new Label(String.valueOf(name));
		lbl.setStyle("-fx-text-fill: black");
		lbl.setFont(Font.font("Times New Roman", 26));
		lbl.setWrapText(true);
			
		return lbl;
	}

	
	//Display an hbox
	public HBox DisplaySimpleHBox(D contents) {
		HBox hBox = new HBox(15);
		hBox.setPadding(new Insets(15, 15, 15, 15));
		hBox.setStyle("-fx-background-color: black");
		hBox.setAlignment(Pos.CENTER);
		
		//create
		Label header = DisplayMainLabel(contents, "white");
		
		//add label
		hBox.getChildren().add(header);
		
		return hBox;
	}
	//Display a smaller label with case mouse click
	public Label DisplaySmallTitleLabel(D name) {
		Label lbl = new Label(name.toString());
		lbl.setStyle("-fx-text-fill: blue");
		lbl.setFont(Font.font("Times New Roman", 16));
		lbl.setUnderline(true);
		
		lbl.setOnMouseClicked(eventManager.handleDescription);
		
		return lbl;
        }
	//Display an error message
	@SuppressWarnings("unchecked")
	public Stage DisplayErrorMessage(String error) {
		//Set stage and scene containing error message
		Stage stage = new Stage();
		Scene scene = new Scene(DisplaySmallLabel((D) ("There was an error: " + error)));
		
		//Set qualities of stage
		stage.setResizable(false);
		stage.setHeight(400);
		stage.setWidth(400);
		stage.setTitle("Error Message");
		stage.setScene(scene);
		
		return stage;
	}
}