package mk.ukim.finki.wp.lab.model.exceptions;

public class InvalidStudentException extends RuntimeException{
    public InvalidStudentException() {
        super("Invalid user,something went wrong! Start over!");
    }
}