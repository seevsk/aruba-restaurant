package pe.com.restaurantearuba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.restaurantearuba.entity.Distrito;

public interface DistritoRepository extends JpaRepository<Distrito, Integer> {

    List<Distrito> findByNombreContainingIgnoreCase(String nombre);
}
