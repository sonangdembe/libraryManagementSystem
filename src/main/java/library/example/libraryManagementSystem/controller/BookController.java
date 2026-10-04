package library.example.libraryManagementSystem.controller;


import library.example.libraryManagementSystem.dto.BookRequestDto;
import library.example.libraryManagementSystem.dto.BookResponseDto;
import library.example.libraryManagementSystem.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/library")
public class BookController {

    private BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<String> addBookToLibrary(@RequestBody BookRequestDto bookRequestDto){

        bookService.addBook(bookRequestDto);
        return ResponseEntity.ok("book added successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id){
      Optional<BookResponseDto> foundedBook = bookService.getBookById(id);
    if(foundedBook.isPresent()){
        return ResponseEntity.ok(foundedBook.get());
    }
    return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks(){
     List<BookResponseDto> bookList = bookService.getAllBook();
        return ResponseEntity.ok(bookList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@RequestBody BookRequestDto bookRequestDto, @PathVariable Long id){
        BookResponseDto updatedBook = bookService.updateBook( id, bookRequestDto);

        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return  ResponseEntity.ok("book deleted successfully");
    }



}
