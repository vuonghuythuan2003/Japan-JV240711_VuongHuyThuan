package ra.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.crud.model.Classes;
import ra.crud.service.ClassesService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/classesController")
public class ClassesController {
    @Autowired
    private ClassesService classesService;

    @GetMapping("/findAll")
    public String findAll(@RequestParam(defaultValue = "1") int page,
                          @RequestParam(defaultValue = "5") int size,
                          Model model) {
        List<Classes> paginatedClasses = classesService.findPaginated(page, size);
        List<Classes> allClasses = classesService.findAll();
        int totalItems = allClasses.size();
        int totalPages = (int) Math.ceil((double) totalItems / size);

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("classesList", paginatedClasses);
        return "listClasses";
    }

    @GetMapping("/initCreate")
    public String initCreate(Model model) {
        model.addAttribute("classes", new Classes());
        return "createClasses";
    }

    @PostMapping("/create")
    public String create(@Valid Classes classes, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "createClasses";
        }

        boolean nameExists = classesService.findAll()
                .stream()
                .anyMatch(existingClass ->
                        existingClass.getClassName().equalsIgnoreCase(classes.getClassName()));

        if (nameExists) {
            model.addAttribute("errorMessage", "Tên lớp đã tồn tại. Vui lòng nhập tên khác.");
            model.addAttribute("classes", classes);
        return "createClasses";
    }

        boolean result = classesService.save(classes);
        if (result) {
            return "redirect:/classesController/findAll";
        }
        return "error";
    }


    @GetMapping("/initUpdate")
    public String initUpdate(Model model, @RequestParam(name = "id", required = true) Integer classId) {
        if(classId == null) {
            return "error";
        }

        Classes classes = classesService.findById(classId);
        model.addAttribute("classes", classes);
        return "updateClasses";
    }

    @PostMapping("/update")
    public String update(@Valid Classes classes, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "updateClasses";
        }
        boolean nameExists = classesService.findAll()
                .stream()
                .anyMatch(existingClass ->
                        existingClass.getClassName().equalsIgnoreCase(classes.getClassName()));

        if (nameExists) {
            model.addAttribute("errorMessage", "Tên lớp đã tồn tại. Vui lòng nhập tên khác.");
            model.addAttribute("classes", classes);
            return "updateClasses";
        }
        boolean result = classesService.update(classes);
        if(result) {
            return "redirect:/classesController/findAll";
        }
        return "error";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id", required = true) Integer classId) {
        boolean result = classesService.delete(classId);
        if(result) {
            return "redirect:/classesController/findAll";
        }
        return "error";
    }
}
