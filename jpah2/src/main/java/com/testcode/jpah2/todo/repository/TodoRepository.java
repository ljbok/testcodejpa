package com.testcode.jpah2.todo.repository;

import com.testcode.jpah2.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {

    // id로 todo 찾기
    Optional<Todo> findById(Long id);
    
    // id로 todo 삭제
    void deleteById(Long id);
}
