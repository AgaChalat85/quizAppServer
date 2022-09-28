package pl.agnieszkachalat.quizapp.configuration;

import static pl.agnieszkachalat.quizapp.enums.QuizApiParameterEnum.LIMIT;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class AppConfig {
    
    @Autowired private PropertiesProvider propertiesProvider;
    
    @Bean
    public HttpClient httpClient() {
        return HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).connectTimeout(Duration.ofSeconds(5)).build();
    }
    
    @Bean
    public HttpRequest randomQuestionRequest() throws URISyntaxException {
        URI requestUri = UriComponentsBuilder.fromHttpUrl(propertiesProvider.getQuestionsEndpointUri())
                                             .queryParam(LIMIT.getName(), 1)
                                             .build()
                                             .toUri();
        
        return HttpRequest.newBuilder()
                          .uri(requestUri)
                          .header(propertiesProvider.getQuizApiKeyHeaderName(), propertiesProvider.getQuizApiKey())
                          .header("Accept", "application/json")
                          .header("Content-type", "application/json")
                          .GET()
                          .build();
    }
    
}
