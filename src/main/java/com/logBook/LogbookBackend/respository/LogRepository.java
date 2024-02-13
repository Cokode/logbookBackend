  package com.logBook.LogbookBackend.respository;

  import com.logBook.LogbookBackend.model.Log;
  import org.springframework.data.repository.CrudRepository;
  import org.springframework.stereotype.Repository;

  @Repository
  public interface LogRepository extends CrudRepository<Log, Long> {
    @Override
    Iterable<Log> findAllById(Iterable<Long> longs);
  }
