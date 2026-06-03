package main.java.com.KevinsLibrary.Book;

public class Position {

    private byte library;
    private byte floor;
    private String area;

    public Position(byte library, byte floor, String area) {
        this.library = library;
        this.floor = floor;
        this.area = area;
    }

    public byte getLibrary() {
        return library;
    }

    public String getLibraryString () {
        switch (library) {
            case 1 : return "興老大一館";
            case 2 : return "興老大二館";
            case 3 : return "興老大三館";
            case 4 : return "興老大四館";
            default : return "興老大總館";
        }
    }

    public byte getFloor() {
        return floor;
    }

    public String getFloorString () {
        return " " + Byte.toString (floor) + "F ";
    }

    public String getArea () {
        return area;
    }
}
