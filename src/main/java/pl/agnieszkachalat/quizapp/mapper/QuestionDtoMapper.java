package pl.agnieszkachalat.quizapp.mapper;

import java.util.List;
import static pl.agnieszkachalat.quizapp.mapper.AnswerDtoMapper.*;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.agnieszkachalat.quizapp.client.dto.QuestionResponseDto;
import pl.agnieszkachalat.quizapp.dto.QuestionDto;

@Mapper(componentModel = "spring", uses = {
    AnswerDtoMapper.class,
    TagDtoMapper.class
})
public interface QuestionDtoMapper {
    
    @Mapping(source = "id", target = "qstId")
    @Mapping(source = "questionResponseDto", target = "answers", qualifiedByName = MAP_TO_ANSWER_LIST)
    @Mapping(source = "multiple_correct_answers", target = "multipleAnswers")
    QuestionDto mapToQuestionDto(QuestionResponseDto questionResponseDto);
    
    List<QuestionDto> mapToQuestionDtoList(List<QuestionResponseDto> questionResponseDtoList);
}
