package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentService studentService;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, StudentService studentService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findAllStudentsByCourse(courseId);
    }

    public List<Course> listAll(){
        return courseRepository.findAllCourses();
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Course course = courseRepository.findById(courseId);
        List<Student> students = studentService.listAll();
        Student student = null;
        for (Student s : students) {
            if (s.username.equals(username)) {
                student = s;
                break;
            }
        }
        return courseRepository.addStudentToCourse(student, course);
    }
}
