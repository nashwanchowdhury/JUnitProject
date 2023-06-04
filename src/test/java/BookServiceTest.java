import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class BookServiceTest {
    private BookService bookService;
    private User user;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all test methods");
    }
    @BeforeEach
    public void initialize() {

        user = new User("username", "password", "john.smith@gmail.com");

        book1 = new Book("Title: The Giver", "Author: F. Scott Fitzgerald", "Genre: Comedy", 1.99);
        book2 = new Book("Title: Harry Potter and Chamber of Secrets", "Author: Harper Lee", "Genre: Fiction", 2.99);
        book3 = new Book("Title: The Lion King", "Author: J.K Rowling", "Genre: Non-Fiction", 3.99);

        bookService = new BookService();
        bookService.addBook(book1);
        bookService.addBook(book2);
        bookService.addBook(book3);
    }

    @AfterEach
    public void reset() {
        bookService = null;
        user = null;
    }

    @Test
    public void testTitle() {
        String keyword = "Title";
        List<Book> result = bookService.searchBook(keyword);
        assertEquals(Arrays.asList(book1, book2, book3), result);
    }

    @Test
    public void testAuthor() {
        String keyword = "Author";
        List<Book> result = bookService.searchBook(keyword);
        assertEquals(Arrays.asList(book1, book2, book3), result);
    }

    @Test
    public void testGenre() {
        String keyword = "Genre";
        List<Book> result = bookService.searchBook(keyword);
        assertEquals(Arrays.asList(book1, book2, book3), result);
    }

    @Test
    public void testNotFound() {
        String keyword = "Unknown";
        List<Book> result = bookService.searchBook(keyword);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testBookPurchase() {
        boolean success = bookService.purchaseBook(user, book1);
        assertTrue(success);
    }

    @Test
    public void testNoBookPurchase() {
        Book unknownBook = new Book("Unknown", "Author", "Genre", 2.99);
        boolean success = bookService.purchaseBook(user, unknownBook);
        assertFalse(success);
    }
}