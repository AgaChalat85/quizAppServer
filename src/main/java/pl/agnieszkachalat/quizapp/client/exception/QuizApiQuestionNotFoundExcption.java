package pl.agnieszkachalat.quizapp.client.exception;

public class QuizApiQuestionNotFoundExcption extends RuntimeException {
    
    public QuizApiQuestionNotFoundExcption(String message) {
        super(message);
    }
}
