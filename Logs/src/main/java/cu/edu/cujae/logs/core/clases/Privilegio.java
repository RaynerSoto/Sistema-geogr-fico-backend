package cu.edu.cujae.logs.core.clases;

import cu.edu.cujae.logs.core.enums.PrivilegioEnums;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "privilegios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Privilegio {
    @Id()
    @NotNull(message = "El id no puede ser nulo")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "privilegiosID",nullable = false,unique = true, updatable = false)
    private Long uuid;

    @NotNull(message = "El código no puede ser null")
    @NotBlank(message = "El código no puede estar vacío")
    @Size(min = 1,message = "El código debe tener mínimo 1 caracter")
    @Column(name = "codigo",nullable = false,unique = true)
    private String codigo;

    @NotNull(message = "La descripción no puede ser null")
    @NotBlank(message = "La descripción no puede estar vacío")
    @Size(min = 1,message = "La descripción debe tener mínimo 1 caracter")
    @Column(name = "descripcion",nullable = false,unique = true)
    private String descripcion;

    //FALTA LA ASIGnACIÒN DE LOS PRIVILEGIOS A LOS


    public Privilegio(PrivilegioEnums privilegioEnums) {
        this.codigo = privilegioEnums.getCodigo();
        this.descripcion = privilegioEnums.getDescripcion();
    }
}
