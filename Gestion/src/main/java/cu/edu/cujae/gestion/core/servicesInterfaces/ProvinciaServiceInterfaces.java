package cu.edu.cujae.gestion.core.servicesInterfaces;

import cu.edu.cujae.gestion.core.mapping.Provincia;

import java.util.List;
import java.util.Optional;

public interface ProvinciaServiceInterfaces {
    public List<Provincia> listadoProvincia();

    public Optional<Provincia> buscarProvinciaPorNombre(String nombre) throws Exception;
}
