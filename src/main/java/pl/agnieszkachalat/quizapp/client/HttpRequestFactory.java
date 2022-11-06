package pl.agnieszkachalat.quizapp.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import pl.agnieszkachalat.quizapp.configuration.PropertiesProvider;
import pl.agnieszkachalat.quizapp.dto.CriteriaDto;

import java.net.URI;
import java.net.http.HttpRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import static pl.agnieszkachalat.quizapp.enums.QuizApiParameterEnum.*;

@Component
public class HttpRequestFactory {

    @Autowired
    private PropertiesProvider propertiesProvider;

    public HttpRequest createHttpRequest(CriteriaDto criteria){
        URI uriWithParameters = createUriWithParameters(criteria);

        return HttpRequest.newBuilder()
                .uri(uriWithParameters)
                .header(propertiesProvider.getQuizApiKeyHeaderName(), propertiesProvider.getQuizApiKey())
                .header("Accept", "application/json")
                .header("Content-type", "application/json")
                .GET()
                .build();
    }


    private URI createUriWithParameters(CriteriaDto criteria) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(propertiesProvider.getQuestionsEndpointUri());
        if (!StringUtils.isEmpty(criteria.getCategory())) {
            uriBuilder.queryParam(CATEGORY.getName(), criteria.getCategory());
        }
        if (!StringUtils.isEmpty(criteria.getDifficulty())) {
            uriBuilder.queryParam(DIFFICULTY.getName(), criteria.getDifficulty());
        }
        if (criteria.getLimit()!= null) {
            uriBuilder.queryParam(LIMIT.getName(), criteria.getLimit());
        }
        if(!CollectionUtils.isEmpty(criteria.getTags())) {
            uriBuilder.queryParam(TAGS.getName(), criteria.getTags());
        }

        return uriBuilder.build()
                .toUri();
    }
}
