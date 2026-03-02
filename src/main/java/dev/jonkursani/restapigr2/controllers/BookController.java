package dev.jonkursani.restapigr2.controllers;

import dev.jonkursani.restapigr2.entities.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    private List<Book> books = new ArrayList<>();

    private void initializeBooks() {
        books.addAll(List.of(
                new Book("Title One", "Author One", "science"),
                new Book("Title Two", "Author Two", "history"),
                new Book("Title Three", "Author Three", "math")
        ));
    }

    public BookController() {
        initializeBooks();
    }

    // @RequstParam
    @GetMapping("/api/books")
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

    @GetMapping("/api/books/{title}")
    public Book getBookByTitle(@PathVariable String title) {
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

        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }
}