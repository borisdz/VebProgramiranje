package mk.ukim.finki.wp.lab.repository.inMemory;

import lombok.Data;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Data
public class StudentRepository {
    public List<Student> students;

    public StudentRepository() {
        this.students = new ArrayList<>(5);
        students.add(new Student("user1","test","Boris","Djotov"));
        students.add(new Student("user2","test","Ilija","Djotov"));
        students.add(new Student("user3","test","Mihail","Djotov"));
    }

    List<Student> findAllStudents(){
        return students;
    }

    List<Student> findAllByNameOrSurname(String text){
        return students.stream()
                .filter(
                        i->i.getName().contains(text)||i.getSurname().contains(text)
                ).collect(Collectors.toList());
    }

    public void addStudent(Student s){
        students.add(s);
    }
}
