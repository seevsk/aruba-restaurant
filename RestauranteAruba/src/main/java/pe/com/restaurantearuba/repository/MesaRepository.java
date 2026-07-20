package pe.com.restaurantearuba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.restaurantearuba.entity.MesaEntity;

public interface MesaRepository extends JpaRepository<MesaEntity, Integer> {

    List<MesaEntity> findByNumeroContainingIgnoreCase(String numero);
}
