package col.indesap.CodigoPromocional.codigoPromocional.Service.CodigoPromocionalService;

import col.indesap.CodigoPromocional.codigoPromocional.DTO.CodigoPromocionalDTO.CodigoPromocionalDTO;
import col.indesap.CodigoPromocional.codigoPromocional.DTO.UsuarioDTO.UsuarioDTO;
import col.indesap.CodigoPromocional.codigoPromocional.Repository.CodigoPromocionalRepository.CodigoPromocionalRepository;
import col.indesap.CodigoPromocional.codigoPromocional.Repository.UsuarioRepository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CodigoPromocionalService {

    HashMap<String, Object> datos;
    @Autowired
    private final CodigoPromocionalRepository codigoPromocionalRepository;
    @Autowired
    private final UsuarioRepository usuarioRepository;

    public CodigoPromocionalService(CodigoPromocionalRepository codigoPromocionalRepository, UsuarioRepository usuarioRepository) {
        this.codigoPromocionalRepository = codigoPromocionalRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // <editor-fold desc="Servicio GET">
    // traemos todos los codigos
    public List<CodigoPromocionalDTO> getCodigoPromocional(){
        return codigoPromocionalRepository.findAll();
    }
    // </editor-fold>

    // <editor-fold desc="Servicio getById">
    //traemos el codigo por id
    public ResponseEntity<Object> getCodigoById(Integer codigoPromocional_id){
        Optional<CodigoPromocionalDTO> codigoGuardado = codigoPromocionalRepository.findById(codigoPromocional_id);
        datos = new HashMap<>();
        if (codigoGuardado.isPresent()){
            CodigoPromocionalDTO codigo = codigoGuardado.get();
            datos.put("data", codigo);
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.OK
            );
        }else {
            return new ResponseEntity<>("No se encuentra ninguna codigo por el id", HttpStatus.NOT_FOUND);
        }
    }
    // </editor-fold>

    // <editor-fold desc="Servicio getByEmail">
    //traemos los codigos por el email del usuario
    public List<CodigoPromocionalDTO> obtenerCodigoByEmail(String email) {
        return codigoPromocionalRepository.findByUsuario_Email(email);
    }
    // </editor-fold>

    // <editor-fold desc="Servicio getByCodigo">
    //traemos el numero del codigo
    public Optional<CodigoPromocionalDTO> obtenerByCodigo(String codigo) {
        return codigoPromocionalRepository.findByCodigo(codigo);
    }
    // </editor-fold>

    // <editor-fold desc="Servicio para traer la descripcion mediante el codigo">
    public Optional<String> obtenerDescripcion(String codigo) {
        // Llama al repositorio para obtener la descripción
        return codigoPromocionalRepository.findDescripcionByCodigo(codigo);
    }
    // </editor-fold>


    // <editor-fold desc="Servicio para validar codigo e incrementar numero de consultas">

    public String validarCodigo(String codigo){

        codigo = codigo.trim().replace("\"", "");  // Elimina espacios al inicio o final
        System.out.println("Código recibido: " + codigo);

        CodigoPromocionalDTO codigoPromocional = codigoPromocionalRepository.findByCodigo(codigo)
                .orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,"Codigo promocional no encontrado")
                );
        //incrementa
        codigoPromocional.setNum_consultas(codigoPromocional.getNum_consultas() + 1);

        codigoPromocionalRepository.save(codigoPromocional);

        String descripcion = codigoPromocional.getDescripcion();
        System.out.println("Descripción obtenida: " + descripcion);

        return codigoPromocional.getDescripcion();
    }
    // </editor-fold>

    // <editor-fold desc="Servicio para asignar codigo e incrementar numero de consultas">
    public void asignarCodigoPorEmail(String email, String codigo){



        //buscar el codigo promocional
        CodigoPromocionalDTO codigoPromocional = codigoPromocionalRepository.findByCodigo(codigo)
                .orElseThrow(()->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,"Codigo promocional no encontrado")
                );

        //buscar el usuario por email
        UsuarioDTO usuario = usuarioRepository.findByEmail(email)
                .orElseGet(()->{
                    UsuarioDTO nuevoUsuario = new UsuarioDTO();
                    nuevoUsuario.setEmail(email);
                    return usuarioRepository.save(nuevoUsuario);
                });

        // Verificar si el código ya está asignado a otro usuario
        if (codigoPromocional.getUsuario() != null) {
            throw new IllegalStateException("El código promocional ya está asignado a otro usuario");
        }
        //incrementa
        codigoPromocional.setNum_consultas(codigoPromocional.getNum_consultas() + 1);

        // Asignar el código al usuario
        codigoPromocional.setUsuario(usuario);

        // Guardar los cambios
        codigoPromocionalRepository.save(codigoPromocional);


    }
    // </editor-fold>

}
