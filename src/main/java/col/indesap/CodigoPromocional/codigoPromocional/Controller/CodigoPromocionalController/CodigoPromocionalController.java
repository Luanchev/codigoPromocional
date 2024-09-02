package col.indesap.CodigoPromocional.codigoPromocional.Controller.CodigoPromocionalController;

import col.indesap.CodigoPromocional.codigoPromocional.DTO.CodigoPromocionalDTO.CodigoPromocionalDTO;
import col.indesap.CodigoPromocional.codigoPromocional.Service.CodigoPromocionalService.CodigoPromocionalService;
import col.indesap.CodigoPromocional.codigoPromocional.Service.UsuarioService.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("api/v1/codigopromocional")
@CrossOrigin(origins = "http://localhost:4200")
public class CodigoPromocionalController {
    @Autowired
    private CodigoPromocionalService codigoPromocionalService;
    private UsuarioService usuarioService;

    //exponemos todos los codigos con el metodo GET
    @GetMapping
    public ResponseEntity<List<CodigoPromocionalDTO>> getCodigoPromocional(){
        return ResponseEntity.ok(codigoPromocionalService.getCodigoPromocional());
    }

    @GetMapping("/getbyid/{codigoPromocional_id}")
    public ResponseEntity<Object>encontrarPorId(@PathVariable int codigoPromocional_id){
        return this.codigoPromocionalService.getCodigoById(codigoPromocional_id);
    }

    @GetMapping("/getbyemail/{email}")
    public ResponseEntity<List<CodigoPromocionalDTO>> encontrarPorEmail(@PathVariable  String email){
        List<CodigoPromocionalDTO>codigo = codigoPromocionalService.obtenerCodigoByEmail(email);
        if (codigo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(codigo);
        }
    }
    @GetMapping("/getbycodigo/{codigo}")
    public ResponseEntity<Optional<CodigoPromocionalDTO>> encontrarPorCodigo(@PathVariable String codigo){
        Optional<CodigoPromocionalDTO> codigoPromocional = codigoPromocionalService.obtenerByCodigo(codigo);
        if (codigoPromocional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(codigoPromocional);
        }

    }

    @GetMapping("/descripcionCodigo")
    public ResponseEntity<String> obtenerDescripcion(@RequestBody String codigo) {
        // Llama al servicio para obtener la descripción
        Optional<String> descripcion = codigoPromocionalService.obtenerDescripcion(codigo);
        return descripcion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Código promocional no encontrado"));
    }

    @PostMapping("/validarcodigo")
    public ResponseEntity<String> validarCodigo(@RequestBody String codigo) {

        try {
            String descripcion = codigoPromocionalService.validarCodigo(codigo);
            return ResponseEntity.ok(descripcion);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado");
        }
    }




    @PostMapping("/asignarCodigo")
    public ResponseEntity<Void> asignarCodigo(@RequestBody Map<String, String> request){
        String email = request.get("email");
        String codigo = request.get("codigo");
        try{
            codigoPromocionalService.asignarCodigoPorEmail(email, codigo);
            return ResponseEntity.ok().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
