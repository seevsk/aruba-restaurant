package pe.com.restaurantearuba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.restaurantearuba.entity.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

    @Query("""
            SELECT c FROM ClienteEntity c
            WHERE LOWER(c.nombres) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR LOWER(c.apellidoPaterno) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR LOWER(c.apellidoMaterno) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR c.numeroDocumento LIKE CONCAT('%', :texto, '%')
            """)
    List<ClienteEntity> buscar(@Param("texto") String texto);

    List<ClienteEntity> findByEstadoTrue();

    @Query("""
            SELECT c FROM ClienteEntity c
            WHERE c.estado = true
              AND (LOWER(c.nombres) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR LOWER(c.apellidoPaterno) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR LOWER(c.apellidoMaterno) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR c.numeroDocumento LIKE CONCAT('%', :texto, '%'))
            """)
    List<ClienteEntity> buscarActivos(@Param("texto") String texto);
}
