package pe.com.restaurantearuba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.restaurantearuba.entity.DetallePedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {

    @Query("""
            SELECT d FROM DetallePedido d
            WHERE LOWER(d.producto.nombre) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR CAST(d.pedido.id AS string) LIKE CONCAT('%', :texto, '%')
            """)
    List<DetallePedido> buscar(@Param("texto") String texto);
}
