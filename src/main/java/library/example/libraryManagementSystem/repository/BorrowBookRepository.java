package library.example.libraryManagementSystem.repository;

import library.example.libraryManagementSystem.entity.BorrowBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowBookRepository extends JpaRepository<BorrowBook, Long> {
   Boolean existsByBookIdAndReturnDateIsNull(Long bookId);
   Optional<BorrowBook> findByBookIdAndReturnDateIsNull(Long id);

}
