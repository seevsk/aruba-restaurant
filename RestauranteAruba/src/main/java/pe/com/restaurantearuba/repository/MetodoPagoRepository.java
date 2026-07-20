package pe.com.restaurantearuba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.restaurantearuba.entity.MetodoPagoEntity;

public interface MetodoPagoRepository extends JpaRepository<MetodoPagoEntity, Integer> {

    List<MetodoPagoEntity> findByNombreContainingIgnoreCase(String nombre);
}
