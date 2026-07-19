package pe.com.restaurantearuba.entity;

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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pedido")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codped")
    private Integer id;

    @CreationTimestamp
    @Column(name = "fecped", nullable = false, updatable = false)
    private LocalDateTime fecha;

    // PENDIENTE / ENTREGADO / CANCELADO. Habilitar reabre el pedido, deshabilitar lo cancela.
    @Size(max = 20)
    @Column(name = "estped", nullable = false, length = 20)
    private String estado;

    @NotNull(message = "El empleado es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codemp", nullable = false)
    private Empleado empleado;

    @NotNull(message = "La mesa es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codmes", nullable = false)
    private Mesa mesa;

    // Un pedido puede no tener cliente registrado (consumidor sin cuenta).
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codcli")
    private Cliente cliente;
}
