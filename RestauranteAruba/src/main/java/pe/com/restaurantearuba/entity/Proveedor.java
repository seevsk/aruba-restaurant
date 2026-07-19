package pe.com.restaurantearuba.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "proveedor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codpro")
    private Integer id;

    @NotBlank(message = "El RUC es obligatorio")
    @Pattern(regexp = "\\d{11}", message = "El RUC debe tener 11 digitos")
    @Column(name = "rucpro", nullable = false, length = 11)
    private String ruc;

    @NotBlank(message = "La razon social es obligatoria")
    @Size(max = 150)
    @Column(name = "nompro", nullable = false, length = 150)
    private String razonSocial;

    @Size(max = 100)
    @Column(name = "apeppro", length = 100)
    private String apellidoPaterno;

    @Size(max = 100)
    @Column(name = "apempro", length = 100)
    private String apellidoMaterno;

    @NotBlank(message = "El telefono es obligatorio")
    @Size(max = 15)
    @Column(name = "telpro", nullable = false, length = 15)
    private String telefono;

    @NotBlank(message = "El correo es obligatorio")
    @Email
    @Size(max = 50)
    @Column(name = "corpro", nullable = false, length = 50)
    private String correo;

    @Column(name = "estpro", nullable = false)
    private Boolean estado;
}
