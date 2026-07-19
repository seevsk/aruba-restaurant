package pe.com.restaurantearuba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.restaurantearuba.entity.TipoDocumento;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Integer> {

    java.util.List<TipoDocumento> findByNombreContainingIgnoreCase(String nombre);
}
