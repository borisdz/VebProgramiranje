package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exceptions.CourseAlreadyHereException;
import mk.ukim.finki.wp.lab.model.exceptions.CourseNotFoundException;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import mk.ukim.finki.wp.lab.model.exceptions.StudentAlreadyInCourseException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final StudentService studentService;
    private final TeacherService teacherService;

    public CourseServiceImpl(CourseRepository courseRepository, StudentService studentService, TeacherService teacherService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        Course c = courseRepository.findById(courseId).orElseThrow(CourseNotFoundException::new);
        return c.getStudents();
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        Student s = studentService.findByUsername(username);
        Course c = courseRepository.findById(courseId).orElseThrow(CourseNotFoundException::new);

        if (!c.getStudents().contains(s)) {
            c.getStudents().add(s);
            courseRepository.save(c);
        }else
            throw new StudentAlreadyInCourseException();
        return c;
    }

    @Override
    @Transactional
    public Course addCourse(String name, String description, Long teacherId) {
        if(courseRepository.findByName(name).isPresent())
            throw new CourseAlreadyHereException();
        Course vrati = new Course(name, description);
        vrati.setTeacher(teacherService.findById(teacherId));
        courseRepository.save(vrati);
        return vrati;
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> listAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        if(courseRepository.findById(id).isPresent())
            return courseRepository.findById(id).get();
        throw new CourseNotFoundException();
    }

    @Override
    public Course findByName(String name) {
        if(courseRepository.findByName(name).isPresent())
            return courseRepository.findByName(name).get();
        throw new CourseNotFoundException();
    }
}
