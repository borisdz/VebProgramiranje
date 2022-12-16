package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class Course {
    Long courseId;
    String name;
    String description;
    List<Student> students;
    Teacher teacher;

    public Course(String name, String description){
        Random random = new Random();
        courseId = random.nextLong();
        this.name = name;
        this.description = description;
        students = new ArrayList<>();
    }

}
