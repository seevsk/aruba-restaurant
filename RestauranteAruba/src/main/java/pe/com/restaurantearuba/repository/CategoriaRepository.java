package pe.com.restaurantearuba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.restaurantearuba.entity.CategoriaEntity;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {

    List<CategoriaEntity> findByNombreContainingIgnoreCase(String nombre);

    List<CategoriaEntity> findByEstadoTrue();

    List<CategoriaEntity> findByEstadoTrueAndNombreContainingIgnoreCase(String nombre);
}
