// StudentController.java

package com.studentregistration.controller;

import com.studentregistration.errors.StudentNotFoundException;
import com.studentregistration.model.Student;
import com.studentregistration.model.Unit;
import com.studentregistration.service.StudentService;
import com.studentregistration.service.UnitService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final UnitService unitService;
    public StudentController(StudentService studentService, UnitService unitService) {
        this.studentService = studentService;
        this.unitService = unitService;
    }

    @GetMapping("/student-form")
    public String showStudentForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("units",unitService.getAllUnits());

        return "student-form";
    }

    @GetMapping("/new")
    public String showNewStudentForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("units",unitService.getAllUnits());

        return "student-form";
    }

    @PostMapping("/student-form")
    public String submitStudentForm(@ModelAttribute("student") Student student,
                                    @RequestParam(value = "unitIds", required = false) List<Integer> unitIds) {
        if(unitIds != null) {

            List<Unit> units = new ArrayList<>();
            for(Integer unitId : unitIds) {
                units.add(unitService.getUnitById(unitId));
            }

            student.setUnits(units);
        }
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
    public String showStudentDetails(@PathVariable("id") int id, Model model) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            model.addAttribute("student", student.get());

            return "student-details";
        } else {
            throw new StudentNotFoundException("Student with ID " + id + " not found");
        }
    }
    @GetMapping("/edit/{id}")
    public String showEditStudentForm(@PathVariable("id") int id, Model model) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            model.addAttribute("student", student.get());
            model.addAttribute("units",unitService.getAllUnits());

            return "edit-student";
        } else {
            throw new StudentNotFoundException("Student with ID " + id + " not found");
        }
    }
    @PostMapping("/edit")
    public String updateStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudentById(id);
        return "redirect:/students/list";
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

