package io.spring.bookstore;

import io.spring.bookstore.domain.Book;
import io.spring.bookstore.domain.User;
import io.spring.bookstore.service.BookService;
import io.spring.bookstore.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}


	@Bean
	CommandLineRunner run(UserService userService, BookService bookService) {
		return args-> {

//			userService.saveUser(new User("Kachi", "Malaka", "Kachi", "1234", new Date(80,1,11)));
//
//			bookService.saveBook(new Book("Before We Were Yours: A Novel", "Lisa Wingate", 340.0, false));
//			bookService.saveBook(new Book("When Never Comes", "Barbara Davis", 179.0, false));
//			bookService.saveBook(new Book("Giraffes Can't Dance", "Giles Andreae, Guy Parker-Rees", 200.5, false));
//			bookService.saveBook(new Book("The Great Alone: A Novel Kristin Hannah", "Kristin Hannah", 495.0, true));
//			bookService.saveBook(new Book("An American Princess: The Many Lives of Allene Tew", "Annejet van der Zijl, Michele Hutchison", 149.0, true));
//			bookService.saveBook(new Book("Medical Medium Life-Changing Foods", "Anthony William", 929.7, false));
//			bookService.saveBook(new Book("Vegan 100", "Gaz Oakley", 897.96, false));
//			bookService.saveBook(new Book("Have You Filled A Bucket Today?", "Carol McCloud", 290.06, false));
//			bookService.saveBook(new Book("The Very Hungry Caterpillar", "Eric Carle", 208.51, false));
//			bookService.saveBook(new Book("The Hate U Give", "Angie Thomas", 319.16, false));
//			bookService.saveBook(new Book("The Very Hungry Caterpillar", "Kate Quinn", 208.51, false));
//			bookService.saveBook(new Book("Harry Potter - A History of Magic", "British Library", 1379.78, false));

		};
	}

}
