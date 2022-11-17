package mk.ukim.finki.wp.lab.repository;


import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CourseRepository {
    public List<Course> findAllCourses() {
        return DataHolder.courses;
    }

    public Course findById(Long courseId) {
        List<Course> list = DataHolder.courses.stream().filter(s -> s.courseId.equals(courseId)).collect(Collectors.toList());
        return list.get(0);
    }

    public List<Student> findAllStudentsByCourse(Long courseId) {
        return DataHolder.courses.stream().filter(c -> c.courseId.equals(courseId)).findFirst().get().students;
    }

    public Course addStudentToCourse(Student student, Course course) {
        course.students.add(student);
        return course;
    }

    public Optional<Course> findByName(String name){
        return DataHolder.courses.stream().filter(i->i.name.equals(name)).findFirst();
    }

    public Course addCourse(Course course){
        DataHolder.courses.add(course);
        return course;
    }

    public void deleteCourse(Long id) {
        DataHolder.courses.removeIf(i->i.courseId.equals(id));
    }
}
