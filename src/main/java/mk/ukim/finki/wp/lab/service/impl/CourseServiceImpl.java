package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exceptions.CourseAlreadyExistsException;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentService studentService;
    private final TeacherService teacherService;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, StudentService studentService, TeacherService teacherService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
        this.teacherService = teacherService;
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

    @Override
    public Course findById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public Optional<Course> findByName(String name) {
        return courseRepository.findByName(name);
    }

    @Override
    public Course addCourse(String name, String description, Long teacherId) {
        if(courseRepository.findByName(name).isPresent())
            throw new CourseAlreadyExistsException(name);
        Course kurs= new Course(name,description);
        kurs.setTeacher(teacherService.findById(teacherId));
        courseRepository.addCourse(kurs);

        return kurs;
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteCourse(id);
    }

    @Override
    public List<Course> listAllCourses() {
        return courseRepository.findAllCourses();
    }
}
