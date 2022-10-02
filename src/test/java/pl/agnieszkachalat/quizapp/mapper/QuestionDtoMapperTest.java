package pl.agnieszkachalat.quizapp.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.agnieszkachalat.quizapp.BaseTest;
import pl.agnieszkachalat.quizapp.client.dto.AnswersResponsDto;
import pl.agnieszkachalat.quizapp.client.dto.CorrectAnswersResponseDto;
import pl.agnieszkachalat.quizapp.client.dto.QuestionResponseDto;
import pl.agnieszkachalat.quizapp.client.dto.TagResponseDto;
import pl.agnieszkachalat.quizapp.dto.AnswerDto;
import pl.agnieszkachalat.quizapp.dto.QuestionDto;
import pl.agnieszkachalat.quizapp.dto.TagDto;

public class QuestionDtoMapperTest extends BaseTest {
    
    @Autowired private QuestionDtoMapper questionDtoMapper;
    
    @Test
    public void thatMappingToQuestionDtoWorks() throws Exception {
        String jsonResponseString = getFileAsString("json/QuestionDtoMapperTest_thatMappingToQuestionDtoWorks.json");
        
        QuestionResponseDto quizApiQuestion = new ObjectMapper().readValue(jsonResponseString, new TypeReference<QuestionResponseDto>() {});
        
        assertNotNull(quizApiQuestion);
        
        QuestionDto question = questionDtoMapper.mapToQuestionDto(quizApiQuestion);
        
        assertNotNull(question);
        assertEquals(quizApiQuestion.getId(), question.getQstId());
        assertEquals(quizApiQuestion.getQuestion(), question.getQuestion());
        assertEquals(quizApiQuestion.getDescription(), question.getDescription());
        assertAnswers(quizApiQuestion, question.getAnswers());
        assertEquals(quizApiQuestion.isMultiple_correct_answers(), question.isMultipleAnswers());
        assertEquals(quizApiQuestion.getExplanation(), question.getExplanation());
        assertEquals(quizApiQuestion.getTip(), question.getTip());
        assertTags(quizApiQuestion.getTags(), question.getTags());
        assertEquals(quizApiQuestion.getCategory(), question.getCategory());
        assertEquals(quizApiQuestion.getDifficulty(), question.getDifficulty());
    }
    
    private void assertAnswers(QuestionResponseDto quizApiQuestion, List<AnswerDto> answers) {
        assertEquals(5, answers.size());
        
        AnswersResponsDto quizApiAnswers = quizApiQuestion.getAnswers();
        CorrectAnswersResponseDto quizApiCorrectAnswers = quizApiQuestion.getCorrect_answers();
        
        assertEquals(quizApiAnswers.getAnswer_a(), answers.get(0).getAnswer());
        assertEquals(quizApiCorrectAnswers.isAnswer_a_correct(), answers.get(0).isCorrect());
        
        assertEquals(quizApiAnswers.getAnswer_b(), answers.get(1).getAnswer());
        assertEquals(quizApiCorrectAnswers.isAnswer_b_correct(), answers.get(1).isCorrect());
        
        assertEquals(quizApiAnswers.getAnswer_c(), answers.get(2).getAnswer());
        assertEquals(quizApiCorrectAnswers.isAnswer_c_correct(), answers.get(2).isCorrect());
        
        assertEquals(quizApiAnswers.getAnswer_d(), answers.get(3).getAnswer());
        assertEquals(quizApiCorrectAnswers.isAnswer_d_correct(), answers.get(3).isCorrect());
        
        assertEquals(quizApiAnswers.getAnswer_e(), answers.get(4).getAnswer());
        assertEquals(quizApiCorrectAnswers.isAnswer_e_correct(), answers.get(4).isCorrect());
    }
    
    private void assertTags(List<TagResponseDto> apiTags, List<TagDto> tags) {
        assertEquals(apiTags.size(), tags.size());
        assertEquals(apiTags.get(0).getName(), tags.get(0).getName());
        assertEquals(apiTags.get(1).getName(), tags.get(1).getName());
    }
}
