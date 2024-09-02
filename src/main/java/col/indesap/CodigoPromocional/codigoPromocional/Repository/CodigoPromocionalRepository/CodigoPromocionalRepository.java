package col.indesap.CodigoPromocional.codigoPromocional.Repository.CodigoPromocionalRepository;

import col.indesap.CodigoPromocional.codigoPromocional.DTO.CodigoPromocionalDTO.CodigoPromocionalDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CodigoPromocionalRepository  extends JpaRepository<CodigoPromocionalDTO, Integer> {


    List<CodigoPromocionalDTO> findByUsuario_Email(String email);

    Optional<CodigoPromocionalDTO> findByCodigo(String codigo);

    @Query(value = "SELECT c.descripcion FROM codigos_promocionales c WHERE upper(c.codigo) = :codigo", nativeQuery = true)
    Optional<String> findDescripcionByCodigo(@Param("codigo") String codigo);


}
