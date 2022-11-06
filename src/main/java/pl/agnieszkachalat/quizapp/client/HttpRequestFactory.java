package pl.agnieszkachalat.quizapp.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import pl.agnieszkachalat.quizapp.configuration.PropertiesProvider;
import pl.agnieszkachalat.quizapp.dto.CriteriaDto;

import java.net.URI;
import java.net.http.HttpRequest;

import static pl.agnieszkachalat.quizapp.enums.QuizApiParameterEnum.*;

@Component
public class HttpRequestFactory {

    @Autowired
    private PropertiesProvider propertiesProvider;

    public HttpRequest createHttpRequest(CriteriaDto parameters){
        URI uriWithParameters = createUriWithParameters(parameters);

        return HttpRequest.newBuilder()
                .uri(uriWithParameters)
                .header(propertiesProvider.getQuizApiKeyHeaderName(), propertiesProvider.getQuizApiKey())
                .header("Accept", "application/json")
                .header("Content-type", "application/json")
                .GET()
                .build();
    }


    private URI createUriWithParameters(CriteriaDto parameters) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(propertiesProvider.getQuestionsEndpointUri());
        if (parameters.getCategory() != null) {
            uriBuilder.queryParam(CATEGORY.getName(), parameters.getCategory());
        }
        if (parameters.getDifficulty() != null) {
            uriBuilder.queryParam(DIFFICULTY.getName(), parameters.getDifficulty());
        }
        if (parameters.getLimit()!= null) {
            uriBuilder.queryParam(LIMIT.getName(), parameters.getLimit());
        }

        return uriBuilder.build()
                .toUri();
    }
}
