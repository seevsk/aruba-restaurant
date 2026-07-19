package pe.com.restaurantearuba.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mesa")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codmes")
    private Integer id;

    @NotBlank(message = "El numero de mesa es obligatorio")
    @Size(max = 10)
    @Column(name = "nummes", nullable = false, length = 10)
    private String numero;

    @NotNull
    @Min(value = 1, message = "La capacidad debe ser mayor a 0")
    @Column(name = "capmes", nullable = false)
    private Integer capacidad;

    // DISPONIBLE / OCUPADA / INACTIVO. Habilitar y deshabilitar alternan entre DISPONIBLE e INACTIVO.
    @Size(max = 20)
    @Column(name = "estmes", nullable = false, length = 20)
    private String estado;
}
