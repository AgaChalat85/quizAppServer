package pl.agnieszkachalat.quizapp.client.exception;

public class QuizApiRequestFailedException extends RuntimeException {
    
    public QuizApiRequestFailedException(String message) {
        super(message);
    }
    
    public QuizApiRequestFailedException(String message, Throwable ex) {
        super(message, ex);
    }
    
}
