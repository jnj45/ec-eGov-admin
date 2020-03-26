package net.ecbank.fwk.admin.sample.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.ecbank.fwk.admin.sample.entity.Book;
import net.ecbank.fwk.admin.sample.service.BookService;

@Controller
@RequestMapping("/sample")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@GetMapping("/bookList")
	public String bookList() {
		return "sample/bookList";
	}
	
	@PostMapping("/bookDetail")
	public String bookDetail(@RequestParam Long bookId, Model model) {
		Book findBook = bookService.findBook(bookId);
		model.addAttribute("book", findBook);
		return "sample/bookDetail";
	}
}
