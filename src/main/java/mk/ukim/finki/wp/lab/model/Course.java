package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import mk.ukim.finki.wp.lab.model.enumerations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long courseId;
    String name;
    String description;

    @ManyToMany(fetch = FetchType.EAGER)
    List<Student> students;

    @ManyToOne
    Teacher teacher;

    @Enumerated(EnumType.STRING)
    private Type type;

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
        students = new ArrayList<>();
        type=Type.WINTER;
    }

    public Course() {
    }
}
