package conadal.service;

import conadal.dto.ConadDTO;
import conadal.mapper.ConadMapper;
import conadal.model.Conad;
import conadal.repository.ConadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConadService {

    private final ConadRepository conadRepository;

    private final ConadMapper conadMapper;

    @Autowired
    public ConadService(ConadRepository conadRepository, ConadMapper conadMapper) {
        this.conadRepository = conadRepository;
        this.conadMapper = conadMapper;
    }

    public List<ConadDTO> getAllSuperMarkets() {
        List<Conad> conads = conadRepository.findAll();
        List<ConadDTO> conadDTOs = new ArrayList<>();

        for(Conad conad : conads){
            ConadDTO conadDTO = conadMapper.toDto(conad);
            conadDTOs.add(conadDTO);
        }
        return conadDTOs;
    }

    public ConadDTO addSuperMarket(ConadDTO conadDTO) {
        Conad conad = conadMapper.toEntity(conadDTO);
        Conad savedConad = conadRepository.save(conad);

        return conadMapper.toDto(savedConad);
    }

    public ConadDTO getSuperMarketById(Long id) throws IllegalArgumentException {
        Conad conad = conadRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Conad not found"));

        return conadMapper.toDto(conad);
    }

    public ConadDTO updateSuperMarket(Long id, ConadDTO updatedSuperMarket) {
        Conad existingConad = conadRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Conad not found with id: " + id));

        existingConad.setName(updatedSuperMarket.getName());
        existingConad.setLocation(updatedSuperMarket.getLocation());

        Conad savedConad = conadRepository.save(existingConad);

        return conadMapper.toDto(savedConad);
    }

    public void deleteSuperMarket(Long id) { conadRepository.deleteById(id); }
}
