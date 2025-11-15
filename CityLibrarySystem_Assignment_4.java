import java.io.*;
import java.util.*;

// Book Class
class Book implements Comparable<Book> {
    private int bookId;
    private String title;
    private String author;
    private String category;
    private boolean isIssued;

    public Book(int id, String title, String author, String category, boolean issued) {
        this.bookId = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isIssued = issued;
    }

    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public boolean getIssuedStatus() { return isIssued; }

    public void markAsIssued() { this.isIssued = true; }
    public void markAsReturned() { this.isIssued = false; }

    public void displayBookDetails() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Category: " + category);
        System.out.println("Issued: " + (isIssued ? "Yes" : "No"));
        System.out.println("----------------------------------");
    }

    // Default sorting = by title
    public int compareTo(Book b) {
        return this.title.compareToIgnoreCase(b.title);
    }
}

// Comparator for sorting by author
class AuthorComparator implements Comparator<Book> {
    public int compare(Book a, Book b) {
        return a.getAuthor().compareToIgnoreCase(b.getAuthor());
    }
}

// Member Class
class Member {
    private int memberId;
    private String name;
    private String email;
    private List<Integer> issuedBooks = new ArrayList<>();

    public Member(int id, String name, String email) {
        this.memberId = id;
        this.name = name;
        this.email = email;
    }

    public int getMemberId() { return memberId; }

    public void addIssuedBook(int bookId) {
        issuedBooks.add(bookId);
    }

    public void returnIssuedBook(int bookId) {
        issuedBooks.remove(Integer.valueOf(bookId));
    }

    public void displayMemberDetails() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Issued Books: " + issuedBooks);
        System.out.println("----------------------------------");
    }
}

// Library Manager Class
public class CityLibrarySystem_Assignment_4 {

    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, Member> members = new HashMap<>();

    private Scanner sc = new Scanner(System.in);
    private String bookFile = "books.txt";
    private String memberFile = "members.txt";

    public CityLibrarySystem_Assignment_4() {
        loadFromFile();
    }

    // Add Book
    public void addBook() {
        try {
            System.out.print("Enter Book Title: ");
            String title = sc.nextLine();
            System.out.print("Enter Author: ");
            String author = sc.nextLine();
            System.out.print("Enter Category: ");
            String category = sc.nextLine();

            int id = books.size() + 101;
            Book b = new Book(id, title, author, category, false);

            books.put(id, b);
            saveToFile();

            System.out.println("Book added successfully with ID: " + id);

        } catch (Exception e) {
            System.out.println("Error adding book.");
        }
    }

    // Add Member
    public void addMember() {
        try {
            System.out.print("Enter Member Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            int id = members.size() + 501;
            Member m = new Member(id, name, email);

            members.put(id, m);
            saveToFile();

            System.out.println("Member added with ID: " + id);

        } catch (Exception e) {
            System.out.println("Error adding member.");
        }
    }

    // Issue Book
    public void issueBook() {
        try {
            System.out.print("Enter Book ID: ");
            int bookId = Integer.parseInt(sc.nextLine());
            System.out.print("Enter Member ID: ");
            int memberId = Integer.parseInt(sc.nextLine());

            if (!books.containsKey(bookId)) {
                System.out.println("Book not found.");
                return;
            }

            if (!members.containsKey(memberId)) {
                System.out.println("Member not found.");
                return;
            }

            Book b = books.get(bookId);

            if (b.getIssuedStatus()) {
                System.out.println("Book is already issued.");
                return;
            }

            b.markAsIssued();
            members.get(memberId).addIssuedBook(bookId);

            saveToFile();
            System.out.println("Book issued successfully.");

        } catch (Exception e) {
            System.out.println("Error issuing book.");
        }
    }

    // Return Book
    public void returnBook() {
        try {
            System.out.print("Enter Book ID: ");
            int bookId = Integer.parseInt(sc.nextLine());
            System.out.print("Enter Member ID: ");
            int memberId = Integer.parseInt(sc.nextLine());

            Book b = books.get(bookId);
            Member m = members.get(memberId);

            if (b == null || m == null) {
                System.out.println("Invalid book or member ID.");
                return;
            }

            b.markAsReturned();
            m.returnIssuedBook(bookId);

            saveToFile();
            System.out.println("Book returned successfully.");

        } catch (Exception e) {
            System.out.println("Error returning book.");
        }
    }

    // Search books by title/author/category
    public void searchBooks() {
        System.out.print("Enter keyword: ");
        String key = sc.nextLine().toLowerCase();

        for (Book b : books.values()) {
            if (b.getTitle().toLowerCase().contains(key) ||
                b.getAuthor().toLowerCase().contains(key) ||
                b.getCategory().toLowerCase().contains(key)) {
                b.displayBookDetails();
            }
        }
    }

    // Sort Books
    public void sortBooks() {
        System.out.println("1. Sort by Title");
        System.out.println("2. Sort by Author");
        System.out.print("Enter choice: ");
        int ch = Integer.parseInt(sc.nextLine());

        List<Book> list = new ArrayList<>(books.values());

        if (ch == 1) {
            Collections.sort(list);
        } else if (ch == 2) {
            Collections.sort(list, new AuthorComparator());
        }

        for (Book b : list) {
            b.displayBookDetails();
        }
    }

    // Load from File
    private void loadFromFile() {
        try {
            File f1 = new File(bookFile);
            File f2 = new File(memberFile);

            if (!f1.exists()) f1.createNewFile();
            if (!f2.exists()) f2.createNewFile();

            BufferedReader br = new BufferedReader(new FileReader(bookFile));
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                int id = Integer.parseInt(p[0]);
                books.put(id, new Book(id, p[1], p[2], p[3], Boolean.parseBoolean(p[4])));
            }
            br.close();

            BufferedReader br2 = new BufferedReader(new FileReader(memberFile));
            while ((line = br2.readLine()) != null) {
                String[] p = line.split(",");
                int id = Integer.parseInt(p[0]);
                Member m = new Member(id, p[1], p[2]);
                members.put(id, m);
            }
            br2.close();

        } catch (Exception e) {
            System.out.println("Error loading files.");
        }
    }

    // Save to File
    private void saveToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(bookFile));

            for (Book b : books.values()) {
                bw.write(
                        b.getBookId() + "," +
                        b.getTitle() + "," +
                        b.getAuthor() + "," +
                        b.getCategory() + "," +
                        b.getIssuedStatus()
                );
                bw.newLine();
            }
            bw.close();

            BufferedWriter bw2 = new BufferedWriter(new FileWriter(memberFile));
            for (Member m : members.values()) {
                bw2.write(m.getMemberId() + "," + m.getMemberId() + "," + m.getMemberId());
                bw2.newLine();
            }
            bw2.close();

        } catch (Exception e) {
            System.out.println("Error saving files.");
        }
    }

    // Menu
    public void mainMenu() {
        int ch = 0;

        while (ch != 7) {
            System.out.println("\n--- City Library Digital Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Books");
            System.out.println("6. Sort Books");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            try {
                ch = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid choice.");
                continue;
            }

            switch (ch) {
                case 1: addBook(); break;
                case 2: addMember(); break;
                case 3: issueBook(); break;
                case 4: returnBook(); break;
                case 5: searchBooks(); break;
                case 6: sortBooks(); break;
                case 7: saveToFile(); System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    public static void main(String[] args) {
        CityLibrarySystem_Assignment_4 app = new CityLibrarySystem_Assignment_4();
        app.mainMenu();
    }
}
