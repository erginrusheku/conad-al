package conadal.mapper;

import conadal.dto.ConadDTO;
import conadal.model.Conad;
import org.springframework.stereotype.Component;

@Component
public class ConadMapper {
    public ConadDTO toDto(Conad conad) {

        ConadDTO conadDto = new ConadDTO();
        conadDto.setId(conad.getId());
        conadDto.setName(conad.getName());
        conadDto.setLocation(conad.getLocation());

        return conadDto;
    }

    public Conad toEntity(ConadDTO conadDto) {

        Conad conad = new Conad();
        conad.setId(conadDto.getId());
        conad.setName(conadDto.getName());
        conad.setLocation(conadDto.getLocation());


        return conad;
    }
}
