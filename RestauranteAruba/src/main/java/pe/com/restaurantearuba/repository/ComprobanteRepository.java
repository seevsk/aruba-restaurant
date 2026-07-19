package pe.com.restaurantearuba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.restaurantearuba.entity.Comprobante;

public interface ComprobanteRepository extends JpaRepository<Comprobante, Integer> {

    List<Comprobante> findByTipoContainingIgnoreCaseOrSerieContainingIgnoreCaseOrNumeroContainingIgnoreCase(
            String tipo, String serie, String numero);
}
