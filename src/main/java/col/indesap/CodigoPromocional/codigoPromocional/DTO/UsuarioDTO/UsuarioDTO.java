package col.indesap.CodigoPromocional.codigoPromocional.DTO.UsuarioDTO;

import jakarta.persistence.*;

@Entity
@Table(name= "usuarios")

public class UsuarioDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id_usuario")
    private Integer id_usuario;

    @Column(name= "correo_electronico")
    private String correo_electronico;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Integer id_usuario, String correo_electronico) {
        this.id_usuario = id_usuario;
        this.correo_electronico = correo_electronico;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }
}
