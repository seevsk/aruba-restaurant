package pe.com.restaurantearuba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.restaurantearuba.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("""
            SELECT c FROM Cliente c
            WHERE LOWER(c.nombres) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR LOWER(c.apellidoPaterno) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR LOWER(c.apellidoMaterno) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR c.numeroDocumento LIKE CONCAT('%', :texto, '%')
            """)
    List<Cliente> buscar(@Param("texto") String texto);
}
