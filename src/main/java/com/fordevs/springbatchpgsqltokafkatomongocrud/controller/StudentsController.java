package com.fordevs.springbatchpgsqltokafkatomongocrud.controller;

import com.fordevs.springbatchpgsqltokafkatomongocrud.entity.postgresql.InputStudent;
import com.fordevs.springbatchpgsqltokafkatomongocrud.repository.StudentRepository;
import com.fordevs.springbatchpgsqltokafkatomongocrud.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentsController {
    @Autowired
    private StudentService studentService;

    //	getting all users
    @GetMapping("/get-all-users")
    public ResponseEntity<List<InputStudent>> getAllUsers() {
        try {
            List<InputStudent> students = new ArrayList<>(studentService.findAll());

            if (students.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //	getting users by id
    @GetMapping("/users/{id}")
    public ResponseEntity<InputStudent> getUsersById(@PathVariable("id") long id) {
        Optional<InputStudent> userData = studentService.getStudentById(id);

        return userData.map(student -> new ResponseEntity<>(student, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //	Create Student
    @PostMapping("/users")
    public ResponseEntity<InputStudent> createUser(@RequestBody InputStudent student) {
        try {
            InputStudent _student = studentService
                    .save(student);
            return new ResponseEntity<>(_student, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //	Update Student
    @PutMapping("/users/{id}")
    public ResponseEntity<InputStudent> updateUser(@PathVariable("id") long id, @RequestBody InputStudent student) {
        Optional<InputStudent> userData = studentService.getStudentById(id);

        if (userData.isPresent()) {
            InputStudent _student = userData.get();
            _student.setFirstName(student.getFirstName());
            _student.setLastName(student.getLastName());
            _student.setEmail(student.getEmail());
            _student.setIsActive(student.getIsActive());
            _student.setDeptId(student.getDeptId());
            return new ResponseEntity<>(studentService.save(_student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Student
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            studentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete All Student
    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            studentService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
