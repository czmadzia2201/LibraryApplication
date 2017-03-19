package controller;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import model.Book;
import model.Library;
import model.User;

public class LibraryManager_bookAddAndRemove_Test {

	private Map<Integer, Book> bookList = new HashMap<Integer, Book>();
	private Library library = new Library(bookList);
	private LibraryManager libraryManager = new LibraryManager(library);

	private Validator validator;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		System.setOut(new PrintStream(outContent));
	}

	@Test
	public void testAddBook_happyPath() {
		createAndAddBook("Title1", "Author1", 2015);
		
		assertThat(library.getBookList()).isNotEmpty();
		Book createdBook = libraryManager.getBook(1);
		assertThat(createdBook.getTitle()).isEqualTo("Title1");
		assertThat(createdBook.getAuthor()).isEqualTo("Author1");
		assertThat(createdBook.getYear()).isEqualTo(2015);
	}

	@Test
	public void testAddBook_nullValues() {
		Book bookToCreate = createAndAddBook(null, null, null);

		Set<ConstraintViolation<Book>> constraintViolations = validator.validate(bookToCreate);

		assertThat(constraintViolations).extracting("message").containsOnly( 
				"Title must not be null", 
				"Author must not be null", 
				"Year must not be null"
				);
		assertThat(library.getBookList()).isEmpty();
	}

	@Test
	public void testRemoveBook_happyPath() {
		createAndAddBook("Title1", "Author1", 2015);
		libraryManager.removeBook(1);
		
		assertThat(libraryManager.getBook(1)).isNull();
	}
	
	@Test
	public void testRemoveBook_bookIsLent() {
		createAndAddBook("Title1", "Author1", 2015);
		libraryManager.lendBook(1, new User("Name", "LastName"));
		libraryManager.removeBook(1);
		
		assertThat(libraryManager.getBook(1)).isNotNull();
	}

	@Test
	public void testRemoveBook_bookDoesNotExist() {
		libraryManager.removeBook(1);
		assertThat(outContent.toString()).contains("Book with id 1 does not exist.");
	}
	

	private Book createAndAddBook(String title, String author, Integer year) {
		Book book = new Book(title, author, year);
		book.setId(1);
		libraryManager.addBook(book);		
		return book;
	}
	
}
