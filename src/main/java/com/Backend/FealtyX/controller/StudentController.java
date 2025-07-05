package com.Backend.FealtyX.controller;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.*;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.json.JSONObject;

import com.Backend.FealtyX.model.Student;
import com.Backend.FealtyX.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService service;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(service.createStudent(student));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(service.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        Student s = service.getStudentById(id);
        return s != null ? ResponseEntity.ok(s) : ResponseEntity.status(404).body("Student not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Student student) {
        Student updated = service.updateStudent(id, student);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.status(404).body("Student not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return service.deleteStudent(id) ?
                ResponseEntity.ok("Deleted successfully") :
                ResponseEntity.status(404).body("Student not found");
    }

    @GetMapping("/{id}/summary")
    public ResponseEntity<?> getStudentSummary(@PathVariable int id) {
        Student student = service.getStudentById(id);
        if (student == null) {
            return ResponseEntity.status(404).body("Student not found");
        }

        String prompt = String.format("Summarize the profile of this student: Name: %s, Age: %d, Email: %s",
                student.getName(), student.getAge(), student.getEmail());

        try {
            // Use Java 11+ HttpClient
            HttpClient client = HttpClient.newHttpClient();
            String requestBody = String.format("{\"model\":\"llama3\",\"prompt\":\"%s\"}", prompt);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:11434/api/generate"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(response.body(), StandardCharsets.UTF_8));
            StringBuilder summary = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                JSONObject json = new JSONObject(line);
                if (json.has("response")) {
                    summary.append(json.getString("response"));
                }
            }

            return ResponseEntity.ok(summary.toString());

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ollama call failed: " + e.getMessage());
        }
    }

}