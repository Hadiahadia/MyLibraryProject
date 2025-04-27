// ملف: src/BorrowingProcess.java

import java.time.LocalDate;

public class BorrowingProcess {
    private Book book;
    private Borrower borrower;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowingProcess(Book book, Borrower borrower) {
        this.book = book;
        this.borrower = borrower;
        this.borrowDate = LocalDate.now();
        this.returnDate = null;

        borrower.borrowBook(book);
    }

    public void returnBook() {
        if (returnDate == null) {
            this.returnDate = LocalDate.now();
            borrower.returnBook(book);
        }
    }

    public String getStatus() {
        return returnDate == null ? "Borrowed" : "Returned";
    }

    @Override
    public String toString() {
        return book.getTitle() + " | Borrowed by: " + borrower.getName() +
               " on " + borrowDate +
               (returnDate != null ? ", Returned on: " + returnDate : ", Not yet returned");
    }
}
