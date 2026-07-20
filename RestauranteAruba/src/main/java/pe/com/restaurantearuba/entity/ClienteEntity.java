package pe.com.restaurantearuba.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "ClienteEntity")
@Table(name = "cliente")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codcli")
    private Integer id;

    @NotBlank(message = "El numero de documento es obligatorio")
    @Size(max = 15)
    @Column(name = "numdoc", nullable = false, length = 15)
    private String numeroDocumento;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 150)
    @Column(name = "nomcli", nullable = false, length = 150)
    private String nombres;

    @NotBlank(message = "El apellido paterno es obligatorio")
    @Size(max = 100)
    @Column(name = "apepcli", nullable = false, length = 100)
    private String apellidoPaterno;

    @NotBlank(message = "El apellido materno es obligatorio")
    @Size(max = 100)
    @Column(name = "apemcli", nullable = false, length = 100)
    private String apellidoMaterno;

    @NotBlank(message = "El telefono es obligatorio")
    @Size(max = 15)
    @Column(name = "telcli", nullable = false, length = 15)
    private String telefono;

    @NotBlank(message = "La direccion es obligatoria")
    @Size(max = 100)
    @Column(name = "dirclir", nullable = false, length = 100)
    private String direccion;

    @Column(name = "estcli", nullable = false)
    private Boolean estado;

    @NotNull(message = "El distrito es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coddis", nullable = false)
    private DistritoEntity distrito;

    @NotNull(message = "El tipo de documento es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codtipdoc", nullable = false)
    private TipoDocumentoEntity tipoDocumento;
}
