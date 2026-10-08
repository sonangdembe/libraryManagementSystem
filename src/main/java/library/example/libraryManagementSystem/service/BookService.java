package library.example.libraryManagementSystem.service;

import library.example.libraryManagementSystem.dto.BookRequestDto;
import library.example.libraryManagementSystem.dto.BookResponseDto;
import library.example.libraryManagementSystem.entity.Book;
import library.example.libraryManagementSystem.exception.BookNotFoundException;
import library.example.libraryManagementSystem.mapper.BookMapper;
import library.example.libraryManagementSystem.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BookService {


    private BookRepository bookRepository;
    private BookMapper bookMapper;
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public void addBook(BookRequestDto bookRequestDto){
        Book book = bookMapper.toEntity(bookRequestDto);
        bookRepository.save(book);
    }

    public BookResponseDto getBookById(Long id){
      return bookRepository.findByIdAndIsDeletedFalse(id)
              .map(bookMapper::toDto)
              .orElseThrow(() -> new BookNotFoundException("Book with id " +id + "is not in Database"));

    }

    public List<BookResponseDto> getAllBook(){
        return bookRepository.findAllByIsDeletedFalse()
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookResponseDto  updateBook(Long id, BookRequestDto bookRequestDto){

        Book bookFound =  bookRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BookNotFoundException("book not found with id so cannot update" +id));
        bookMapper.updateEntity(bookFound,bookRequestDto);
        Book saved = bookRepository.save(bookFound);
        return bookMapper.toDto(saved);
    }

    public void deleteBook(Long id){
       Book book =  bookRepository.findByIdAndIsDeletedFalse(id)
                       .orElseThrow(() -> new BookNotFoundException("Book not found with this id so cannot delete" + id));

           book.setIsDeleted(true);
           bookRepository.save(book);

    }





}
