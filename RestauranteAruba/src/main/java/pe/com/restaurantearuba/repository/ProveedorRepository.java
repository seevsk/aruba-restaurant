package pe.com.restaurantearuba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.restaurantearuba.entity.ProveedorEntity;

public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Integer> {

    List<ProveedorEntity> findByRazonSocialContainingIgnoreCaseOrRucContainingIgnoreCase(String razonSocial, String ruc);
}
