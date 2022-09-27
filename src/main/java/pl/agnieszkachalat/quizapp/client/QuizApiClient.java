package pl.agnieszkachalat.quizapp.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.agnieszkachalat.quizapp.client.dto.QuestionResponseDto;

@Component
public class QuizApiClient {
    
    @Autowired private HttpClient httpClient;
    @Autowired private HttpRequest randomQuestionRequest;
    
    public List<QuestionResponseDto> getRandomQuestion() {
        
        try {
            HttpResponse<String> response = httpClient.send(randomQuestionRequest, HttpResponse.BodyHandlers.ofString());
            String jsonBody = response.body();
            return new ObjectMapper().readValue(jsonBody, new TypeReference<List<QuestionResponseDto>>() {});
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(QuizApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
