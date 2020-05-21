package com.lolo.juc.lombok;

public class BookMain {

    public static void main(String[] args) {

        Book book = new Book(11, "CoreJava", 45d);
        System.out.println(book.toString());

        BookUltra myBook = new BookUltra(11, "CoreJava", 45d, "LaoShe");
        myBook.setBookName("CoreSpark");
        System.out.println(myBook.getBookName());
        System.out.println(myBook.toString());
    }
}
