package com.Backend.FealtyX.service;

import com.Backend.FealtyX.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StudentService {
    private final Map<Integer, Student> studentMap = new ConcurrentHashMap<>();

    public Student createStudent(Student student) {
        studentMap.put(student.getId(), student);
        return student;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(studentMap.values());
    }

    public Student getStudentById(int id) {
        return studentMap.get(id);
    }

    public Student updateStudent(int id, Student student) {
        if (!studentMap.containsKey(id)) return null;
        student.setId(id);
        studentMap.put(id, student);
        return student;
    }

    public boolean deleteStudent(int id) {
        return studentMap.remove(id) != null;
    }
}