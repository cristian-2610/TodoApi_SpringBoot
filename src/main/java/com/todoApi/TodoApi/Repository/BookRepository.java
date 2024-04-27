package com.todoApi.TodoApi.Repository;


import com.todoApi.TodoApi.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Integer> {


    @Modifying
    @Query(value = "UPDATE book SET title=:title,description=:description, author=:author WHERE id=:id", nativeQuery = true)
    void updateBook(@Param("title") String title, @Param("description") String description, @Param("author") String author, @Param("id") int id);

}
