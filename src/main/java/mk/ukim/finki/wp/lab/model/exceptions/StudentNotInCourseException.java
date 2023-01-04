package mk.ukim.finki.wp.lab.model.exceptions;

public class StudentNotInCourseException extends RuntimeException {
    public StudentNotInCourseException(){
        super("Student not in course exception!");
    }
}
