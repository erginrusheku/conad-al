package conadal.controller;

import conadal.dto.ConadDTO;
import conadal.service.ConadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supermarkets")
public class ConadController {

    private final ConadService conadService;

    @Autowired
    public ConadController(ConadService conadService) {
        this.conadService = conadService;
    }

    @GetMapping
    public List<ConadDTO> getAllSuperMarkets(){ return conadService.getAllSuperMarkets(); }

    @PostMapping
    public ConadDTO addSuperMarket(@RequestBody ConadDTO conadDTO){ return  conadService.addSuperMarket(conadDTO); }


    @GetMapping("/{id}")
    public ConadDTO getSuperMarketById(@PathVariable Long id) throws IllegalArgumentException { return conadService.getSuperMarketById(id); }

    @PutMapping("/{id}")
    public ConadDTO updateSuperMarket(@PathVariable Long id , @RequestBody ConadDTO updatedSuperMarket) throws IllegalArgumentException { return conadService.updateSuperMarket(id, updatedSuperMarket); }

    @DeleteMapping("/{id}")
    public void deleteSuperMarket(@PathVariable Long id){ conadService.deleteSuperMarket(id); }
}
