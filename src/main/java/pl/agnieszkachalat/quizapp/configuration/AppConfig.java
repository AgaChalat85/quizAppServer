package pl.agnieszkachalat.quizapp.configuration;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Autowired private PropertiesProvider propertiesProvider;
    
    @Bean
    public HttpClient httpClient() {
        return HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).connectTimeout(Duration.ofSeconds(5)).build();
    }
    
    @Bean
    public HttpRequest randomQuestionRequest() throws URISyntaxException {
        return HttpRequest.newBuilder()
                          .uri(new URI(propertiesProvider.getQuestionsEndpointUri()))
                          .header(propertiesProvider.getQuizApiKeyHeaderName(), propertiesProvider.getQuizApiKey())
                          .header("Accept", "application/json")
                          .header("Content-type", "application/json")
                          .GET()
                          .build();
        // TODO -> extend url with limit parameter and value 1
    }
    
}
