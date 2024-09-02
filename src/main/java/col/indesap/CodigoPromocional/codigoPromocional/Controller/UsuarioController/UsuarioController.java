package col.indesap.CodigoPromocional.codigoPromocional.Controller.UsuarioController;

import col.indesap.CodigoPromocional.codigoPromocional.DTO.UsuarioDTO.UsuarioDTO;
import col.indesap.CodigoPromocional.codigoPromocional.Repository.UsuarioRepository.UsuarioRepository;
import col.indesap.CodigoPromocional.codigoPromocional.Service.UsuarioService.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios(){
        return ResponseEntity.ok(usuarioService.getAllUsuarios());

    }

    @GetMapping("/getbyid/{id_usuario}")
    public ResponseEntity<Object>EncontrarPorId(@PathVariable Integer id_usuario){
        return this.usuarioService.getUsuarioById(id_usuario);
    }
    @PostMapping("/guardar/")
    public ResponseEntity<Object>createUsuario(@RequestBody UsuarioDTO usuario){
        try {
            UsuarioDTO usuarioGuardado = usuarioService.guardarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);
        }catch (ResponseStatusException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }



}
