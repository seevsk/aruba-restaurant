package pe.com.restaurantearuba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.restaurantearuba.entity.TipoDocumentoEntity;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumentoEntity, Integer> {

    java.util.List<TipoDocumentoEntity> findByNombreContainingIgnoreCase(String nombre);

    java.util.List<TipoDocumentoEntity> findByEstadoTrue();

    java.util.List<TipoDocumentoEntity> findByEstadoTrueAndNombreContainingIgnoreCase(String nombre);
}
