package mk.ukim.finki.wp.lab.repository.inMemory;

import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TeacherRepository {
    List<Teacher> teacherList;

    public TeacherRepository() {
        this.teacherList = new ArrayList<>(5);
//        teacherList.add(new Teacher(0L,"Riste","Stojanov"));
//        teacherList.add(new Teacher(1L,"Sasho","Gramatikov"));
//        teacherList.add(new Teacher(2L,"Dragan","Bozhinoski"));
//        teacherList.add(new Teacher(3L,"Kostadin","Mishev"));
//        teacherList.add(new Teacher(4L,"Ana","Todorovska"));
    }
    public List<Teacher> findAll()
    {
        return teacherList;
    }
    public Teacher findById(Long id)
    {
        return teacherList.stream().filter(t -> t.getId().equals(id)).collect(Collectors.toList()).get(0);
    }
//    public void addTeacher(String name,String surname,Long id)
//    {
//        teacherList.add(new Teacher(id,name,surname));
//    }
    public void deleteTeacher(Long id)
    {
        teacherList.removeIf(t -> t.getId().equals(id));
    }
}