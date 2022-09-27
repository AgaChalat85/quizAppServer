package pl.agnieszkachalat.quizapp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesProvider {
    
    @Value("${quiz.api.key}")
    private String quizApiKey;
    
    @Value("${quiz.api.key.header.name}")
    private String quizApiKeyHeaderName;
    
    @Value("${questions.endpoint.uri}")
    private String questionsEndpointUri;
    
    private PropertiesProvider() {
        // DEFAULT CONSTRUCTOR
    }

    public String getQuizApiKey() {
        return quizApiKey;
    }

    public String getQuizApiKeyHeaderName() {
        return quizApiKeyHeaderName;
    }

    public String getQuestionsEndpointUri() {
        return questionsEndpointUri;
    }
}
