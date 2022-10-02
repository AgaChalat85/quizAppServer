package pl.agnieszkachalat.quizapp.service;

import java.util.List;
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
    
    public QuestionDto getRandomQuestion() throws QuizApiQuestionNotFoundExcption, QuizApiRequestFailedException {
        List<QuestionResponseDto> result = quizApiClient.getRandomQuestion();
        return questionDtoMapper.mapToQuestionDto(result.get(0));
    } 
}
