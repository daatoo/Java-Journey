import java.util.Arrays;
import java.util.List;

public class Book {
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year){
        this.title = title;
        this.year = year;
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
                new Book("1984", "George Orwell", 1949),
                new Book("To Kill a Mockingbird", "Harper Lee", 1960),
                new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925),
                new Book("Brave New World", "Aldous Huxley", 1932),
                new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 1997),
                new Book("The Hobbit", "J.R.R. Tolkien", 1937),
                new Book("Pride and Prejudice", "Jane Austen", 1813),
                new Book("The Catcher in the Rye", "J.D. Salinger", 1951),
                new Book("The Da Vinci Code", "Dan Brown", 2003),
                new Book("The Kite Runner", "Khaled Hosseini", 2003),
                new Book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", 2011),
                new Book("Life of Pi", "Yann Martel", 2001),
                new Book("The Road", "Cormac McCarthy", 2006),
                new Book("The Hunger Games", "Suzanne Collins", 2008)
        );
        books.stream()
                .filter(book -> book.getYear() > 2000)
                .map(Book::getTitle)
                .forEach(System.out::println);
    }
}


