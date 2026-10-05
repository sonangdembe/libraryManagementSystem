package library.example.libraryManagementSystem.service;

import library.example.libraryManagementSystem.entity.Book;
import library.example.libraryManagementSystem.entity.BorrowBook;
import library.example.libraryManagementSystem.entity.User;
import library.example.libraryManagementSystem.repository.BookRepository;
import library.example.libraryManagementSystem.repository.BorrowBookRepository;
import library.example.libraryManagementSystem.repository.UserRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
public class BorrowBookService {

    BookRepository bookRepository;
    UserRepository userRepository;
    BorrowBookRepository borrowBookRepository;

    public BorrowBookService(BookRepository bookRepository, UserRepository userRepository, BorrowBookRepository borrowBookRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.borrowBookRepository = borrowBookRepository;
    }


    @Transactional
    public void bookBorrow(Long userId,Long bookId){
        User user = userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));

        Book book = bookRepository.findByIdAndIsDeletedFalse(bookId)
                .orElseThrow(() -> new RuntimeException("book id not found"));

        if(borrowBookRepository.existsByBookIdAndReturnDateIsNull(bookId)){
            throw new RuntimeException("Book is already taken");
        }

        BorrowBook borrowBook = new BorrowBook();
        borrowBook.setUser(user);
        borrowBook.setBook(book);
        borrowBook.setBorrowDate(LocalDate.now());

        borrowBookRepository.save(borrowBook);
    }


    public void returnBook(Long bookId){
     BorrowBook bookBorrow  = borrowBookRepository.findByBookIdAndReturnDateIsNull(bookId)
              .orElseThrow(() -> new RuntimeException("book not found"));

        bookBorrow .setReturnDate(LocalDate.now());
        borrowBookRepository.save(bookBorrow);
    }
}
