package pl.agnieszkachalat.quizapp.client;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import static pl.agnieszkachalat.quizapp.enums.HttpStatusEnum.*;
import static pl.agnieszkachalat.quizapp.client.exception.ExceptionMessages.*;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.agnieszkachalat.quizapp.BaseTest;
import pl.agnieszkachalat.quizapp.client.dto.QuestionResponseDto;
import pl.agnieszkachalat.quizapp.client.exception.QuizApiQuestionNotFoundExcption;
import pl.agnieszkachalat.quizapp.client.exception.QuizApiRequestFailedException;
import pl.agnieszkachalat.quizapp.dto.CriteriaDto;

public class QuizApiClientTest extends BaseTest {
    
    @InjectMocks private QuizApiClient quizApiClient;
    @Mock private HttpClient httpClient;
    @Mock private HttpRequest request;
    @Mock private HttpRequestFactory httpRequestFactory;
    @Mock private HttpResponse response;
    
    @BeforeEach
    public void init() {
         Mockito.when(httpRequestFactory.createHttpRequest(ArgumentMatchers.any(CriteriaDto.class))).thenReturn(request);
    }
    
    
    @Test
    public void thatGetRandomQuestionWorks() throws Exception {
        String jsonResponseString = getFileAsString("json/QuizApiClientTest_thatGetRandomQuestionWorks.json");
        
        assertNotNull(jsonResponseString);
        
        Mockito.when(httpClient.send(request, HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        Mockito.when(response.statusCode()).thenReturn(200);
        Mockito.when(response.body()).thenReturn(jsonResponseString);
        
        List<QuestionResponseDto> result = quizApiClient.getRandomQuestion();
        
        assertNotNull(result);
        assertEquals(1, result.size());
        
        QuestionResponseDto question = result.get(0);
        assertEquals(1, question.getId());
        assertFalse(question.isMultiple_correct_answers());
        assertNull(question.getCorrect_answer());
        assertEquals(1, question.getTags().size());
        assertEquals("linux", question.getCategory());
        assertEquals("Easy", question.getDifficulty());
    }
    
    @Test
    public void thatGetRandomQuestionFailedWithWrongStatus() throws Exception{
        Mockito.when(httpClient.send(request, HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        Mockito.when(response.statusCode()).thenReturn(404);
        
        QuizApiRequestFailedException exception = assertThrows(QuizApiRequestFailedException.class, () -> 
                quizApiClient.getRandomQuestion()
        );
        
        String expectedExceptionMessage = String.format(GET_RANDOM_QUESTION_REQUEST_FAILED_WITH_STATUS_RESPONSE, NOT_FOUND.getStatusCode(), NOT_FOUND.getDescription());
        
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }
    
    @Test
    public void thatGetRandomQuestionGivesAnyResult() throws Exception {
        String jsonResponseString = "[]";
        
        Mockito.when(httpClient.send(request, HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        Mockito.when(response.statusCode()).thenReturn(200);
        Mockito.when(response.body()).thenReturn(jsonResponseString);
        
        QuizApiQuestionNotFoundExcption exception = assertThrows(QuizApiQuestionNotFoundExcption.class, () -> 
                quizApiClient.getRandomQuestion()
        );
        
        assertEquals(QUIZ_API_QUESTION_NOT_FOUND, exception.getMessage());
    }
    
    @Test
    public void thatGetRandomQuestionRequestThrowsIOException() throws Exception {
        Mockito.when(httpClient.send(request, HttpResponse.BodyHandlers.ofString())).thenThrow(IOException.class);
        
        QuizApiRequestFailedException exception = assertThrows(QuizApiRequestFailedException.class, () -> 
                quizApiClient.getRandomQuestion()
        );
        
        assertEquals(GET_RANDOM_QUESTION_REQUEST_FAILED, exception.getMessage());
    }
    
    @Test
    public void thatGetRandomQuestionRequestThrowsInterruptedException() throws Exception {
        Mockito.when(httpClient.send(request, HttpResponse.BodyHandlers.ofString())).thenThrow(InterruptedException.class);
        
        QuizApiRequestFailedException exception = assertThrows(QuizApiRequestFailedException.class, () -> 
                quizApiClient.getRandomQuestion()
        );
        
        assertEquals(GET_RANDOM_QUESTION_REQUEST_FAILED, exception.getMessage());
    }
}
