package library.example.libraryManagementSystem.repository;

import library.example.libraryManagementSystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIdAndIsDeletedFalse(Long id);
    List<Book> findAllByIsDeletedFalse();

}
