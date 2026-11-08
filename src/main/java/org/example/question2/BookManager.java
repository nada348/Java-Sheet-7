package org.example.question2;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.IOException;

public class BookManager {

    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book b) {
        books.add(b);
    }

    public void clearBooks() {
        books.clear();
    }

    public void writeBooksToTextFile(String fileName)
            throws IOException {

        BufferedWriter writer =
                new BufferedWriter(new FileWriter(fileName));

        for (int i = 0; i < books.size(); i++) {
            writer.write(books.get(i).toString());
            writer.newLine();
        }

        writer.close();
    }

    public void readBooksFromTextFile(String fileName)
            throws IOException {

        BufferedReader reader =
                new BufferedReader(new FileReader(fileName));

        String line;

        while ((line = reader.readLine()) != null) {

            String[] data = line.split("\\|", 3);

            String title = data[0];
            String author = data[1];
            double price = Double.parseDouble(data[2]);

            Book b = new Book(title, author, price);

            books.add(b);
        }

        reader.close();
    }

    public void sortBooks() {

        Collections.sort(books, new Comparator<Book>() {

            @Override
            public int compare(Book b1, Book b2) {
                return Double.compare(b2.getPrice(), b1.getPrice());
            }
        });
    }

    public void saveBooks(String fileName)
            throws IOException {

        sortBooks();

        ObjectOutputStream output =
                new ObjectOutputStream(new FileOutputStream(fileName));

        output.writeObject(books);

        output.close();
    }

    public void loadBooks(String fileName)
            throws IOException, ClassNotFoundException {

        ObjectInputStream input =
                new ObjectInputStream(new FileInputStream(fileName));

        books = (ArrayList<Book>) input.readObject();

        input.close();

        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }
    }
}

 class BookApp {

    public static void main(String[] args) {

        try {

            // 1. Create BookManager object
            BookManager manager = new BookManager();

            // 2. Create three Book objects
            Book b1 = new Book("Java", "Ahmed", 300);
            Book b2 = new Book("Python", "Mona", 200);
            Book b3 = new Book("C++", "Omar", 500);

            manager.addBook(b1);
            manager.addBook(b2);
            manager.addBook(b3);

            manager.writeBooksToTextFile("books.txt");
            manager.clearBooks();
            manager.readBooksFromTextFile("books.txt");
            manager.saveBooks("books.dat");
            System.out.println("Books loaded from binary file:");

            manager.loadBooks("books.dat");

        } catch (IOException e) {

            System.out.println(e.getMessage());

        } catch (ClassNotFoundException e) {

            System.out.println(e.getMessage());
        }
    }
}