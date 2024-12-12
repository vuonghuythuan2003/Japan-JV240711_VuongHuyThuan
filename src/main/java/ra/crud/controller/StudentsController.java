package ra.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ra.crud.model.Classes;
import ra.crud.model.Students;
import ra.crud.service.ClassesService;
import ra.crud.service.StudentsService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/studentsController")

public class StudentsController {
    @Autowired
    private StudentsService studentsService;
    @Autowired
    private ClassesService classesService;

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<Classes> listClasses = classesService.findAll();
        List<Students> listStudents = studentsService.findAll();
        model.addAttribute("listClasses", listClasses);
        model.addAttribute("listStudents", listStudents);
        return "listStudents";
    }

    @GetMapping("/initCreate")
    public String initCreate(Model model) {
        List<Classes> listClasses = classesService.findAll();
        model.addAttribute("listClasses", listClasses);
        model.addAttribute("student", new Students());
        return "createStudent";
    }

    @PostMapping("/create")
    public String create(@Valid Students students,
                         BindingResult bindingResult,
                         @RequestParam("avatarFile") MultipartFile avatarFile,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listClasses", classesService.findAll());
            return "createStudent";
        }

        try {
            boolean result = studentsService.save(students, avatarFile);
            if (result) {
                return "redirect:/studentsController/findAll";
            }
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("listClasses", classesService.findAll());
            return "createStudent";
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Lỗi tải lên file.");
            model.addAttribute("listClasses", classesService.findAll());
            return "createStudent";
        }

        return "error";
    }


    @GetMapping("/initUpdate")
    public String initUpdate(Model model, @RequestParam(name = "id", required = true) Integer studentId) {
        List<Classes> listClasses = classesService.findAll();
        model.addAttribute("listClasses", listClasses);
        if(studentId == null) {
            return "error";
        }
        Students student = studentsService.findById(studentId);
        model.addAttribute("student", student);
        return "updateStudent";
    }

    @PostMapping("/update")
    public String update(@Valid Students student, BindingResult bindingResult, @RequestParam("avatarFile") MultipartFile avatarFile) {
        if (bindingResult.hasErrors()) {
            return "updateStudent";
        }
        try {
            boolean result = studentsService.update(student, avatarFile);
            if (result) {
                return "redirect:/studentsController/findAll";
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id", required = true) Integer studentId) {
        boolean result = studentsService.delete(studentId);
        if (result) {
            return "redirect:/studentsController/findAll";
        }
        return "error";
    }

}
