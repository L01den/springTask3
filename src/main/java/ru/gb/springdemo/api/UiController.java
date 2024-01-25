package ru.gb.springdemo.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springdemo.service.UiService;

/**
 * Created by Lorden on 21.01.2024
 */

//   * 1.1 Реализовать контроллер по управлению книгами с ручками:
//   GET /book/{id} - получить описание книги,
//   DELETE /book/{id} - удалить книгу,
//   POST /book - создать книгу

@Controller
@RequestMapping("/ui")
public class UiController {

    private final UiService uiService;

    public UiController(UiService uiService) {
        this.uiService = uiService;
    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books", uiService.findBookAll());
        return "uiBooks";
    }

    @GetMapping("/readers")
    public String getReader(Model model) {
        model.addAttribute("readers", uiService.findReaderAll());
        return "uiReader";
    }

    @GetMapping("/issues")
    public String getIssues(Model model) {
        model.addAttribute("issues", uiService.getIssues());
        return "uiIssues";
    }
    @GetMapping("/reader/{id}")
    public String getIssuesByReaderId(@PathVariable long id, Model model){
        model.addAttribute("issues", uiService.getIssuesByReaderId(id));
        return "booksReader";
    }


}
