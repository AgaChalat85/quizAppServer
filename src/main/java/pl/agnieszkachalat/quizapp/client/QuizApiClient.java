package pl.agnieszkachalat.quizapp.client;

import java.io.IOException;
import static pl.agnieszkachalat.quizapp.enums.HttpStatusEnum.*;
import static pl.agnieszkachalat.quizapp.client.exception.ExceptionMessages.*;

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
import pl.agnieszkachalat.quizapp.client.exception.QuizApiQuestionNotFoundExcption;
import pl.agnieszkachalat.quizapp.client.exception.QuizApiRequestFailedException;
import pl.agnieszkachalat.quizapp.dto.CriteriaDto;
import pl.agnieszkachalat.quizapp.enums.HttpStatusEnum;
import pl.agnieszkachalat.quizapp.util.JSONUtils;

@Component
public class QuizApiClient {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(QuizApiClient.class.getName());
    
    @Autowired private HttpClient httpClient;

    @Autowired
    private HttpRequestFactory httpRequestFactory;
    
    public List<QuestionResponseDto> getRandomQuestion() throws QuizApiQuestionNotFoundExcption, QuizApiRequestFailedException {
        HttpRequest randomQuestion = httpRequestFactory.createHttpRequest(new CriteriaDto());
        try {
            HttpResponse<String> response = httpClient.send(randomQuestion, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() != 200) {
                HttpStatusEnum httpStatus = findStatusByCode(response.statusCode());
                String message = String.format(GET_RANDOM_QUESTION_REQUEST_FAILED_WITH_STATUS_RESPONSE, httpStatus.getStatusCode(), httpStatus.getDescription());
                throw new QuizApiRequestFailedException(message);
            }

            LOGGER.info("Quiz Api request for single random question was executed successfully.");
            
            List<QuestionResponseDto> questions = JSONUtils.convertJSONStringToQuestionResponseDtoList(response.body());
            
            if(CollectionUtils.isEmpty(questions)) {
                throw new QuizApiQuestionNotFoundExcption(QUIZ_API_QUESTION_NOT_FOUND);
            }
            
            return questions;
        } catch (IOException | InterruptedException ex) {
            throw new QuizApiRequestFailedException(GET_RANDOM_QUESTION_REQUEST_FAILED, ex);
        }
    }
    public List<QuestionResponseDto> getQuestionByCriteria(String category, String difficulty, Integer limit ) {
        HttpRequest questionsByParameter = httpRequestFactory.createHttpRequest(new CriteriaDto(category, difficulty, limit, null));

        try {
            HttpResponse<String> response = httpClient.send(questionsByParameter, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() != 200) {
                HttpStatusEnum httpStatus = findStatusByCode(response.statusCode());
                String message = String.format(GET_RANDOM_QUESTION_REQUEST_FAILED_WITH_STATUS_RESPONSE, httpStatus.getStatusCode(), httpStatus.getDescription());
                throw new QuizApiRequestFailedException(message);
            }

            LOGGER.info("Quiz Api request for single question by category was executed successfully.");

            List<QuestionResponseDto> questions = JSONUtils.convertJSONStringToQuestionResponseDtoList(response.body());

            if(CollectionUtils.isEmpty(questions)) {
                throw new QuizApiQuestionNotFoundExcption(QUIZ_API_QUESTION_NOT_FOUND);
            }

            return questions;
        } catch (IOException | InterruptedException ex) {
            throw new QuizApiRequestFailedException(GET_RANDOM_QUESTION_REQUEST_FAILED, ex);
        }

    }
    
}
