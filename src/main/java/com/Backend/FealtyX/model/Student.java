package com.Backend.FealtyX.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Student {
    private int id;
    private String name;
    private int age;
    private String email;
}