package pe.com.restaurantearuba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.restaurantearuba.entity.DetallePedidoEntity;

public interface DetallePedidoRepository extends JpaRepository<DetallePedidoEntity, Integer> {

    @Query("""
            SELECT d FROM DetallePedidoEntity d
            WHERE LOWER(d.producto.nombre) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR CAST(d.pedido.id AS string) LIKE CONCAT('%', :texto, '%')
            """)
    List<DetallePedidoEntity> buscar(@Param("texto") String texto);
}
