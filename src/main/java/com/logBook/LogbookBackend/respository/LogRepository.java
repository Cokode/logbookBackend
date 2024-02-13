  package com.logBook.LogbookBackend.respository;

  import com.logBook.LogbookBackend.model.Log;
  import com.logBook.LogbookBackend.model.User;
  import org.springframework.data.repository.CrudRepository;
  import org.springframework.stereotype.Repository;

  @Repository
  public interface LogRepository extends CrudRepository<User, Long> {
    @Override
    Iterable<User> findAllById(Iterable<Long> longs);
  }
