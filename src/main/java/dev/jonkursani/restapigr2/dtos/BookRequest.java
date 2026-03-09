package dev.jonkursani.restapigr2.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getters, setters, equals
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest { // dto => request object
    // id gjenerohet
    // @Size perdoret per text
    @Size(min = 1, max = 30, message = "Titulli duhet te jete ne mes {min} dhe {max} karaktere")
    private String title;

    @Size(min = 1, max = 40)
    private String author;

    @Size(min = 1, max = 30)
    private String category;

    // @Min, @Max perdoret per numra
    @Min(value = 1)
    @Max(value = 5)
    private int rating;
}