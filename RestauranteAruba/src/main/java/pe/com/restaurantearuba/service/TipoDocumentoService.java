package pe.com.restaurantearuba.service;

import java.util.List;

import pe.com.restaurantearuba.entity.TipoDocumentoEntity;

public interface TipoDocumentoService {

    List<TipoDocumentoEntity> listar();

    List<TipoDocumentoEntity> buscar(String texto);

    TipoDocumentoEntity obtenerPorId(Integer id);

    TipoDocumentoEntity registrar(TipoDocumentoEntity tipoDocumento);

    TipoDocumentoEntity actualizar(Integer id, TipoDocumentoEntity tipoDocumento);

    void eliminar(Integer id);

    void habilitar(Integer id);

    void deshabilitar(Integer id);
}
