package pl.agnieszkachalat.quizapp.client;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuizApiClient {
    
    @Autowired private HttpClient httpClient;
    @Autowired private HttpRequest randomQuestionRequest;
    
    public String getRandomQuestion() {
        
        try {
            HttpResponse<String> response = httpClient.send(randomQuestionRequest, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(QuizApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
