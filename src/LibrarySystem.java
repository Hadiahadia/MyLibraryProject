import java.util.Scanner;
import java.util.ArrayList;

public class LibrarySystem {
    private static ArrayList<Book> books = new ArrayList<>();
    private static ArrayList<Borrower> borrowers = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n📚 Digital Library System");
            System.out.println("1. Add Book");
            System.out.println("2. Add Borrower");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Book");
            System.out.println("6. Show Borrower’s Books");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1: addBook(); break;
                case 2: addBorrower(); break;
                case 3: borrowBook(); break;
                case 4: returnBook(); break;
                case 5: searchBook(); break;
                case 6: showBorrowerBooks(); break;
                case 7: running = false; break;
                default: System.out.println("❌ Invalid option!");
            }
        }

        System.out.println("👋 Goodbye!");
    }

    private static void addBook() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Type (1: Paper, 2: E-Book): ");
        int type = scanner.nextInt();
        scanner.nextLine();

        Book book = (type == 1) ? new PaperBook(title, author, isbn)
                                : new EBook(title, author, isbn);

        books.add(book);
        System.out.println("✅ Book added!");
    }

    private static void addBorrower() {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Student ID: ");
        String id = scanner.nextLine();

        borrowers.add(new Borrower(name, id));
        System.out.println("✅ Borrower added!");
    }

    private static void borrowBook() {
        System.out.print("Student ID: ");
        String id = scanner.nextLine();

        Borrower borrower = findBorrowerById(id);
        if (borrower == null) {
            System.out.println("❌ Borrower not found!");
            return;
        }

        System.out.print("Book ISBN: ");
        String isbn = scanner.nextLine();

        Book book = findBookByIsbn(isbn);
        if (book == null || !book.isAvailable()) {
            System.out.println("❌ Book not available!");
            return;
        }

        new BorrowingProcess(book, borrower);
        System.out.println("✅ Book borrowed!");
    }

    private static void returnBook() {
        System.out.print("Student ID: ");
        String id = scanner.nextLine();

        Borrower borrower = findBorrowerById(id);
        if (borrower == null) {
            System.out.println("❌ Borrower not found!");
            return;
        }

        System.out.print("Book ISBN: ");
        String isbn = scanner.nextLine();

        Book book = findBookByIsbn(isbn);
        if (book != null && borrower.getBorrowedBooks().contains(book)) {
            borrower.returnBook(book);
            System.out.println("✅ Book returned!");
        } else {
            System.out.println("❌ Book not found or not borrowed by this student!");
        }
    }

    private static void searchBook() {
        System.out.print("Enter title or author: ");
        String query = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query) ||
                book.getAuthor().toLowerCase().contains(query)) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("❌ No matching books found.");
        }
    }

    private static void showBorrowerBooks() {
        System.out.print("Student ID: ");
        String id = scanner.nextLine();

        Borrower borrower = findBorrowerById(id);
        if (borrower == null) {
            System.out.println("❌ Borrower not found!");
            return;
        }

        ArrayList<Book> borrowed = borrower.getBorrowedBooks();
        if (borrowed.isEmpty()) {
            System.out.println("ℹ️ This borrower has no borrowed books.");
        } else {
            System.out.println("📘 Books borrowed by " + borrower.getName() + ":");
            for (Book book : borrowed) {
                System.out.println(book);
            }
        }
    }

    private static Borrower findBorrowerById(String id) {
        for (Borrower b : borrowers) {
            if (b.getStudentId().equals(id)) return b;
        }
        return null;
    }

    private static Book findBookByIsbn(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) return b;
        }
        return null;
    }
}
