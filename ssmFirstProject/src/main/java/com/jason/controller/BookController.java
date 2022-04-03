package com.jason.controller;

import com.jason.pojo.Books;
import com.jason.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    @Qualifier("booksServiceImpl")
    private BooksService booksService;

    //查询全部的书籍并且返回到一个书籍展示页面
    @RequestMapping("/allBooks")
    public String list(Model model){
        List<Books> books = booksService.queryAllBooks();
        model.addAttribute("books",books);
        return "allBooks";
    }
    //跳转到增加书籍页面
    @RequestMapping("/addBookPaper")
    public String toAddBook(Books books){
        return "addBookPaper";
    }

    //新增书籍操作
    @RequestMapping("/addBook")
    public String addBook(Books books){
        booksService.addBook(books);
        return "redirect:/book/allBooks";
    }
    @RequestMapping("/toUpdatePaper")
    public String toUpdatePaper(int id,Model model){
        Books books = booksService.queryBookById(id);
        model.addAttribute("books",books);
        return "updatePaper";
    }
    @RequestMapping("/updateBook")
    public String updateBook(Books books){
        booksService.updateBook(books);
        return "redirect:/book/allBooks";
    }
    @RequestMapping("/deleteBook/{bookID}")
    public String deleteBook(@PathVariable("bookID") int id){
        booksService.deleteBookById(id);
        return "redirect:/book/allBooks";
    }
}
