package pe.com.restaurantearuba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.restaurantearuba.entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    List<Proveedor> findByRazonSocialContainingIgnoreCaseOrRucContainingIgnoreCase(String razonSocial, String ruc);
}
