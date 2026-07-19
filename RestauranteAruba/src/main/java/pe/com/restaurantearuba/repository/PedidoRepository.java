package pe.com.restaurantearuba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.restaurantearuba.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query("""
            SELECT p FROM Pedido p
            LEFT JOIN p.cliente c
            WHERE LOWER(p.estado) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR LOWER(p.mesa.numero) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR LOWER(p.empleado.nombres) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR (c IS NOT NULL AND LOWER(c.nombres) LIKE LOWER(CONCAT('%', :texto, '%')))
            """)
    List<Pedido> buscar(@Param("texto") String texto);
}
