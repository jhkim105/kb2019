package com.example.demo.base;

import com.example.demo.book.BookSearchController;
import com.example.demo.config.WebMvcTestExclude;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;

@WebMvcTest(controllers = BookSearchController.class, excludeFilters = @ComponentScan.Filter(WebMvcTestExclude.class))
public class AbstractControllerTests {
}
