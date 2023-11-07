package com.fordevs.springbatchpgsqltokafkatomongocrud.repository;

import com.fordevs.springbatchpgsqltokafkatomongocrud.entity.postgresql.InputStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<InputStudent, Long> {

}
