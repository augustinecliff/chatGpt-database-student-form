// StudentController.java

package com.chatgpt.studentregistration.controller;

import com.chatgpt.studentregistration.errors.StudentNotFoundException;
import com.chatgpt.studentregistration.model.Student;
import com.chatgpt.studentregistration.model.Unit;
import com.chatgpt.studentregistration.service.StudentService;
import com.chatgpt.studentregistration.service.UnitService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final UnitService unitService;
    private final StudentService studentService;
    public StudentController(UnitService unitService, StudentService studentService) {
        this.unitService = unitService;
        this.studentService = studentService;
    }

    @GetMapping("/student-form")
    public String showStudentForm(Model model) {
        List<Unit> units = unitService.getAllUnits();
        model.addAttribute("student", new Student());
        model.addAttribute("units", units);
        return "student-form";
    }

    @PostMapping("/form")
    public String submitStudentForm(@ModelAttribute("student") Student student, @RequestParam(value = "id", required = false) int id) {
        student.setId(id);
        studentService.saveStudent(student);
        return "redirect:/students/list";
    }


    @GetMapping("/list")
    public String showStudentList(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "student-list";
    }

    @GetMapping("/{id}")
    public String showStudentDetails(@PathVariable("id") Long id, Model model) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            model.addAttribute("student", student.get());
            return "student-details";
        } else {
            throw new StudentNotFoundException("Student with ID " + id + " not found");
        }
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @RequestMapping("/error")
    public ModelAndView handleStudentNotFoundException(HttpServletRequest request, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("url", request.getRequestURL() != null ? request.getRequestURL() : "unknown");
        modelAndView.setViewName("error");
        return modelAndView;
    }
}

