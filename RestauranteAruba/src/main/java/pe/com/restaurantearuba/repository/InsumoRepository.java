package pe.com.restaurantearuba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.restaurantearuba.entity.Insumo;

public interface InsumoRepository extends JpaRepository<Insumo, Integer> {

    List<Insumo> findByNombreContainingIgnoreCase(String nombre);
}
