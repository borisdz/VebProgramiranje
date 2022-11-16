package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class Student {
    public String username;
    public String password;
    public String name;
    public String surname;

    public Student(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

}
