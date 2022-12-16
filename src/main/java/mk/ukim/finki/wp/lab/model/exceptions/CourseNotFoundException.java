package mk.ukim.finki.wp.lab.model.exceptions;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException() {
        super("Course not found!");
    }
}