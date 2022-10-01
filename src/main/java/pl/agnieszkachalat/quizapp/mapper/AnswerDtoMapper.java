package pl.agnieszkachalat.quizapp.mapper;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import pl.agnieszkachalat.quizapp.client.dto.AnswersResponsDto;
import pl.agnieszkachalat.quizapp.client.dto.CorrectAnswersResponseDto;
import pl.agnieszkachalat.quizapp.client.dto.QuestionResponseDto;
import pl.agnieszkachalat.quizapp.dto.AnswerDto;

@Mapper(componentModel = "spring")
public interface AnswerDtoMapper {
    
    public final static String MAP_TO_ANSWER_LIST = "mapToAnswerList";
    
    @Named(MAP_TO_ANSWER_LIST)
    default List<AnswerDto> mapToAnswerDtoList(QuestionResponseDto responseDto) {
        List<AnswerDto> answerDtoList = new ArrayList<>();
        AnswersResponsDto quizApiAnswers = responseDto.getAnswers();
        CorrectAnswersResponseDto quizApiCorrectAnswers = responseDto.getCorrect_answers();
        
        if(StringUtils.isEmpty(quizApiAnswers.getAnswer_a())) {
            answerDtoList.add(new AnswerDto(quizApiAnswers.getAnswer_a(), quizApiCorrectAnswers.isAnswer_a_correct()));
        }
        
        if(StringUtils.isEmpty(quizApiAnswers.getAnswer_b())) {
            answerDtoList.add(new AnswerDto(quizApiAnswers.getAnswer_b(), quizApiCorrectAnswers.isAnswer_b_correct()));
        }
        
        if(StringUtils.isEmpty(quizApiAnswers.getAnswer_c())) {
            answerDtoList.add(new AnswerDto(quizApiAnswers.getAnswer_c(), quizApiCorrectAnswers.isAnswer_c_correct()));
        }
        
        if(StringUtils.isEmpty(quizApiAnswers.getAnswer_d())) {
            answerDtoList.add(new AnswerDto(quizApiAnswers.getAnswer_d(), quizApiCorrectAnswers.isAnswer_d_correct()));
        }
        
        if(StringUtils.isEmpty(quizApiAnswers.getAnswer_e())) {
            answerDtoList.add(new AnswerDto(quizApiAnswers.getAnswer_e(), quizApiCorrectAnswers.isAnswer_e_correct()));
        }
        
        if(StringUtils.isEmpty(quizApiAnswers.getAnswer_f())) {
            answerDtoList.add(new AnswerDto(quizApiAnswers.getAnswer_f(), quizApiCorrectAnswers.isAnswer_f_correct()));
        }
        
        return answerDtoList;
    }
}
