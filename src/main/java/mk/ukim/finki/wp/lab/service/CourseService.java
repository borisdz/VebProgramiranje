package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Student> listStudentsByCourse(Long courseId);

    Course addStudentInCourse(String username, Long courseId);

    Course findById(Long courseId);

    Optional<Course> findByName(String name);

    Course addCourse(String name, String description, Long teacherId);

    void deleteCourse(Long id);

    List<Course> listAllCourses();
}