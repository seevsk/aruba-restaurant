package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.TipoDocumento;

public interface TipoDocumentoService {

    List<TipoDocumento> listar();

    List<TipoDocumento> buscar(String texto);

    TipoDocumento obtenerPorId(Integer id);

    TipoDocumento registrar(TipoDocumento tipoDocumento);

    TipoDocumento actualizar(Integer id, TipoDocumento tipoDocumento);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
