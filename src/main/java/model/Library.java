package model;

import java.util.Map;

public class Library {
	
	private Map<Integer, Book> bookList;
	
	public Library(Map<Integer, Book> bookList) {
		this.bookList = bookList;
	}

	public Map<Integer, Book> getBookList() {
		return bookList;
	}

	public void setBookList(Map<Integer, Book> bookList) {
		this.bookList = bookList;
	}
	
}
