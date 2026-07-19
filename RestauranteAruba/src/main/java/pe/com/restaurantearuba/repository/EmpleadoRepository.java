package pe.com.restaurantearuba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.restaurantearuba.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    @Query("""
            SELECT e FROM Empleado e
            WHERE LOWER(e.nombres) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR LOWER(e.apellidoPaterno) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR LOWER(e.apellidoMaterno) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR e.numeroDocumento LIKE CONCAT('%', :texto, '%')
            """)
    List<Empleado> buscar(@Param("texto") String texto);
}
