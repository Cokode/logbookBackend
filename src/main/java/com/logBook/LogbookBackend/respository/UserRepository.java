package com.logBook.LogbookBackend.respository;

import com.logBook.LogbookBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findUserByID(Long ID);

  User findUserByUserName(String userName);
  User findUserByEmail(String UserEmail);
}
