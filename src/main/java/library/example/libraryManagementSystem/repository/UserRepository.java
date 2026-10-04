package library.example.libraryManagementSystem.repository;



import library.example.libraryManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

   Optional<User> findByIdAndIsDeletedFalse(Long id);
   List<User> findAllByIsDeletedFalse();
}

