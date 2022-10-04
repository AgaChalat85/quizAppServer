package pl.agnieszkachalat.quizapp.webservice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.net.http.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.agnieszkachalat.quizapp.BaseTest;

@AutoConfigureMockMvc
public class QuizApiWebserviceTest extends BaseTest {
    
    private static final String GET_RANDOM_QUESTION_URI = "/v1/quizApi/randomQuestion";
    
    @MockBean private HttpClient httpClient;
    @MockBean private HttpRequest randomQuestionRequest;
    @Mock private HttpResponse response;
    
    @Autowired private MockMvc mockMvc;
    
    @Test
    public void thatGetRandomQuestionEndpointWorks() throws Exception {
        String jsonResponseString = getFileAsString("json/QuizApiClientTest_thatGetRandomQuestionWorks.json");
        
        Mockito.when(httpClient.send(randomQuestionRequest, HttpResponse.BodyHandlers.ofString())).thenReturn(response);
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
        Mockito.when(httpClient.send(randomQuestionRequest, HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        Mockito.when(response.statusCode()).thenReturn(200);
        Mockito.when(response.body()).thenReturn("[]");
        
        mockMvc.perform(MockMvcRequestBuilders.get(GET_RANDOM_QUESTION_URI)
                                              .accept(MediaType.APPLICATION_JSON))
                                              .andExpect(status().isNotFound());
    }
    
    @Test
    public void thatGetRandomQuestionEndpointReturnsBadRequest() throws Exception {
        Mockito.when(httpClient.send(randomQuestionRequest, HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        Mockito.when(response.statusCode()).thenReturn(400);
        
        mockMvc.perform(MockMvcRequestBuilders.get(GET_RANDOM_QUESTION_URI)
                                              .accept(MediaType.APPLICATION_JSON))
                                              .andExpect(status().isBadRequest());
    }
}
