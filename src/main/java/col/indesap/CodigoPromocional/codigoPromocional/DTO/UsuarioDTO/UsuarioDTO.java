package col.indesap.CodigoPromocional.codigoPromocional.DTO.UsuarioDTO;

import col.indesap.CodigoPromocional.codigoPromocional.DTO.CodigoPromocionalDTO.CodigoPromocionalDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "usuarios")

public class UsuarioDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id_usuario")
    private Integer id_usuario;

    @Column(name= "email",unique = true)
    private String email;

    @OneToMany(mappedBy = "usuario")
    private List<CodigoPromocionalDTO> codigosPromocionales;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Integer id_usuario, String email, List<CodigoPromocionalDTO> codigosPromocionales) {
        this.id_usuario = id_usuario;
        this.email = email;
        this.codigosPromocionales = codigosPromocionales;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CodigoPromocionalDTO> getCodigosPromocionales() {
        return codigosPromocionales;
    }

    public void setCodigosPromocionales(List<CodigoPromocionalDTO> codigosPromocionales) {
        this.codigosPromocionales = codigosPromocionales;
    }
}
