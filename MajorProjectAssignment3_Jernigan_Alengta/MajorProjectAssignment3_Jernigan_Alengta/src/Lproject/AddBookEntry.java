// **********************************************
// Title:  Library Of Reigh//good
// Author: Alengta Jernigan
// Course Section: CMIS202-ONL1 (Seidel) Spring 2024
// File: AddBookEntry.java
// Description: UI for inputing book information
//Small collection of books sort by author's last name classify genere or number of pages
// **********************************************
package Lproject;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.Queue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

//Description Class
public class AddBookEntry {
	
	private static DisplayBL<String> outline = new DisplayBL<String>(); //static reference to Display.java
	public static BorderPane bPane = new BorderPane(); //reference to bPane for viewlibraryofreigh
	private static String bookPath = "src\\libraryofreigh.txt";//reference to file path
	private static Queue<Book> saveQueue = new LinkedList<>();

	//build the add book scene
	public static Scene buildBookScene() throws FileNotFoundException {
		//create panes and nodes
		bPane.setTop(outline.DisplaySimpleHBox("Enter Book Information"));
		bPane.setCenter(BookInfo());
		bPane.setBottom(eventBox());
		
		//set scene
		Scene bookScene = new Scene(bPane);
		return bookScene;
	}
	
	//create the textfields, buttons, boxes
	private static HBox BookInfo() {
		//create panes
		VBox authorBox = new VBox(15);
		VBox miscBox = new VBox(15);
		
		//declaring textfields
		TextField titleText = new TextField();//title
		TextField fName = new TextField();//first name
		TextField mName = new TextField();//middle initial/name
		TextField lName = new TextField();//last name
		TextField pgCount = new TextField();//page count
		
                //declare textArea
		TextArea descriptionText = new TextArea();//description
		descriptionText.setWrapText(true);

                //ListView
		ListView<String> genreBox = new ListView<>();
		genreBox.getItems().addAll("Philosophy", "Sci-Fi", "Mystery", "Adventure", "Romance", "Comedy", "Young Adult", 
		 "Manga", "History", "Religion", "Horror", "Graphic Novel", "Short Story","Science");
		genreBox.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                genreBox.setPrefWidth(100);
                genreBox.setPrefHeight(100);
                
                
		//set contents of authorPane
		authorBox.getChildren().addAll((outline.DisplaySmallLabel("Title of The Book:")), titleText, outline.DisplaySmallLabel("Author's First Name:"), fName, 
				outline.DisplaySmallLabel("Author's Middle Initial:"), mName, outline.DisplaySmallLabel("Author's Last Name:"), lName);
		
		//Set contents of miscPane
		miscBox.getChildren().addAll(outline.DisplaySmallLabel("Page Count:"), pgCount, outline.DisplaySmallLabel("Genre: "), genreBox, outline.DisplaySmallLabel("Description: "), descriptionText);
		
		//Vbox
		HBox hBox = new HBox();
		hBox.getChildren().addAll(authorBox, miscBox);
		hBox.setAlignment(Pos.BOTTOM_CENTER);
		hBox.setPadding(new Insets(150, 50, 50, 50));
		
		return hBox;
	}
	
	//save the book to queue file
	@SuppressWarnings("unchecked")
	public static void saveBookToQueue(BorderPane bPane) {
		
		Book book = new Book();
		
		//record information
		
			//get contents of borderpane
			HBox centerBox = (HBox) bPane.getCenter();
			VBox leftBox = (VBox) centerBox.getChildren().get(0);
			VBox rightBox = (VBox) centerBox.getChildren().get(1);
			
			//record author
			String firstName = (String) ((TextField) leftBox.getChildren().get(3)).getText();
			String midInitial = (String) ((TextField) leftBox.getChildren().get(5)).getText();
			String lastName = (String) ((TextField) leftBox.getChildren().get(7)).getText();
			book.setAuthor(firstName, midInitial, lastName);		
			
			//record title
			book.setTitle((String) ((TextField) leftBox.getChildren().get(1)).getText());
			
			//record page count
			String pgCt = ((String) (((TextField) rightBox.getChildren().get(1)).getText()));
			book.setPageCount(Integer.parseInt(pgCt));
			
			//record genre
			book.setGenre((String) ((ListView<String>)rightBox.getChildren().get(3)).getSelectionModel().getSelectedItem());
			
                        //Record Des
                        book.setDescription((String) (((TextArea) rightBox.getChildren().get(5)).getText()));

                    //Record book to file if not comic
		saveQueue.add(book);
		
		//Reset the textFields
		((TextField) leftBox.getChildren().get(1)).setText("");
		((TextField) leftBox.getChildren().get(3)).setText("");
		((TextField) leftBox.getChildren().get(5)).setText("");
		((TextField) leftBox.getChildren().get(7)).setText("");
		((TextField) rightBox.getChildren().get(1)).setText("");
		((TextArea) rightBox.getChildren().get(5)).setText("");
	}
	
	//Save the books in queue to file
	public static void saveBooksInQueue(BorderPane bPane) {
		PrintWriter file;
		
		//get boxes
		HBox centerBox = (HBox) bPane.getCenter();
		VBox leftBox = (VBox) centerBox.getChildren().get(0);
		VBox rightBox = (VBox) centerBox.getChildren().get(1);
		
		//get textfields
		String Title = (String) ((TextField) rightBox.getChildren().get(1)).getText();
		String fName = (String) ((TextField) leftBox.getChildren().get(3)).getText();
		String mI = (String) ((TextField) leftBox.getChildren().get(5)).getText();
		String lName = (String) ((TextField) leftBox.getChildren().get(7)).getText();
		String pgCt = ((String) (((TextField) leftBox.getChildren().get(1)).getText()));
		
		try {
			//Check to see if the textFields have contents, if so save book
			//If nothing in the textfields then error is caught and queue is saved without adding current textfields to queue first.
			if (Title != null || fName != null || mI != null || lName != null || pgCt != null) {
				saveBookToQueue(bPane);
			}
			
			//go through the queue to save the books and delete them from queue.
			while (saveQueue.peek() != null) {
				Book book = saveQueue.poll();
                        //Record book to file 
                        try{
			file = new PrintWriter(new FileOutputStream(new File(bookPath), true));
			file.println(book.recordBook());
			file.close();
			}catch (Exception e) {
			outline.DisplayErrorMessage(e.toString());
			
		}
                        }
	}catch (NumberFormatException e) { //Checks if textFields are empty
			//try to save book again without saving textFields to queue first
			try {				
				while (saveQueue.peek() != null) {
					Book book = saveQueue.poll();
					
					file = new PrintWriter(new FileOutputStream(new File(bookPath), true));
					file.println(book.recordBook());
					file.close();
				}
			} catch (Exception c) {
				outline.DisplayErrorMessage(c.toString());
			}
		}
	}

      
	
	//create buttons for ui
	private static HBox eventBox() throws FileNotFoundException {
		//set up hbox
		HBox hBox = new HBox(15);
		hBox.setPadding(new Insets(15, 15, 15, 15));
		hBox.setStyle("-fx-background-color: slategray");
		EventMain eventManager = new EventMain();
				
		//Back to main menu
		Button btHome = outline.DisplayButton("Home");
		btHome.setOnAction(eventManager.buttonHome);
				
		//View Library
		Button btView = outline.DisplayButton("Library");
		btView.setOnAction(eventManager.buttonView);
		
                /*//Save book to queue button
		Button btQueue = outline.DisplayButton("Add Another Book");//save the book to queue Window
		btQueue.setOnAction(eventManager.buttonQAdd);
                   */ 
                //Save queue to file button
		Button btSave = outline.DisplayButton("Save Books");//Save the books in queue to file
		btSave.setOnAction(eventManager.buttonQSave);

		

		//Quit the application
		Button btQuit = outline.DisplayButton("Quit");
		btQuit.setOnAction(eventManager.buttonQuit);
				
		//add buttons
		hBox.getChildren().addAll(btHome, btSave,btView, btQuit);
				
		//set alignment
		hBox.setAlignment(Pos.CENTER);
				
		return hBox;
	}
}