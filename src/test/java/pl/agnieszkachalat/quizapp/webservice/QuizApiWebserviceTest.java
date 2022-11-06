package pl.agnieszkachalat.quizapp.webservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.net.http.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.agnieszkachalat.quizapp.BaseTest;
import pl.agnieszkachalat.quizapp.client.HttpRequestFactory;
import pl.agnieszkachalat.quizapp.dto.CriteriaDto;

@AutoConfigureMockMvc
public class QuizApiWebserviceTest extends BaseTest {
    
    private static final String GET_RANDOM_QUESTION_URI = "/v1/quizApi/randomQuestion";
    private static final String GET_QUESTIONS_BY_CRITERIA = "/v1/quizApi/questionsByCriteria";
    
    @MockBean private HttpClient httpClient;
    @MockBean private HttpRequest request;
    @MockBean private HttpRequestFactory httpRequestFactory;
    @Mock private HttpResponse response;
    
    @Autowired private MockMvc mockMvc;
    
    @BeforeEach
    public void init() {
         Mockito.when(httpRequestFactory.createHttpRequest(ArgumentMatchers.any(CriteriaDto.class))).thenReturn(request);
    }
    
    @Test
    public void thatGetRandomQuestionEndpointWorks() throws Exception {
        String jsonResponseString = getFileAsString("json/QuizApiClientTest_thatGetRandomQuestionWorks.json");
        
        Mockito.when(httpClient.send(request, HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        Mockito.when(response.statusCode()).thenReturn(200);
        Mockito.when(response.body()).thenReturn(jsonResponseString);
        
        mockMvc.perform(MockMvcRequestBuilders.get(GET_RANDOM_QUESTION_URI)
                                              .accept(MediaType.APPLICATION_JSON))
                                              .andDo(print())
                                              .andExpect(status().isOk())
                                              .andExpect(jsonPath("$.qstId").value(1L))
                                              .andExpect(jsonPath("$.question").value("How to delete a directory in Linux?"));
    }
    
    @Test
    public void thatGetRandomQuestionEndpointReturnsNotFound() throws Exception {
        Mockito.when(httpClient.send(request, HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        Mockito.when(response.statusCode()).thenReturn(200);
        Mockito.when(response.body()).thenReturn("[]");
        
        mockMvc.perform(MockMvcRequestBuilders.get(GET_RANDOM_QUESTION_URI)
                                              .accept(MediaType.APPLICATION_JSON))
                                              .andExpect(status().isNotFound());
    }
    
    @Test
    public void thatGetRandomQuestionEndpointReturnsBadRequest() throws Exception {
        Mockito.when(httpClient.send(request, HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        Mockito.when(response.statusCode()).thenReturn(400);
        
        mockMvc.perform(MockMvcRequestBuilders.get(GET_RANDOM_QUESTION_URI)
                                              .accept(MediaType.APPLICATION_JSON))
                                              .andExpect(status().isBadRequest());
    }
    
    @Test
    public void thatGetQuestionsByCriteriaEndpointWorks() throws Exception {
        String jsonResponseString = getFileAsString("json/QuizApiClientTest_thatGetQuestionsByCriteriaWorks.json");
        
        Mockito.when(httpClient.send(request, HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        Mockito.when(response.statusCode()).thenReturn(200);
        Mockito.when(response.body()).thenReturn(jsonResponseString);
        
        CriteriaDto criteria = new CriteriaDto("Linux", "Easy", 5, null);
        String contentString = new ObjectMapper().writeValueAsString(criteria);
        
        mockMvc.perform(MockMvcRequestBuilders.post(GET_QUESTIONS_BY_CRITERIA)
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(contentString) 
                                              .accept(MediaType.APPLICATION_JSON))
                                              .andExpect(status().isOk())
                                              .andExpect(jsonPath("$", Matchers.hasSize(5)))
                                              .andExpect(jsonPath("$[0].qstId").value(1071L))
                                              .andExpect(jsonPath("$[1].qstId").value(682L))
                                              .andExpect(jsonPath("$[2].qstId").value(707L))
                                              .andExpect(jsonPath("$[3].qstId").value(719L))
                                              .andExpect(jsonPath("$[4].qstId").value(650L)); 
    }
}
