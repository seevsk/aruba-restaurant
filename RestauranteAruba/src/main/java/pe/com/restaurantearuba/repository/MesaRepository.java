package pe.com.restaurantearuba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.restaurantearuba.entity.Mesa;

public interface MesaRepository extends JpaRepository<Mesa, Integer> {

    List<Mesa> findByNumeroContainingIgnoreCase(String numero);
}
