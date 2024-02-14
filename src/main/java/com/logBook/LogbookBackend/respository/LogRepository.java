package com.logBook.LogbookBackend.respository;

import com.logBook.LogbookBackend.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
//  Log findLogByUser (LogUser user);
}
