package pl.agnieszkachalat.quizapp.client;

import static org.junit.jupiter.api.Assertions.*;
import static pl.agnieszkachalat.quizapp.enums.HttpStatusEnum.*;
import static pl.agnieszkachalat.quizapp.client.exception.ExceptionMessages.*;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.agnieszkachalat.quizapp.BaseTest;
import pl.agnieszkachalat.quizapp.client.dto.QuestionResponseDto;
import pl.agnieszkachalat.quizapp.client.exception.QuizApiQuestionNotFoundExcption;
import pl.agnieszkachalat.quizapp.client.exception.QuizApiRequestFailedException;

public class QuizApiClientTest extends BaseTest {
    
    @InjectMocks private QuizApiClient quizApiClient;
    @Mock private HttpClient httpClient;
    @Mock private HttpRequest randomQuestionRequest;
    @Mock private HttpResponse response;
    
    
    @Test
    public void thatGetRandomQuestionWorks() throws Exception {
        String jsonResponseString = getFileAsString("json/singleQuestionResponse.json");
        
        assertNotNull(jsonResponseString);
        
        Mockito.when(httpClient.send(randomQuestionRequest, HttpResponse.BodyHandlers.ofString())).thenReturn(response);
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
        Mockito.when(httpClient.send(randomQuestionRequest, HttpResponse.BodyHandlers.ofString())).thenReturn(response);
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
        
        Mockito.when(httpClient.send(randomQuestionRequest, HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        Mockito.when(response.statusCode()).thenReturn(200);
        Mockito.when(response.body()).thenReturn(jsonResponseString);
        
        QuizApiQuestionNotFoundExcption exception = assertThrows(QuizApiQuestionNotFoundExcption.class, () -> 
                quizApiClient.getRandomQuestion()
        );
        
        assertEquals(QUIZ_API_QUESTION_NOT_FOUND, exception.getMessage());
    }
}
