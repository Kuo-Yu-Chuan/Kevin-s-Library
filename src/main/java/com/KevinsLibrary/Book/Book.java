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
    private String ebook;
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
            String ebook
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
        this.ebook = ebook;
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
    public void setInfo (
            String title,
            String author,
            int year, 
            String language, 
            String ISBN,
            String[] categories,
            String callNumber,
            String barCode,
            String positionString,
            byte available,
            String ebook) {
                
        this.title = title;
        this.author = author;
        this.year = year;
        this.language = language;
        this.ISBN = ISBN;

        this.categories.clear ();
        for (int i = 0; i < categories.length; i++) {
            this.categories.add (categories[i]);
        }

        this.callNumber = callNumber;
        this.barCode = barCode;
        setPosition (positionString);
        this.available = available;
        this.ebook = ebook;
    }

    public void setPosition (String positionString) {
        Position position = getPosition ();
        switch (positionString.charAt (5)) {
            case '一' :
                position.setLibrary ((byte) 1);
                break;
            case '二' :
                position.setLibrary ((byte) 2);
                break;
            case '三' :
                position.setLibrary ((byte) 3);
                break;
            case '四' :
                position.setLibrary ((byte) 4);
                break;
            default :
                position.setLibrary ((byte) 0);
        }
        position.setFloor (Byte.parseByte (positionString.substring (8, 9)));
        position.setArea (positionString.substring (11, positionString.length ()));
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
        Position position = getPosition ();
        return "位於" + position.getLibraryString () + " " + position.getFloorString () + " " + position.getArea ();
    }

    public byte getAvailable() {
        return available;
    }

    public String getEbook() {
        return ebook;
    }

}