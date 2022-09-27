package pl.agnieszkachalat.quizapp.client;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.agnieszkachalat.quizapp.BaseTest;
import pl.agnieszkachalat.quizapp.client.dto.QuestionResponseDto;

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
        Mockito.when(response.body()).thenReturn(jsonResponseString);
        
        List<QuestionResponseDto> result = quizApiClient.getRandomQuestion();
        
        assertNotNull(result);
        assertEquals(1, result.size());
    }
}
