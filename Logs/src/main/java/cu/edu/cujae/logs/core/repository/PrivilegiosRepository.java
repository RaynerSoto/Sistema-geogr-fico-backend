package cu.edu.cujae.logs.core.repository;

import cu.edu.cujae.logs.core.mapping.Privilegio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrivilegiosRepository extends JpaRepository<Privilegio,Long> {
    public Optional<Privilegio> findByCodigoEqualsIgnoreCase(String nombre);
}
