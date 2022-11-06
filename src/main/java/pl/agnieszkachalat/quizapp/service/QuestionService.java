package pl.agnieszkachalat.quizapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.agnieszkachalat.quizapp.client.QuizApiClient;
import pl.agnieszkachalat.quizapp.client.dto.QuestionResponseDto;
import pl.agnieszkachalat.quizapp.dto.CriteriaDto;
import pl.agnieszkachalat.quizapp.dto.QuestionDto;
import pl.agnieszkachalat.quizapp.mapper.QuestionDtoMapper;

@Service
@Transactional
public class QuestionService {
    
    @Autowired private QuizApiClient quizApiClient;
    @Autowired private QuestionDtoMapper questionDtoMapper;
    
    public QuestionDto getRandomQuestion() {
        List<QuestionResponseDto> result = quizApiClient.getRandomQuestion();
        return questionDtoMapper.mapToQuestionDto(result.get(0));
    } 
    
    public List<QuestionDto> getQuestionsByCriteria(CriteriaDto criteria) {
        List<QuestionResponseDto> result = quizApiClient.getQuestionByCriteria(criteria);
        return questionDtoMapper.mapToQuestionDtoList(result);
    }
}
