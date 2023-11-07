package com.fordevs.springbatchpgsqltokafkatomongocrud.services;

import com.fordevs.springbatchpgsqltokafkatomongocrud.entity.postgresql.InputStudent;
import com.fordevs.springbatchpgsqltokafkatomongocrud.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public Optional<InputStudent> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public List<InputStudent> findAll() {
        return studentRepository.findAll();
    }

    public InputStudent save(InputStudent student) {
        return studentRepository.save(student);
    }

    public Optional<InputStudent> deleteById(Long id) {
        Optional<InputStudent> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
        }
        return student;
    }

    public Optional<InputStudent> deleteAll() {
        Optional<InputStudent> student = studentRepository.findById(1L);
        if (student.isPresent()) {
            studentRepository.deleteAll();
        }
        return student;
    }
}


