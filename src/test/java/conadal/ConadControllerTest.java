package conadal;

import conadal.controller.ConadController;
import conadal.dto.ConadDTO;
import conadal.mapper.ConadMapper;
import conadal.service.ConadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(ConadController.class)
public class ConadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConadService conadService;

    @Mock
    private ConadMapper conadMapper;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAllSuperMarkets() throws Exception {
        ConadDTO conadDTO1 = new ConadDTO(1L,"name1","location1");
        ConadDTO conadDTO2 = new ConadDTO(2L,"name2","location2");
        List<ConadDTO> conadDTOS = Arrays.asList(conadDTO1,conadDTO2);

        when(conadService.getAllSuperMarkets()).thenReturn(conadDTOS);

        mockMvc.perform(MockMvcRequestBuilders.get("/supermarkets")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("name1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].location").value("location1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("name2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].location").value("location2"));

    }

    @Test
    public void testAddSuperMarkets() throws Exception {
        ConadDTO conadDTO = new ConadDTO(1L,"name1","location1");

        when(conadService.addSuperMarket(conadDTO)).thenReturn(conadDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/supermarkets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"name\": \"name1\", \"location\": \"location1\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("name1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location").value("location1"));
    }

    @Test
    public void testGetById() throws Exception {
        Long id = 1L;
        ConadDTO conadDTO = new ConadDTO(id,"name1","location1");

        when(conadService.getSuperMarketById(id)).thenReturn(conadDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/supermarkets/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("name1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location").value("location1"));
    }

    @Test
    public void testUpdateSuperMarket() throws Exception {
        Long id = 1L;
        ConadDTO updatedConadDTO = new ConadDTO(id,"name2","location2");

        when(conadService.updateSuperMarket(id,updatedConadDTO)).thenReturn(updatedConadDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/supermarkets/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"name\": \"name2\", \"location\": \"location2\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("name2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location").value("location2"));
    }

    @Test
    public void testDeleteSuperMarket() throws Exception{

        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/supermarkets/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
