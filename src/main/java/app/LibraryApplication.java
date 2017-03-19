package app;
import java.util.HashMap;
import java.util.Map;

import controller.LibraryManager;
import model.Book;
import model.Library;
import model.User;

public class LibraryApplication {

	public static void main(String[] args) {
		
		Map<Integer, Book> bookList = new HashMap<Integer, Book>();
		Library library = new Library(bookList);
		LibraryManager libraryManager = new LibraryManager(library);
		
		Book book1 = new Book("Zbrodnia i kara", "Fiodor Dostojewski", 2000);
		Book book2 = new Book("Zbrodnia i kara", "Fiodor Dostojewski", 1995);
		Book book3 = new Book("Wywiad z wampirem", "Anne Rice", 1992);
		Book book4 = new Book("Zmierzch", "Stephenie Meyer", 2008);
		Book book5 = new Book("Zmierzch", "Stephenie Meyer", 2008);
		Book book6 = new Book("Lord Demon", "Roger Zelazny", 2003);
		Book book7 = new Book("Bracia Karamazow", "Fiodor Dostojewski", 1992);
		
		libraryManager.addBook(book1);
		libraryManager.addBook(book2);
		libraryManager.addBook(book3);
		libraryManager.addBook(book4);
		libraryManager.addBook(book5);
		libraryManager.addBook(book6);
		libraryManager.addBook(book7);
		
		System.out.println("--------------------");

		libraryManager.getBook(7);
		libraryManager.getBook(8);

		System.out.println("--------------------");

		libraryManager.lendBook(2, new User("Name1", "LastName1"));
		libraryManager.lendBook(2, new User("Name2", "LastName2"));
		libraryManager.lendBook(3, new User("Name2", "LastName2"));
		
		libraryManager.listAllBooks();

		System.out.println("--------------------");

		libraryManager.listAvailableBooks();

		System.out.println("--------------------");

		libraryManager.searchBooksBy(null, "Fiodor Dostojewski", null);

		System.out.println("--------------------");
		
		libraryManager.searchBooksBy("Zmierzch", null, 2008);

		System.out.println("--------------------");
		
		libraryManager.searchBooksBy("Zmierzch", null, 2009);
		
		System.out.println("--------------------");

	}

}
