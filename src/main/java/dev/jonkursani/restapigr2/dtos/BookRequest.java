package dev.jonkursani.restapigr2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getters, setters, equals
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest { // dto => request object
    // id gjenerohet
    private String title;
    private String author;
    private String category;
    private int rating;
}