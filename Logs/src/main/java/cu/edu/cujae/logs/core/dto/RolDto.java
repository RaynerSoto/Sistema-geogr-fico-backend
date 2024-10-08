package cu.edu.cujae.logs.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cu.edu.cujae.logs.core.mapper.Rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;


    @NotBlank(message = "El rol no puede estar vacío")
    @NotNull(message = "El rol no puede ser null")
    @Size(min = 4,max = 50,message = "El rol debe tener entre 4 y 50 caracteres")
    private String nombre;


    public RolDto(Rol rol) {
        this.id = rol.getUuid();
        this.nombre = rol.getRol();
    }

    public RolDto(Optional<Rol> rol) {
        this.id = rol.get().getUuid();
        this.nombre = rol.get().getRol();
    }
}
