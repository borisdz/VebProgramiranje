package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TeacherRepository {

    List<Teacher> teachers;

    public TeacherRepository() {
        this.teachers = new ArrayList<>();

        teachers.add(new Teacher(1L,"Ana", "Todorovska"));
        teachers.add(new Teacher(2L,"Lasko", "Basnarkov"));
        teachers.add(new Teacher(3L,"Sasho", "Gramatikov"));
        teachers.add(new Teacher(4L,"Marjan", "Gushev"));
        teachers.add(new Teacher(5L,"Riste", "Stojanov"));
    }

    public List<Teacher> findAll(){
        return teachers;
    }

    public Optional<Teacher> findById(Long id){
        return teachers.stream().filter(i->i.getId().equals(id)).findFirst();
    }
}
