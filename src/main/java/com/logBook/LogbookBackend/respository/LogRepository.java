package com.logBook.LogbookBackend.respository;

import com.logBook.LogbookBackend.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {
  List<Log> findLogByLogUserEmailAndLogUserPassword(String email, String password);
  List<Log> findLogByLogUserId(long id);
}