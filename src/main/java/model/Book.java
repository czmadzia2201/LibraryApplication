package model;

import java.util.concurrent.atomic.AtomicInteger;
import javax.validation.constraints.NotNull;

public class Book {
	
	private static final AtomicInteger count = new AtomicInteger(0); 
	
	private int id;
	@NotNull(message = "Title must not be null")
	private String title;
	@NotNull(message = "Author must not be null") 
	private String author;
	@NotNull(message = "Year must not be null") 
	private	Integer year;
	
	private boolean lent;
	
	private User lentBy;
	
	public Book(String title, String author, Integer year) {
		id = count.incrementAndGet();
		this.title = title;
		this.author = author;
		this.year = year;
	}
	
	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public boolean isLent() {
		return lent;
	}

	public void setLent(boolean lent) {
		this.lent = lent;
	}

	public User getLentBy() {
		return lentBy;
	}

	public void setLentBy(User lentBy) {
		this.lentBy = lentBy;
	}

	@Override
	public String toString() {
		return  "id: " + id + ",\n" +
				"title: " + title + ",\n" +
				"author: " + author + ",\n" +
				"year: " + year + ",\n" +
				"lent: " + lent + ",\n" +
				"lent by: " + lentBy + ",\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + id;
		result = prime * result + (lent ? 1231 : 1237);
		result = prime * result + ((lentBy == null) ? 0 : lentBy.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (id != other.id)
			return false;
		if (lent != other.lent)
			return false;
		if (lentBy == null) {
			if (other.lentBy != null)
				return false;
		} else if (!lentBy.equals(other.lentBy))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
	
}
