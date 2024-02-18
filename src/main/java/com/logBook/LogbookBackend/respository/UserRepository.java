package com.logBook.LogbookBackend.respository;

import com.logBook.LogbookBackend.model.LogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<LogUser, Long> {
  LogUser findUserById(Long id);

  LogUser findUserByUserName(String userName);
  LogUser findUserByEmail(String UserEmail);

  LogUser findUserByPhoneNumber(String phoneNumber);


}

