package dev.jonkursani.restapigr2.controllers;

import dev.jonkursani.restapigr2.dtos.BookRequest;
import dev.jonkursani.restapigr2.entities.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private List<Book> books = new ArrayList<>();

    private void initializeBooks() {
        books.addAll(List.of(
                new Book(1L, "Title One", "Author One", "science", 5),
                new Book(2L, "Title Two", "Author Two", "history", 4),
                new Book(3L, "Title Three", "Author Three", "math", 3)
        ));
    }

    public BookController() {
        initializeBooks();
    }

    // @RequstParam
    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String category) {
        if (category == null) {
            return books;
        }

        // filtrimi i librave ne baze te kategorise
        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    // @PathVariable
//    @GetMapping("/api/books/{id}")
//    public Book getBookById(@PathVariable int id) {
//        return books.get(id);
//    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id) {
//        for (Book book : books) {
//            if (book.getTitle().equalsIgnoreCase(title)) {
//                return book;
//            }
//        }
//
//        return null;

//        Book book = books.stream()
//                .filter(b -> b.getTitle().equalsIgnoreCase(title))
//                .findFirst()
//                .orElse(null);
//
//
//        return book;

//        return books.stream()
//                .filter(book -> book.getTitle().equalsIgnoreCase(title))
//                .findFirst()
//                .orElse(null);

        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public void createBook(@RequestBody BookRequest bookRequest) {
//        for (Book book : books) {
//            if (book.getTitle().equalsIgnoreCase(newBook.getTitle())) {
//                // sepse ky liber me ket titull ekziston
//                return;
//            }
//        }

//        boolean isNewBook = books.stream()
//                .noneMatch(book -> book.getTitle().equalsIgnoreCase(newBook.getTitle()));
//
//        if (isNewBook) {
//            books.add(newBook);
//        }

//        long id;
//        if (books.isEmpty()) {
//            id = 1;
//        } else {
//            id = books.getLast().getId() + 1;
//        }
        long id = books.isEmpty() ? 1 : books.getLast().getId() + 1;

        // konvertimin prej dto (request object) ne entitet
//        Book book = new Book(
//                id,
//                bookRequest.getTitle(),
//                bookRequest.getAuthor(),
//                bookRequest.getCategory(),
//                bookRequest.getRating()
//        );
        Book book = convertToBook(id, bookRequest);

        books.add(book);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable long id, @RequestBody BookRequest bookRequest) {
        for (int i = 0; i < books.size(); i++) {
//            if (books.get(i).getTitle().equalsIgnoreCase(title)) {
            if (books.get(i).getId() == id) {
                // konvertimi prej dto ne entitet
                Book updatedBook = convertToBook(id, bookRequest);
                books.set(i, updatedBook);
                return;
            }
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
//        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
        books.removeIf(book -> book.getId() == id);
    }

    private Book convertToBook(long id, BookRequest bookRequest) {
        return new Book(
                id,
                bookRequest.getTitle(),
                bookRequest.getAuthor(),
                bookRequest.getCategory(),
                bookRequest.getRating()
        );
    }
}