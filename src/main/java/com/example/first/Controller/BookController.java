package com.example.first.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.first.BookServices.Bookservice;
import com.example.first.Entities.Book;

@RestController
public class BookController {
    @Autowired
    Bookservice bookservice;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> list = this.bookservice.findAll();
        if (list.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
        return ResponseEntity.of(Optional.of(list));
    }

    @GetMapping("books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
        Book book = null;
        book = this.bookservice.findById(id);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(book);
    }

    @PostMapping("/books")
    public void addBook(@RequestBody Book book) {
        this.bookservice.addBook(book);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable("id") int id) {
        this.bookservice.deleteBook(id);
    }

    @PutMapping("/books/{id}")
    public void updateBook(@RequestBody Book book, @PathVariable("id") int id) {
        this.bookservice.updateBook(book, id);
    }

}
