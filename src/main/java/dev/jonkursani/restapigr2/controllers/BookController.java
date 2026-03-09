package dev.jonkursani.restapigr2.controllers;

import dev.jonkursani.restapigr2.dtos.BookRequest;
import dev.jonkursani.restapigr2.entities.Book;
import dev.jonkursani.restapigr2.exceptions.book.BookNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Book Endpoints", description = "Endpoints related to books")
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
    @Operation(summary = "Get all books", description = "Endpoint to retrieve list of books")
    @ResponseStatus(HttpStatus.OK) // 200
    @GetMapping
    public List<Book> getBooks(
            @Parameter(description = "Optional category of the book")
            @RequestParam(required = false) String category
    ) {
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

    @Operation(summary = "Get by id", description = "Endpoint to get book by id")
    @ResponseStatus(HttpStatus.OK) // 200
    @GetMapping("/{id}")
    public Book getBookById(
            @Parameter(description = "Id of the book you want to retrieve")
            @PathVariable @Min(value = 1) long id
    ) {
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
                .orElseThrow(() -> new BookNotFoundException(id));
//                .orElse(null);
    }

    @Operation(summary = "Create book", description = "Endpoint to create a new book")
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping
    public void createBook(@Valid @RequestBody BookRequest bookRequest) {
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

    @Operation(summary = "Update book", description = "Endpoint to update an existing book")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @PutMapping("/{id}")
    public void updateBook(
            @Parameter(description = "Id of the book you want to update")
            @PathVariable long id,
            @RequestBody BookRequest bookRequest
    ) {
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

    @Operation(summary = "Delete book", description = "Endpoint to delete an existing book")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @DeleteMapping("/{id}")
    public void deleteBook(
            @Parameter(description = "Id of the book you want to delete")
            @PathVariable long id
    ) {
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