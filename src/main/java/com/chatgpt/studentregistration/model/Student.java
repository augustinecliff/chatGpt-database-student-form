package com.chatgpt.studentregistration.model;

import com.chatgpt.studentregistration.model.Unit;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "First Name is required")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "PhoneNumber is required")
    private String phoneNumber;
    @NotBlank(message = "DateOfBirth is required")
    private LocalDate dateOfBirth;
    @NotBlank(message = "gender is required")
    private String gender;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_unit",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "unit_id"))
    private List<Unit> units;
}



