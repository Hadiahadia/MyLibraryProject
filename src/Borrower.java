// ملف: src/Borrower.java

import java.util.ArrayList;

public class Borrower {
    private String name;
    private String studentId;
    private ArrayList<Book> borrowedBooks;

    public Borrower(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        if (book != null && book.isAvailable()) {
            borrowedBooks.add(book);
            book.setAvailable(false);
        }
    }

    public void returnBook(Book book) {
        if (book != null && borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.setAvailable(true);
        }
    }

    @Override
    public String toString() {
        return name + " (" + studentId + ")";
    }
}
