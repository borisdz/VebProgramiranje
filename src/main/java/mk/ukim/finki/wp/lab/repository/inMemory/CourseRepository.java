package mk.ukim.finki.wp.lab.repository.inMemory;

import lombok.Data;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Data
public class CourseRepository {
    List<Course> courses;

    public CourseRepository(){
        this.courses = new ArrayList<>(5);
        /* Course a = new Course("Веб Програмирање","Web Programming");
        Course b = new Course("Оперативни Системи","OS");
        Course c = new Course("Електронска и мобилна трговија","EIMT");
        Course d = new Course("Компјутерски мрежи","KM");*/
    }

    public List<Course> findAllCourses(){
        return courses;
    }

    public Optional<Course> findById(Long courseId){
        return courses.stream()
                .filter(i->i.getCourseId().equals(courseId))
                .findFirst();
    }

    public List<Student> findAllStudentsByCourse(Long courseId){
        return findById(courseId).get().getStudents();
    }

    public Course addStudentToCourse(Student student, Course course){
        course.getStudents().add(student);
        return course;
    }

    public Course addCourse(Course course){
        courses.add(course);
        return course;
    }

    public void deleteCourse(Long id){
        courses.removeIf(i->i.getCourseId().equals(id));
    }

    public Optional<Course> findByName(String name){
        return courses.stream().
                filter(i->i.getName().equals(name))
                .findFirst();
    }
}
