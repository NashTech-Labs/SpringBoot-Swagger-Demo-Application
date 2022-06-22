package com.knoldus.springbootswagger.service;

import com.knoldus.springbootswagger.model.Student;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jfairy.Fairy;
import org.springframework.stereotype.Service;

/**
 * this StudentService is used to map student from database.
 * copyright : knoldus Inc.
 * version : 11.0.11
 * @author shashikant on 22/06/2022
 */
@Service
public class StudentService {
    private static Map<Integer, Student> studentDB;
    private Fairy fairy = Fairy.create();

    @PostConstruct
    public void init() throws Exception {
        studentDB = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            Student student = new Student(i, fairy.person());
            studentDB.put(new Integer(i), student);
        }
    }

    public List<Student> getAll(){
        List<Student> studentList = studentDB.entrySet().stream()
                .map(Map.Entry::getValue).collect(Collectors.toList());
        return studentList;
    }

    public Student getStudentById(Integer studentId) {
        return studentDB.get(studentId);
    }

    public List<Student> filterByAge(Integer age) {
        List<Student> studentList = studentDB.entrySet().stream().filter(e -> e.getValue().getAge() > age)
                .map(Map.Entry::getValue).collect(Collectors.toList());
        return studentList;
    }

    public List<Student> filterByCity(String cityName) {
        List<Student> studentList = studentDB.entrySet().stream()
                .filter(e -> e.getValue().getAddress().getCity().equals(cityName)).map(Map.Entry::getValue)
                .collect(Collectors.toList());
        return studentList;
    }

    public List<Student> filterByAgeAndCity(Integer age, String cityName) {
        List<Student> studentList = studentDB.entrySet().stream()
                .filter(e -> e.getValue().getAddress().getCity().equals(cityName) && e.getValue().getAge() > age)
                .map(Map.Entry::getValue).collect(Collectors.toList());
        return studentList;
    }
}