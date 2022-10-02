package pl.agnieszkachalat.quizapp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.slf4j.LoggerFactory;
import pl.agnieszkachalat.quizapp.client.dto.QuestionResponseDto;

public class JSONUtils {
    
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(JSONUtils.class.getName());
    
    public static List<QuestionResponseDto> convertJSONStringToQuestionResponseDtoList(String jsonString) {
        
        try {
            return new ObjectMapper().readValue(jsonString, new TypeReference<List<QuestionResponseDto>>() {});
        } catch (JsonProcessingException ex) {
            LOGGER.error("Convertion from JSON String to Java Object failed!", ex);
            return null;
        }
    }
}
