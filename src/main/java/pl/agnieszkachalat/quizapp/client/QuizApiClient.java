package pl.agnieszkachalat.quizapp.client;

import static pl.agnieszkachalat.quizapp.enums.HttpStatusEnum.*;
import static pl.agnieszkachalat.quizapp.client.exception.ExceptionMessages.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pl.agnieszkachalat.quizapp.client.dto.QuestionResponseDto;
import pl.agnieszkachalat.quizapp.client.exception.QuizApiException;
import pl.agnieszkachalat.quizapp.enums.HttpStatusEnum;

@Component
public class QuizApiClient {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(QuizApiClient.class.getName());
    
    @Autowired private HttpClient httpClient;
    @Autowired private HttpRequest randomQuestionRequest;
    
    public List<QuestionResponseDto> getRandomQuestion() throws IOException, InterruptedException, QuizApiException {
        
        HttpResponse<String> response = httpClient.send(randomQuestionRequest, HttpResponse.BodyHandlers.ofString());
        
        if(response.statusCode() != 200) {
            HttpStatusEnum httpStatus = findStatusByCode(response.statusCode());
            String message = String.format(GET_RANDOM_QUESTION_REQUEST_FAILED, httpStatus.getStatusCode(), httpStatus.getDescription());
            throw new QuizApiException(message);
        }
        
        LOGGER.info("Quiz Api request for single random question was executed successfully.");
        
        List<QuestionResponseDto> questions = new ObjectMapper().readValue(response.body(), new TypeReference<List<QuestionResponseDto>>() {});
        
        if(CollectionUtils.isEmpty(questions)) {
            throw new QuizApiException(THERE_ARE_ANY_QUESTIONS);
        }
        
        return questions;
    }
    
}
