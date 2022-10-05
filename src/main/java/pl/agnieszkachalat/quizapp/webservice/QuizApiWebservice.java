package pl.agnieszkachalat.quizapp.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.agnieszkachalat.quizapp.dto.QuestionDto;
import pl.agnieszkachalat.quizapp.service.QuestionService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value = "/v1/quizApi")
public class QuizApiWebservice {
    
    @Autowired private QuestionService questionService;
    
    @GetMapping(value = "/randomQuestion") 
    public ResponseEntity<QuestionDto> getRandomQuestion() {
        QuestionDto question = questionService.getRandomQuestion();
        return new ResponseEntity<>(question, HttpStatus.OK);
    }
}
