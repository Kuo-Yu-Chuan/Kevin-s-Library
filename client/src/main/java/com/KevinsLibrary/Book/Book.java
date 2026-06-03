package main.java.com.KevinsLibrary.Book;

import java.util.Set;

/**
 * Represents a physical or electronic book.
 */
public class Book {

    private String title;
    private String author;
    private int year;
    private String language;
    private String ISBN;
    private Set<String> categories;
    private String callNumber;
    private String barCode;
    private Position position;
    private byte available;
    private boolean ebookAvailable;

    /**
     * Constructs a Book object.
     */
    public Book(
            String title,
            String author,
            int year, 
            String language, 
            String ISBN,
            Set<String> categories,
            String callNumber,
            String barCode,
            Position position,
            byte available,
            boolean ebookAvailable
    ) {

        this.title = title;
        this.author = author;
        this.year = year;
        this.language = language;
        this.ISBN = ISBN;
        this.categories = categories;
        this.callNumber = callNumber;
        this.barCode = barCode;
        this.position = position;
        this.available = available;
        this.ebookAvailable = ebookAvailable;
    }

    /**
     * Borrows a book if available.
     */
    public void borrowBook() {

        if (available > 0) {
            available--;
        }
    }

    /**
     * Returns a book.
     */
    public void returnBook() {
        available++;
    }

    /**
     * Modifies the book title.
     */
    public void modifyTitle(String title) {
        this.title = title;
    }

    /**
     * Modifies the author name.
     */
    public void modifyAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear () {
        return year;
    }

    public String getLanguage () {
        return language;
    }

    public String getISBN() {
        return ISBN;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public String getCategoriesString () {
        String str = "";
        for (String category : categories) {
            str += (category + " ");
        }
        return str;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public String getBarCode() {
        return barCode;
    }

    public Position getPosition() {
        return position;
    }

    public String getPositionString () {
        return "位於" + getPosition ().getLibraryString () + getPosition ().getFloorString () + getPosition ().getArea ();
    }

    public byte getAvailable() {
        return available;
    }

    public boolean isEbookAvailable() {
        return ebookAvailable;
    }
}