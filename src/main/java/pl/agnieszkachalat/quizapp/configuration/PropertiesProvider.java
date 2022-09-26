package pl.agnieszkachalat.quizapp.configuration;

import org.springframework.beans.factory.annotation.Value;

public class PropertiesProvider {
    
    @Value("${quiz.api.key}")
    private String quizApiKey;
    
    @Value("${quiz.api.key.header.name}")
    private String quizApiKeyHeaderName;
    
    private PropertiesProvider() {
        // DEFAULT CONSTRUCTOR
    }

    public String getQuizApiKey() {
        return quizApiKey;
    }

    public String getQuizApiKeyHeaderName() {
        return quizApiKeyHeaderName;
    }
}
