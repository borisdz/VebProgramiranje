package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

public class DataHolder {
    public static List<Course> courses = new ArrayList<>();
    public static List<Student> students = new ArrayList<>();

    @PostConstruct
    public void init() {
        courses.add(new Course("Veb Programiranje", "Veb programiranje so Java Spring", students));
        courses.add(new Course("Verojatnost i Statistika", "Najtezok predmet na FINKI", students));
        students.add(new Student("borisdz", "bd", "Boris", "Dzotov"));
        students.add(new Student("ilijadz", "id", "Ilija", "Dzotov"));
    }
}
