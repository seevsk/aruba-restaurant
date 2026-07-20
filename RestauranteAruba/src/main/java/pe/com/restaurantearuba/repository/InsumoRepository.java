package pe.com.restaurantearuba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.restaurantearuba.entity.InsumoEntity;

public interface InsumoRepository extends JpaRepository<InsumoEntity, Integer> {

    List<InsumoEntity> findByNombreContainingIgnoreCase(String nombre);
}
