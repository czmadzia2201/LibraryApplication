package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import model.Book;
import model.Library;
import model.User;

public class LibraryManager {
	
	private Map<Integer, Book> bookList;
	private static Validator validator;

	public LibraryManager(Library library) {
		bookList = library.getBookList();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	public void addBook(Book book) {
		Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);
		if (!constraintViolations.isEmpty()) {
			System.out.println("Book with id " + book.getId() + " not added - validation failed.");
			return;
		}
		bookList.put(book.getId(), book);
		System.out.println("Book added with id " + book.getId() + ".");			
	}
	
	public void removeBook(int id) {
		if (bookList.get(id) == null) {
			System.out.println("Book with id " + id + " does not exist.");
			return;
		} 
		if (bookList.get(id).isLent() == true) {
			System.out.println("Book with id " + id + " is currently lent and cannot be removed.");
			return;
		} 
		bookList.remove(id);
		System.out.println("Successfully removed book with id " + id + ".");						
	}
	
	
	
	public void lendBook(int id, User user) {
		Book book = bookList.get(id);
		if (book == null) {
			System.out.println("Book with id " + id + " does not exist.");
			return;
		} 
		if (bookList.get(id).isLent() == true) {
			System.out.println("Book with id " + id + " is currently lent.");
			return;
		} 
		book.setLent(true);
		book.setLentBy(user);
	}
	
	public void returnBook(int id) {
		Book book = bookList.get(id);
		if (book == null) {
			System.out.println("Book with id " + id + " does not exist.");
			return;
		}
		book.setLent(false);
		book.setLentBy(null);
	}
	
	
		
	public Book getBook(int id) {
		if (bookList.get(id) == null) {
			System.out.println("Book with id " + id + " does not exist.");
			return null;
		} 
		System.out.println(bookList.get(id));
		return bookList.get(id);
	}
	
	public List<Book> listAllBooks() {
		List<Book> searchMatch = new ArrayList<Book>();
		for (Book book : bookList.values()) {
			printAndAdd(book, searchMatch);
		}
		return searchMatch;
	}
	
	public List<Book> listAvailableBooks() {
		List<Book> searchMatch = new ArrayList<Book>();
		for (Book book : bookList.values()) {
			if (book.isLent()==false) {
				printAndAdd(book, searchMatch);
			}
		}
		return searchMatch;
	}
	
	public List<Book> searchBooksBy(String title, String author, Integer year) {
		List<Book> searchMatch = new ArrayList<Book>();
		for (Book book : bookList.values()) {
			if (title!=null && !book.getTitle().equals(title)) {
				continue;
			} else if (author!=null && !book.getAuthor().equals(author)) {
				continue;
			} else if (year!=null && !book.getYear().equals(year)) {
				continue;
			} else {
				printAndAdd(book, searchMatch);
			}
		}
		return searchMatch;
	}
	
	private void printAndAdd(Book book, List<Book> searchMatch) {
		System.out.println(book);
		searchMatch.add(book);
	}
	
}
