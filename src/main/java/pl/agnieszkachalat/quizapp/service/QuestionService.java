package pl.agnieszkachalat.quizapp.service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.agnieszkachalat.quizapp.client.QuizApiClient;
import pl.agnieszkachalat.quizapp.client.dto.QuestionResponseDto;
import pl.agnieszkachalat.quizapp.client.exception.QuizApiQuestionNotFoundExcption;
import pl.agnieszkachalat.quizapp.client.exception.QuizApiRequestFailedException;
import pl.agnieszkachalat.quizapp.dto.QuestionDto;
import pl.agnieszkachalat.quizapp.mapper.QuestionDtoMapper;

@Service
@Transactional
public class QuestionService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionService.class.getName());
    
    @Autowired private QuizApiClient quizApiClient;
    @Autowired private QuestionDtoMapper questionDtoMapper;
    
    public QuestionDto getRandomQuestion() {
        
        try {
            List<QuestionResponseDto> result = quizApiClient.getRandomQuestion();
            return questionDtoMapper.mapToQuestionDto(result.get(0));
        } catch(InterruptedException | IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            return null;
        } catch (QuizApiQuestionNotFoundExcption | QuizApiRequestFailedException ex) {
            java.util.logging.Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    } 
}
