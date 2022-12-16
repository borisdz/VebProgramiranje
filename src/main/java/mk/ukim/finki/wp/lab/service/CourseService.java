package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;

import java.util.List;

public interface CourseService {
    List<Student> listStudentsByCourse(Long courseId);
    Course addStudentInCourse(String username, Long courseId);
    Course addCourse(String name,String description,Long teacherId);
    void deleteCourse(Long id);
    List<Course> listAllCourses();
    Course findById(Long id);
    Course findByName(String name);
}