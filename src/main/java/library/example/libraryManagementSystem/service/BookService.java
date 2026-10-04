package library.example.libraryManagementSystem.service;

import library.example.libraryManagementSystem.dto.BookRequestDto;
import library.example.libraryManagementSystem.dto.BookResponseDto;
import library.example.libraryManagementSystem.entity.Book;
import library.example.libraryManagementSystem.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BookService {


    private BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(BookRequestDto bookRequestDto){

        Book book = new Book();
        book.setName(bookRequestDto.getName());
        book.setIsbn(bookRequestDto.getIsbn());
        book.setAuthor(bookRequestDto.getAuthor());
        book.setPublisher(bookRequestDto.getPublisher());
        book.setIsDeleted(false);
        bookRepository.save(book);
    }

    public  Optional<BookResponseDto> getBookById(Long id){
      return bookRepository.findByIdAndIsDeletedFalse(id)
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
        return bookRepository.findAllByIsDeletedFalse()
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

        Book bookFound =  bookRepository.findByIdAndIsDeletedFalse(id).get();
        bookFound.setName(bookRequestDto.getName());
        bookFound.setAuthor(bookRequestDto.getAuthor());
        bookFound.setPublisher(bookRequestDto.getPublisher());
        bookFound.setIsbn(bookRequestDto.getIsbn());

        Book saved = bookRepository.save(bookFound);
        BookResponseDto responseDto = new BookResponseDto();
        responseDto.setName(saved.getName());
        responseDto.setAuthor(saved.getAuthor());
        responseDto.setIsbn(saved.getIsbn());
        responseDto.setPublisher(saved.getPublisher());
        return responseDto;
    }

    public void deleteBook(Long id){
       Book book =  bookRepository.findByIdAndIsDeletedFalse(id)
                       .orElseThrow(() -> new RuntimeException("Book not found with this id" + id));

           book.setIsDeleted(true);
           bookRepository.save(book);

    }





}
