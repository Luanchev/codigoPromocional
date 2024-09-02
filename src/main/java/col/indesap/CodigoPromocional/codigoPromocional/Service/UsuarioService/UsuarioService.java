package col.indesap.CodigoPromocional.codigoPromocional.Service.UsuarioService;

import col.indesap.CodigoPromocional.codigoPromocional.DTO.UsuarioDTO.UsuarioDTO;
import col.indesap.CodigoPromocional.codigoPromocional.Repository.UsuarioRepository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    HashMap<String, Object> datos;
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDTO> getAllUsuarios(){
        return usuarioRepository.findAll();
    }
    public ResponseEntity<Object> getUsuarioById(Integer id_usuario){
        Optional<UsuarioDTO> usuarioGuardado = usuarioRepository.findById(id_usuario);
        datos = new HashMap<>();
        if (usuarioGuardado.isPresent()){
            UsuarioDTO usuario = usuarioGuardado.get();
            datos.put("data", usuario);
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.OK
            );
        }else {
            return new ResponseEntity<>("No se encuentra ningun correo por el id", HttpStatus.NOT_FOUND);
        }
    }

    // <editor-fold desc="Servicio POST">
    public UsuarioDTO guardarUsuario(UsuarioDTO usuarioGuardado){
        boolean usuarioExistente = usuarioRepository.existsByEmail(
                usuarioGuardado.getEmail()
        );
        if (usuarioExistente) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya se encuentra registrado el correo");
        }else {
            return usuarioRepository.save(usuarioGuardado);
        }
    }
    // </editor-fold>

}
