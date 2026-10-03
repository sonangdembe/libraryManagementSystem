package library.example.libraryManagementSystem.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {

    private String name;
    private String author;
    private Long isbn;
    private String publisher;
    private Boolean isDeleted;
}
