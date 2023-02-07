import java.util.ArrayList;

public class Library {
	// create two lists
	ArrayList<Book> libraryList = new ArrayList<Book>();
	ArrayList<Book> borrowedBooks = new ArrayList<Book>();
	
	String address;
	
	// assign address
	public Library(String address) {
		this.address = address;
	}
	
	// add book to library
	public void addBook(Book book) {
		libraryList.add(book);
	}
	
	// print library hours
	public static void printOpeningHours() {
		System.out.println("Libraries are open daily from 9am to 5pm.");
	}
	
	// print address
	public void printAddress() {
		System.out.println(address);
	}
		
	// borrow book method
	public void borrowBook(String title) {
		// cycle library
		for(int i=0; i<borrowedBooks.size(); i++) {
			Book bookItem = borrowedBooks.get(i);
			// if book is borrowed, return
			if(bookItem.title.contains(title)) {
				System.out.println("Sorry, this book is already borrowed.");
				return;
			} 
		}
		
		// stream to check if the library contains title
		boolean libraryContains = libraryList.stream()
				.anyMatch(book -> book.getStringProperty().equals(title));
		
		if(libraryContains == false) {
			System.out.println("Sorry, this book is not in our catalog.");
			return;
		} 
		
		// cycle the library
		for(int i=0; i<libraryList.size(); i++) {
			Book bookItem = libraryList.get(i);
			
			// if title is in library and not borrowed then:
			//			set borrowed to true
			//			remove book from library
			//			add the book to library 
			if(bookItem.title.contains(title)) {
				if(bookItem.isBorrowed() == false) {
					bookItem.borrowed();
					libraryList.remove(bookItem);
					borrowedBooks.add(bookItem);
					System.out.println("You successfully borrowed "+ title);
				}
			} 
		}
	}	
	
	// return book
	public void returnBook(String title) {
		// stream to check if book is in library
		boolean contains = borrowedBooks.stream()
				.anyMatch(book -> book.getStringProperty().equals(title));
		
		if(contains == false) {
			System.out.println("Sorry, this book is not in our catalog.");
		}
		
		// cycle list
		for(int i=0; i < borrowedBooks.size(); i++) {
			Book bookItem = borrowedBooks.get(i);
			
			// if title is in library and borrowed then:
			//			set borrowed to true
			//			remove book from library
			//			add the book to library 
			if(bookItem.title.contains(title)) {
				if(bookItem.isBorrowed() == true) {
					bookItem.returned();
					libraryList.add(bookItem);
					borrowedBooks.remove(bookItem);
					System.out.println("You successfully returned "+ title);
				} else if(bookItem.isBorrowed() == false) {
					System.out.println("Sorry, you are not currently borrowing this book.");
				}
				
			}
		}
	}
	
	// print available book in library
	public void printAvailableBooks() {
		
		if(libraryList.isEmpty()) {
			System.out.println("No books in catalog");
		}
		
		// cycle to print each book in library
		for(int i=0; i < libraryList.size(); i++) {
			System.out.println(libraryList.get(i).getStringProperty());  
		}
	}
	
    public static void main(String[] args) {
        // create two libraries
        Library firstLibrary = new Library("10 Main St.");
        Library secondLibrary = new Library("228 Liberty St.");

        // add four books to the first library
        firstLibrary.addBook(new Book("The Da Vinci Code"));
        firstLibrary.addBook(new Book("Le Petit Prince"));
        firstLibrary.addBook(new Book("A Tale of Two Cities"));
        firstLibrary.addBook(new Book("The Lord of the Rings"));

        // print opening hours and the addresses
        System.out.println("Library hours:");
        printOpeningHours();
        System.out.println();

        // print library addresses
        System.out.println("Library addresses:");
        firstLibrary.printAddress();
        secondLibrary.printAddress();
        System.out.println();

        // Try to borrow The Lords of the Rings from both libraries
        System.out.println("Borrowing The Lord of the Rings:");
        firstLibrary.borrowBook("The Lord of the Rings");
        firstLibrary.borrowBook("The Lord of the Rings");
        secondLibrary.borrowBook("The Lord of the Rings");
        System.out.println();

        // Print the titles of all available books from both libraries
        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();
        System.out.println();
        System.out.println("Books available in the second library:");
        secondLibrary.printAvailableBooks();
        System.out.println();

        // Return The Lords of the Rings to the first library
        System.out.println("Returning The Lord of the Rings:");
        firstLibrary.returnBook("The Lord of the Rings");
        System.out.println();

        // Print the titles of available from the first library
        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();
    }
}