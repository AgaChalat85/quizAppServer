package pl.agnieszkachalat.quizapp.service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.agnieszkachalat.quizapp.client.QuizApiClient;
import pl.agnieszkachalat.quizapp.client.dto.QuestionResponseDto;
import pl.agnieszkachalat.quizapp.client.exception.QuizApiException;
import pl.agnieszkachalat.quizapp.dto.QuestionDto;
import pl.agnieszkachalat.quizapp.mapper.QuestionDtoMapper;

@Service
@Transactional
public class QuestionService {
    
    private static final Logger LOGGER = Logger.getLogger(QuestionService.class.getName());
    
    @Autowired private QuizApiClient quizApiClient;
    @Autowired private QuestionDtoMapper questionDtoMapper;
    
    public QuestionDto getRandomQuestion() {
        
        try {
            List<QuestionResponseDto> result = quizApiClient.getRandomQuestion();
            return questionDtoMapper.mapToQuestionDto(result.get(0));
        } catch(QuizApiException | InterruptedException | IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    } 
}
