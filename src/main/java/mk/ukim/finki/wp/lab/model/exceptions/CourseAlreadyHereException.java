package mk.ukim.finki.wp.lab.model.exceptions;

public class CourseAlreadyHereException extends RuntimeException{
    public CourseAlreadyHereException() {
        super("Course with same name already added!");
    }
}