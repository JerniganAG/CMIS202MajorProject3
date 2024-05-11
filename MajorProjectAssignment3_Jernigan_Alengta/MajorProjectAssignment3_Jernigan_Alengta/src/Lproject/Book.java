// **********************************************
// Title:  Library Of Reigh
// Author: Alengta Jernigan
// Course Section: CMIS202-ONL1 (Seidel) Spring 2024
// File: Book.java
// Description: Book getters and settters
//Small collection of books sort by author's last name classify genere or number of pages
// **********************************************

package Lproject;
//Class for books
public class Book extends Media {
	
	//Declaring variables
	private String author;
	private String firstName;
	private String midInitial;
	private String lastName;
	private int pageCount;
       // private String stringHeader;
	
	//Constructors
	public Book(  String title, String genre, String author, int pageCount, String description) {
		super(title, genre, description);
		this.author = author;
		this.pageCount = pageCount;
                //this.stringHeader = stringHeader;
	}
	
	public Book() {
		super("", "","");
		author = "";
		pageCount = 0;
                //stringHeader = "";
                // """ Title, Genre, Author, Page Count, Description
                               //""";
	}
	
	//Mutators
	public void setAuthor(String firstName, String midInitial, String lastname) {
		if (midInitial.length() >= 1) {
			this.author = (firstName + " " + midInitial + ". " + lastname);
		} else {
			this.author = (firstName + " " + lastname);
		}
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setMidInitial(String midInitial) {
		this.midInitial = midInitial;
	}
	
	public void setLastName(String lastName)  {
		this.lastName = lastName;
	}
	
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	//accessors
	public String getAuthor() {
		return author;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getMidInitial() {
		return midInitial;
	}
	
	public String getLastName() {
		int index = author.lastIndexOf(' ');
		setLastName(author.substring(index + 1));
		
		return lastName.toUpperCase();
	}
	
	public int getPageCount() {
		return pageCount;
	}
	
	//Utility
	@Override public String recordBook() {
		String recordAuthor = getAuthor();
		int recordPageCount = getPageCount();
		
		return (super.recordBook() + "|" + recordAuthor + "|" + recordPageCount  + "|" + super.getDescription());

		
	}
}