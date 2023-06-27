package conadal;

import conadal.dto.ConadDTO;
import conadal.mapper.ConadMapper;
import conadal.model.Conad;
import conadal.repository.ConadRepository;
import conadal.service.ConadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ConadServiceTest {

    @InjectMocks
    private ConadService conadService;

    @Mock
    private ConadRepository conadRepository;

    @Mock
    private ConadMapper conadMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllSuperMarkets(){

        Conad conad1 = new Conad(1L,"name1","location1");
        Conad conad2 = new Conad(1L,"name2","location2");
        List<Conad> conadList = Arrays.asList(conad1,conad2);

        when(conadRepository.findAll()).thenReturn(conadList);
        when(conadMapper.toDto(conad1)).thenReturn(new ConadDTO(1L,"name1","location1"));
        when(conadMapper.toDto(conad2)).thenReturn(new ConadDTO(2L,"name2","location2"));

        List<ConadDTO> result = conadService.getAllSuperMarkets();

        assertEquals(conadList.size(),result.size());
    }

    @Test
    public void testAddSuperMarket() {

        ConadDTO conadDTO = new ConadDTO(1L,"name1","location1");
        Conad conad = new Conad(1L,"name1","location1");

        when(conadMapper.toEntity(conadDTO)).thenReturn(conad);
        when(conadRepository.save(conad)).thenReturn(conad);
        when(conadMapper.toDto(conad)).thenReturn(conadDTO);

        ConadDTO result = conadService.addSuperMarket(conadDTO);

        assertEquals(conadDTO, result);
    }

    @Test
    public void testGetById() {

        Long id = 1L;
        Conad conad = new Conad(id,"name1","location1");
        ConadDTO conadDTO = new ConadDTO(id,"name1","location1");

        when(conadRepository.findById(id)).thenReturn(Optional.of(conad));
        when(conadMapper.toDto(conad)).thenReturn(conadDTO);

        ConadDTO result = conadService.getSuperMarketById(id);

        assertEquals(conadDTO, result);

    }

    @Test
    public void testUpdateSuperMarket() {
        Long id = 1L;
        ConadDTO updatedConadDTO = new ConadDTO(id,"name1","location1");
        Conad updatedConad = new Conad(id,"name1","location1");

        when(conadRepository.findById(id)).thenReturn(Optional.of(new Conad()));
        when(conadRepository.save(any(Conad.class))).thenReturn(updatedConad);
        when(conadMapper.toDto(updatedConad)).thenReturn(updatedConadDTO);

        ConadDTO result = conadService.updateSuperMarket(id, updatedConadDTO);

        assertEquals(updatedConadDTO, result);

    }

    @Test
    public void testDeleteSuperMarket(){
        Long id = 1L;

        conadService.deleteSuperMarket(id);

        verify(conadRepository, times(1)).deleteById(id);
    }
}
