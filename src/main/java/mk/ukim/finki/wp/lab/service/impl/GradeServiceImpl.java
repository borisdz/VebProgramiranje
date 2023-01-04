package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exceptions.StudentNotInCourseException;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.service.GradeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {
    GradeRepository gradeRepository;

    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }

    @Override
    public Grade findByStudentAndCourse(Student s, Course c) {
        return gradeRepository.findByStudentAndCourse(s,c);
    }

    @Override
    public List<Grade> findAllByCourse(Course c) {
        return gradeRepository.findAllByCourse(c);
    }

    @Override
    public Grade save(Grade g) {
        Course c = g.getCourse();
        Student s = g.getStudent();
        if(!c.getStudents().contains(s))
            throw new StudentNotInCourseException();
        return gradeRepository.save(g);
    }

    @Override
    public void deleteGradeByCourse(Course c) {
        gradeRepository.deleteAllByCourse(c);
    }

    @Override
    public List<Grade> findBetween(LocalDateTime from, LocalDateTime to, Course c) {
        return gradeRepository.findByTimestampBetweenAndCourse(from,to,c);
    }
}
