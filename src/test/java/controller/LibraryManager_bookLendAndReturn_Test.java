package controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import model.Book;
import model.Library;
import model.User;

public class LibraryManager_bookLendAndReturn_Test {

	private Map<Integer, Book> bookList = new HashMap<Integer, Book>();
	private Library library = new Library(bookList);
	private LibraryManager libraryManager = new LibraryManager(library);

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    @Before
	public void setUp() {
        System.setOut(new PrintStream(outContent));
    }
    
    @Test
	public void testLendBook_happyPath() {
		createAndAddBook("Title1", "Author1", 2015);
		libraryManager.lendBook(1, new User("Name", "LastName"));
		
		assertThat(libraryManager.getBook(1).isLent()).isTrue();
		assertThat(libraryManager.getBook(1).getLentBy().getName()).isEqualTo("Name");
		assertThat(libraryManager.getBook(1).getLentBy().getLastName()).isEqualTo("LastName");
	}

	@Test
	public void testLendBook_bookAlreadyLent() {
		createAndAddBook("Title1", "Author1", 2015);
		libraryManager.lendBook(1, new User("Name1", "LastName1"));
		libraryManager.lendBook(1, new User("Name2", "LastName2"));
		
		assertThat(libraryManager.getBook(1).isLent()).isTrue();
		assertThat(libraryManager.getBook(1).getLentBy().getName()).isEqualTo("Name1");
		assertThat(libraryManager.getBook(1).getLentBy().getLastName()).isEqualTo("LastName1");
	}

	@Test
	public void testLendBook_bookDoesNotExist() {
		libraryManager.lendBook(1, new User("Name", "LastName"));
		assertThat(outContent.toString()).contains("Book with id 1 does not exist.");
	}
	
	@Test
	public void testReturnBook_happyPath() {
		createAndAddBook("Title1", "Author1", 2015);
		libraryManager.lendBook(1, new User("Name1", "LastName1"));
		libraryManager.returnBook(1);

		assertThat(libraryManager.getBook(1).isLent()).isFalse();
		assertThat(libraryManager.getBook(1).getLentBy()).isNull();
	}

	@Test
	public void testReturnBook_bookDoesNotExist() {
		libraryManager.returnBook(1);
		assertThat(outContent.toString()).contains("Book with id 1 does not exist.");
	}
	

	private Book createAndAddBook(String title, String author, Integer year) {
		Book book = new Book(title, author, year);
		book.setId(1);
		libraryManager.addBook(book);		
		return book;
	}
}
