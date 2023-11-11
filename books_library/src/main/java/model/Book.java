package model;

import helper.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Book {
    @Id
    private Long id;
    private String author;
    private String title;
    private LocalDate publishedDate;
}
