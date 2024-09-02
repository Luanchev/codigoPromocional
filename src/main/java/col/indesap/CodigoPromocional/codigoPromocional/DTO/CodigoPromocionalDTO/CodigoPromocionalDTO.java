package col.indesap.CodigoPromocional.codigoPromocional.DTO.CodigoPromocionalDTO;

import col.indesap.CodigoPromocional.codigoPromocional.DTO.UsuarioDTO.UsuarioDTO;
import jakarta.persistence.*;

@Entity
@Table(name= "codigos_promocionales")
public class CodigoPromocionalDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id_codigo")
    private Integer id_codigo;

    @Column(name = "codigo", unique = true)
    private String codigo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "descuento")
    private Double descuento;

    @ManyToOne
    @JoinColumn(name="id_usuario_fk")
    private UsuarioDTO usuario;

    @Column(name = "num_consultas")
    private Integer num_consultas = 0;

    public CodigoPromocionalDTO() {
    }

    public CodigoPromocionalDTO(Integer id_codigo, String codigo, String descripcion, Double descuento, UsuarioDTO usuario, Integer num_consultas) {
        this.id_codigo = id_codigo;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.usuario = usuario;
        this.num_consultas = num_consultas;
    }

    public Integer getId_codigo() {
        return id_codigo;
    }

    public void setId_codigo(Integer id_codigo) {
        this.id_codigo = id_codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public Integer getNum_consultas() {
        return num_consultas;
    }

    public void setNum_consultas(Integer num_consultas) {
        this.num_consultas = num_consultas;
    }
}
