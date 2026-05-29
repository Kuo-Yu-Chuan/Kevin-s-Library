package main.java.com.KevinsLibrary.Book;

public class Position {

    private byte library;
    private byte floor;

    public Position(byte library, byte floor) {
        this.library = library;
        this.floor = floor;
    }

    public byte getLibrary() {
        return library;
    }

    public byte getFloor() {
        return floor;
    }
}
