package library.example.libraryManagementSystem.controller;

import library.example.libraryManagementSystem.entity.BorrowBook;
import library.example.libraryManagementSystem.service.BorrowBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    private BorrowBookService borrowBookService;
    public BorrowController(BorrowBookService borrowBookService){
        this.borrowBookService = borrowBookService;
    }

    @PostMapping("/user/{userId}/book/{bookId}")
    public ResponseEntity<String> bookBorrow(@PathVariable Long userId,
                                             @PathVariable Long bookId){
       borrowBookService.bookBorrow(userId, bookId);
         return ResponseEntity.ok("Done");
    }

    @PatchMapping("/{bookId}")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId){
        borrowBookService.returnBook(bookId);
        return ResponseEntity.ok("you return book.Thank you");
    }


}
