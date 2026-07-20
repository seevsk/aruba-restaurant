package pe.com.restaurantearuba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.restaurantearuba.entity.DistritoEntity;

public interface DistritoRepository extends JpaRepository<DistritoEntity, Integer> {

    List<DistritoEntity> findByNombreContainingIgnoreCase(String nombre);
}
