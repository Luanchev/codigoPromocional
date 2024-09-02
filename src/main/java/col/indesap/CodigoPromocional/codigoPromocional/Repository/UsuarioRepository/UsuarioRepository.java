package col.indesap.CodigoPromocional.codigoPromocional.Repository.UsuarioRepository;

import col.indesap.CodigoPromocional.codigoPromocional.DTO.CodigoPromocionalDTO.CodigoPromocionalDTO;
import col.indesap.CodigoPromocional.codigoPromocional.DTO.UsuarioDTO.UsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository <UsuarioDTO, Integer> {
    Optional<UsuarioDTO> findByEmail(String email);
    boolean existsByEmail(String email);



}
