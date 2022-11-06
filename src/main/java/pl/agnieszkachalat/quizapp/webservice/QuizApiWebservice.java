package pl.agnieszkachalat.quizapp.webservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.agnieszkachalat.quizapp.dto.CriteriaDto;
import pl.agnieszkachalat.quizapp.dto.QuestionDto;
import pl.agnieszkachalat.quizapp.service.QuestionService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/v1/quizApi")
public class QuizApiWebservice {
    
    @Autowired private QuestionService questionService;
    
    @GetMapping(value = "/randomQuestion") 
    public ResponseEntity<QuestionDto> getRandomQuestion() {
        QuestionDto question = questionService.getRandomQuestion();
        return new ResponseEntity<>(question, HttpStatus.OK);
    }
    
    @GetMapping(value = "/questionsByCriteria")
    public ResponseEntity<List<QuestionDto>> getQuestionsByCriteria(@RequestBody CriteriaDto criteria) {
        List<QuestionDto> questions = questionService.getQuestionsByCriteria(criteria);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
}
