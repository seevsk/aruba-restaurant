package pe.com.restaurantearuba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.restaurantearuba.entity.ProveedorEntity;

public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Integer> {

    List<ProveedorEntity> findByRazonSocialContainingIgnoreCaseOrRucContainingIgnoreCase(String razonSocial, String ruc);

    List<ProveedorEntity> findByEstadoTrue();

    @Query("SELECT p FROM ProveedorEntity p WHERE p.estado = true AND (LOWER(p.razonSocial) LIKE LOWER(CONCAT('%', :texto, '%')) OR LOWER(p.ruc) LIKE LOWER(CONCAT('%', :texto, '%')))")
    List<ProveedorEntity> buscarHabilitados(@Param("texto") String texto);
}
