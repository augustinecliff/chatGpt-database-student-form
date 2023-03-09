// StudentService.java
package com.chatgpt.studentregistration.service;

import com.chatgpt.studentregistration.model.Student;
import com.chatgpt.studentregistration.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudentById(int id) {
        studentRepository.deleteById(id);
    }
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }
}
