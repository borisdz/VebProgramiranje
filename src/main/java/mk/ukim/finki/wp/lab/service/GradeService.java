package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;

import java.time.LocalDateTime;
import java.util.List;

public interface GradeService {
    List<Grade> findAll();
    Grade findByStudentAndCourse(Student s, Course c);
    List<Grade> findAllByCourse(Course c);
    Grade save(Grade g);
    void deleteGradeByCourse(Course c);
    List<Grade> findBetween(LocalDateTime from, LocalDateTime to, Course c);
}
