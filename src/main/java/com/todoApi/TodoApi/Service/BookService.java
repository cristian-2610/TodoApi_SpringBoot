package com.todoApi.TodoApi.Service;


import com.todoApi.TodoApi.Model.Book;
import com.todoApi.TodoApi.Model.User;
import com.todoApi.TodoApi.Repository.BookRepository;
import com.todoApi.TodoApi.Repository.UserRepository;
import com.todoApi.TodoApi.Utils.Dto.BookDTO;
import com.todoApi.TodoApi.Utils.Dto.ExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public void save(BookDTO bookDTO) {
        Optional<User> user = userRepository.findByEmail(getUsernameContexHolder());

        try {

            Book book = Book.builder()
                    .title(bookDTO.getTitle())
                    .author(bookDTO.getAuthor())
                    .description(bookDTO.getDescription())
                    .build();

            user.get().getBooks().add(book);
            bookRepository.save(book);

        } catch (Exception e) {
            throw new ExceptionHandler(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR");
        }

    }

    public List<BookDTO> allBook() {

        return userRepository.
                findByEmail(getUsernameContexHolder())
                .get()
                .getBooks().stream()
                .map(i -> BookDTO
                        .builder()
                        .id(i.getId())
                        .author(i.getAuthor())
                        .title(i.getTitle())
                        .description(i.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    public void deleteBook(int id) {

        Optional<User> user = userRepository
                .findByEmail(getUsernameContexHolder());

        Optional<Book> book = user.get().getBooks()
                .stream()
                .filter(s -> s.getId() == (id))
                .findAny();

        if (book.isEmpty())
            throw new ExceptionHandler(HttpStatus.NOT_FOUND, "NOT FOUND");

        user.get()
                .getBooks()
                .remove(book.get());

        bookRepository.deleteById(id);

    }

    @Transactional
    public void updateBook(BookDTO bookDTO, int id) {

        Optional<Book> exist = userRepository
                .findByEmail(getUsernameContexHolder())
                .get()
                .getBooks()
                .stream()
                .filter(s -> s.getId() == (id))
                .findAny();

        if (exist.isEmpty()) throw new ExceptionHandler(HttpStatus.NOT_FOUND, "NOT FOUND");

        bookRepository.updateBook(
                bookDTO.getTitle(),
                bookDTO.getDescription(),
                bookDTO.getAuthor(),
                id
        );

    }

    private String getUsernameContexHolder() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

}
