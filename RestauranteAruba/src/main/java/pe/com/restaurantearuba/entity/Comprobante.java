package pe.com.restaurantearuba.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
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
@Table(name = "comprobante")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codcom")
    private Integer id;

    @NotBlank(message = "El tipo de comprobante es obligatorio")
    @Size(max = 20)
    @Column(name = "tipcom", nullable = false, length = 20)
    private String tipo;

    @NotBlank(message = "La serie es obligatoria")
    @Size(min = 4, max = 4, message = "La serie debe tener 4 caracteres")
    @Column(name = "numser", nullable = false, length = 4)
    private String serie;

    @NotBlank(message = "El numero es obligatorio")
    @Size(max = 8)
    @Column(name = "numcom", nullable = false, length = 8)
    private String numero;

    @CreationTimestamp
    @Column(name = "feccom", nullable = false, updatable = false)
    private LocalDateTime fecha;

    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El total debe ser mayor a 0")
    @Column(name = "totcom", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "estcom", nullable = false)
    private Boolean estado;

    @NotNull(message = "El pedido es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codped", nullable = false)
    private Pedido pedido;

    @NotNull(message = "El metodo de pago es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codmet", nullable = false)
    private MetodoPago metodoPago;
}
