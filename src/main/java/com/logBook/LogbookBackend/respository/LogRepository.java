package com.logBook.LogbookBackend.respository;

import com.logBook.LogbookBackend.model.Log;
import com.logBook.LogbookBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
  Log findLogByUser (User user);
}
