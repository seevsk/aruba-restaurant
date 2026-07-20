package pe.com.restaurantearuba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.restaurantearuba.entity.ComprobanteEntity;

public interface ComprobanteRepository extends JpaRepository<ComprobanteEntity, Integer> {

    List<ComprobanteEntity> findByTipoContainingIgnoreCaseOrSerieContainingIgnoreCaseOrNumeroContainingIgnoreCase(
            String tipo, String serie, String numero);

    List<ComprobanteEntity> findByEstadoTrue();

    @Query("SELECT c FROM ComprobanteEntity c WHERE c.estado = true AND (LOWER(c.tipo) LIKE LOWER(CONCAT('%', :texto, '%')) OR LOWER(c.serie) LIKE LOWER(CONCAT('%', :texto, '%')) OR LOWER(c.numero) LIKE LOWER(CONCAT('%', :texto, '%')))")
    List<ComprobanteEntity> buscarHabilitados(@Param("texto") String texto);
}
