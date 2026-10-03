package library.example.libraryManagementSystem.service;

import library.example.libraryManagementSystem.dto.BookRequestDto;
import library.example.libraryManagementSystem.dto.BookResponseDto;
import library.example.libraryManagementSystem.entity.Book;
import library.example.libraryManagementSystem.repository.LibraryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class LibraryService {


    private LibraryRepository libraryRepository;
    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public void addBook(BookRequestDto bookRequestDto){

        Book book = new Book();
        book.setName(bookRequestDto.getName());
        book.setIsbn(bookRequestDto.getIsbn());
        book.setAuthor(bookRequestDto.getAuthor());
        book.setPublisher(bookRequestDto.getPublisher());
        book.setIsDeleted(false);
        libraryRepository.save(book);
    }

    public  Optional<BookResponseDto> getBookById(Long id){
      return libraryRepository.findByIdAndIsDeletedFalse(id)
              .map( book -> {
                  BookResponseDto bookResponseDto = new BookResponseDto();
                  bookResponseDto.setName(book.getName());
                  bookResponseDto.setIsbn(book.getIsbn());
                  bookResponseDto.setAuthor(book.getAuthor());
                  bookResponseDto.setPublisher(book.getPublisher());

                  return bookResponseDto;
              });
    }

    public List<BookResponseDto> getAllBook(){
        return libraryRepository.findAllByIsDeletedFalse()
                .stream()
                .map( book-> {
                    BookResponseDto bookResponseDto = new BookResponseDto();
                    bookResponseDto.setName(book.getName());
                    bookResponseDto.setIsbn(book.getIsbn());
                    bookResponseDto.setAuthor(book.getAuthor());
                    bookResponseDto.setPublisher(book.getPublisher());

                    return bookResponseDto;
                })
                .collect(Collectors.toList());
    }

    public BookResponseDto  updateBook(Long id, BookRequestDto bookRequestDto){

        Book bookFound =  libraryRepository.findByIdAndIsDeletedFalse(id).get();
        bookFound.setName(bookRequestDto.getName());
        bookFound.setAuthor(bookRequestDto.getAuthor());
        bookFound.setPublisher(bookRequestDto.getPublisher());
        bookFound.setIsbn(bookRequestDto.getIsbn());

        Book saved = libraryRepository.save(bookFound);
        BookResponseDto responseDto = new BookResponseDto();
        responseDto.setName(saved.getName());
        responseDto.setAuthor(saved.getAuthor());
        responseDto.setIsbn(saved.getIsbn());
        responseDto.setPublisher(saved.getPublisher());
        return responseDto;
    }

    public void deleteBook(Long id){
       Book book =  libraryRepository.findByIdAndIsDeletedFalse(id)
                       .orElseThrow(() -> new RuntimeException("Book not found with this id" + id));

           book.setIsDeleted(true);
           libraryRepository.save(book);

    }





}
