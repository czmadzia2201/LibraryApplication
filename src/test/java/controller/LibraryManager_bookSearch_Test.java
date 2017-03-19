package controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import model.Book;
import model.Library;
import model.User;

public class LibraryManager_bookSearch_Test {

	private Map<Integer, Book> bookList = new HashMap<Integer, Book>();
	private Library library = new Library(bookList);
	private LibraryManager libraryManager = new LibraryManager(library);
	
	private Book book1 = new Book("Title1", "Author1", 2001);
	private Book book2 = new Book("Title2", "Author2", 2003);
	private Book book3 = new Book("Title3", "Author3", 2003);
	
	@Before
	public void setUp() {
		book1.setId(1);
		book2.setId(2);
		book3.setId(3);
    	bookList.put(1, book1);
    	bookList.put(2, book2);
    	bookList.put(3, book3);		
	}
	
    @Test
    public void testSearchBooks_listAllBooks() {
    	List<Book> books = libraryManager.listAllBooks();
    	assertThat(books).containsAll(Arrays.asList(book1, book2, book3));
    }

    @Test
    public void testSearchBooks_listAvailableBooks() {
    	libraryManager.lendBook(1, new User("Name", "LastName"));

    	List<Book> books = libraryManager.listAvailableBooks();
    	assertThat(books).containsOnly(book2, book3);
    }

    @Test
    public void testSearchBooks_searchBooksBy_nullCriteria() {
    	List<Book> books = libraryManager.searchBooksBy(null, null, null);
    	assertThat(books).containsAll(Arrays.asList(book1, book2, book3));
    }
    
    @Test
    public void testSearchBooks_searchBooksBy_singleCriteria() {
    	List<Book> books1 = libraryManager.searchBooksBy("Title1", null, null);
    	assertThat(books1).containsOnly(book1);
       	List<Book> books2 = libraryManager.searchBooksBy(null, null, 2003);
    	assertThat(books2).containsOnly(book2, book3);
    }

    @Test
    public void testSearchBooks_searchBooksBy_multipleMatchingCriteria() {
    	List<Book> books1 = libraryManager.searchBooksBy("Title1", null, 2001);
    	assertThat(books1).containsOnly(book1);
    	List<Book> books2 = libraryManager.searchBooksBy("Title2", "Author2", 2003);
    	assertThat(books2).containsOnly(book2);
    }

    @Test
    public void testSearchBooks_searchBooksBy_multipleUnmatchingCriteria() {
    	List<Book> books = libraryManager.searchBooksBy("Title1", "Author2", 2001);
    	assertThat(books).isEmpty();
    }

}
