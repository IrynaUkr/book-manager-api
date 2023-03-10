package com.techreturners.bookmanager.service;

import com.techreturners.bookmanager.exception.BookAlreadyExistException;
import com.techreturners.bookmanager.exception.BookNotFoundException;
import com.techreturners.bookmanager.model.Book;
import com.techreturners.bookmanager.repository.BookManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookManagerServiceImpl implements BookManagerService {

    public static final String BOOK_IS_NOT_FOUND = "book is not found";
    private final BookManagerRepository bookManagerRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookManagerRepository.findAll();
    }

    @Override
    public Book insertBook(Book book) {
        List<Book> bookList = bookManagerRepository
                .findAll()
                .stream()
                .filter(b -> Objects.equals(b.getId(), book.getId()))
                .toList();
        if (bookList.isEmpty()) {
            return bookManagerRepository.save(book);
        } else {
            throw new BookAlreadyExistException("the book is already exist");
        }
    }

    @Override
    public Book getBookById(Long id) {
        return bookManagerRepository
                .findById(id)
                .orElseThrow(() -> new BookNotFoundException(BOOK_IS_NOT_FOUND));
    }

    @Override
    public void updateBookById(Long id, Book book) {
        Book retrievedBook = bookManagerRepository
                .findById(id)
                .orElseThrow(() -> new BookNotFoundException(BOOK_IS_NOT_FOUND));


        retrievedBook.setTitle(book.getTitle());
        retrievedBook.setDescription(book.getDescription());
        retrievedBook.setAuthor(book.getAuthor());
        retrievedBook.setGenre(book.getGenre());

        bookManagerRepository.save(retrievedBook);
    }

    @Override
    public void deleteBookById(Long id) {
        Book persistedBook = bookManagerRepository
                .findById(id)
                .orElseThrow(() -> new BookNotFoundException(BOOK_IS_NOT_FOUND));
        bookManagerRepository.delete(persistedBook);
    }

}
