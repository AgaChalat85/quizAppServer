package pl.agnieszkachalat.quizapp.exception;
    
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.agnieszkachalat.quizapp.client.exception.QuizApiQuestionNotFoundExcption;
import pl.agnieszkachalat.quizapp.client.exception.QuizApiRequestFailedException;

@ControllerAdvice
public class QuizApiWebserviceExceptionHandler {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(QuizApiWebserviceExceptionHandler.class);
    
    @ExceptionHandler(value = QuizApiRequestFailedException.class) 
    public ResponseEntity<String> handleQuizApiRequestFailedException(QuizApiRequestFailedException ex) {
        LOGGER.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = QuizApiQuestionNotFoundExcption.class) 
    public ResponseEntity<String> handleQuizApiQuestionNotFoundExcption(QuizApiQuestionNotFoundExcption ex) {
        LOGGER.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
