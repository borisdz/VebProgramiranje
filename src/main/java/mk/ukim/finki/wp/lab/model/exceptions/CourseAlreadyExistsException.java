package mk.ukim.finki.wp.lab.model.exceptions;

public class CourseAlreadyExistsException extends RuntimeException{
    public CourseAlreadyExistsException(String courseName) {
        super(String.format("Course with name %s already exists!", courseName));
    }

    public CourseAlreadyExistsException() {
        super("Course with that name already exists!");
    }
}
