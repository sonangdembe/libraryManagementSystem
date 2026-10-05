package library.example.libraryManagementSystem.mapper;

import library.example.libraryManagementSystem.dto.BookRequestDto;
import library.example.libraryManagementSystem.dto.BookResponseDto;
import library.example.libraryManagementSystem.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    // dto to new Entity
    public Book toEntity(BookRequestDto bookRequestDto){
        Book book = new Book();
        updateEntity(book, bookRequestDto);
        book.setIsDeleted(false);
        return book;
    }

    // Entity to dto
    public BookResponseDto toDto(Book book){
        BookResponseDto bookResponseDto = new BookResponseDto();

        bookResponseDto.setName(book.getName());
        bookResponseDto.setAuthor(book.getAuthor());
        bookResponseDto.setIsbn(book.getIsbn());
        bookResponseDto.setPublisher(book.getPublisher());
        bookResponseDto.setIsDeleted(false);

        return bookResponseDto;
    }

    // dto to entity
    public void updateEntity(Book book,BookRequestDto bookRequestDto){
        book.setName(bookRequestDto.getName());
        book.setAuthor(bookRequestDto.getAuthor());
        book.setIsbn(bookRequestDto.getIsbn());
        book.setPublisher(bookRequestDto.getPublisher());

    }
}
