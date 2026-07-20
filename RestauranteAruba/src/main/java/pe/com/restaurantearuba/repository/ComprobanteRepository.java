package pe.com.restaurantearuba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.restaurantearuba.entity.ComprobanteEntity;

public interface ComprobanteRepository extends JpaRepository<ComprobanteEntity, Integer> {

    List<ComprobanteEntity> findByTipoContainingIgnoreCaseOrSerieContainingIgnoreCaseOrNumeroContainingIgnoreCase(
            String tipo, String serie, String numero);
}
