package br.com.pucminas.apiproducer.repositories;

import br.com.pucminas.apiproducer.dtos.CountDataRequestDto;
import br.com.pucminas.apiproducer.entities.Projeto;
import br.com.pucminas.apiproducer.enums.AreasEnum;
import br.com.pucminas.apiproducer.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto,Long> {

    Optional<Projeto> findById(Long id);

    List<Projeto> findByUserId(Long userId);

    List<Projeto> findByUserIdAndStatusIn(Long userId, List<StatusEnum> status);

    List<Projeto> findByArea(AreasEnum areas);
    List<Projeto> findByAreaAndStatusIn(AreasEnum areas, List<StatusEnum> status);

    @Query(" select DISTINCT new br.com.pucminas.apiproducer.dtos.CountDataRequestDto( " +
            " count(P), " +
            " P.area " +
            " ) from Projeto P where area in( :areas ) GROUP BY P.area")
    List<CountDataRequestDto> findByCount(List<AreasEnum> areas);

}
