package com.techreturners.bookmanager.repository;

import com.techreturners.bookmanager.model.Book;
import com.techreturners.bookmanager.model.Genre;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookManagerRepositoryTests {

    @Autowired
    private BookManagerRepository bookManagerRepository;
    Book book;

    @BeforeEach
    void setUp(){
        book = new Book(1L, "Book One", "This is the description for Book One", "Person One", Genre.Education);

    }

    @Test
    void testFindAllBooksReturnsBooks() {

        bookManagerRepository.save(book);

        Iterable<Book> books = bookManagerRepository.findAll();
        assertThat(books).hasSize(1);

    }

    @Test
    void testCreatesAndFindBookByIdReturnsBook() {
        bookManagerRepository.save(book);

        var bookById = bookManagerRepository.findById(book.getId());
        assertThat(bookById).isNotNull();
    }

    @Test
    void testDeleteBookById() {
        bookManagerRepository.save(book);
        bookManagerRepository.delete(book);
        List<Book> all = bookManagerRepository.findAll();
        assertThat(all).isEmpty();
    }

}
