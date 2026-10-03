package library.example.libraryManagementSystem.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto {

    private String name;
    private String author;
    private Long isbn;
    private String publisher;
}
