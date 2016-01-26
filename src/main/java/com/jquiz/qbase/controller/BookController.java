package com.jquiz.qbase.controller;

import com.jquiz.qbase.domain.Book;
import com.jquiz.qbase.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class BookController {

    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getBook(Model model){
        List<Book> books = this.bookRepository.listAll();

        model.addAttribute("books",books);
        return "index";
    }

    @RequestMapping(value = "addBook", method = RequestMethod.GET)
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @RequestMapping(value = "addBook", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book){
        this.bookRepository.addBook(book);
        return "redirect:/";
    }

}
