package cu.edu.cujae.logs.core.repository;

import cu.edu.cujae.logs.core.mapping.Privilegio;
import cu.edu.cujae.logs.core.mapping.PrivilegioRol;
import cu.edu.cujae.logs.core.mapping.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrivilegioRolRepository extends JpaRepository<PrivilegioRol,Long> {
    public Optional<PrivilegioRol> findByRolEqualsAndPrivilegioCodigoEquals(Rol rol, Privilegio privilegio);
}
