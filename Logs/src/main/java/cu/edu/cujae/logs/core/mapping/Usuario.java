package cu.edu.cujae.logs.core.mapping;

import cu.edu.cujae.logs.core.dto.UsuarioDto;
import cu.edu.cujae.logs.core.utils.Validacion;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(name = "usuarioID",nullable = false, length = 36, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_seq", sequenceName = "USER_SEQUENCE")
    private Long uuid;

    @NotNull(message = "El nombre de usuario no puede ser nulo")
    @Size(min = 4,max = 50,message = "El nombre de usuario se encuentra en un rango de 4 y 50 caracteres")
    @NotBlank(message = "El nombre de usuario no puede ser vacío")
    @Column(name = "username",nullable = false, length = 100)
    private String username;

    @NotNull(message = "El nombre completo no puede ser nulo")
    @Size(min = 2,max = 100,message = "El nombre completo debe tener entre 2 y 100 caracteres")
    @NotBlank(message = "El nombre completo no puede ser vacío")
    @Column(name = "nombre",nullable = false, length = 100)
    private String name;

    @NotNull(message = "El correo no puede ser nulo")
    @NotBlank(message = "El correo no puede ser vacío")
    @Size(max = 100,message = "El correo tiene un máximo de 100 caracteres")
    @Email(message = "Correo no válido")
    @Column(name = "email",nullable = false, length = 100)
    private String email;

    @NotNull(message = "El rol no puede ser nulo")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rolID")
    private Rol rol;


    @OneToMany(mappedBy = "nombreUsuario",cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<Registro> registroList;

    @NotNull(message = "El sexo no puede ser nulo")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sexoID")
    private Sexo sexo;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    public Usuario(Usuario usuario){
        this.uuid = usuario.getUuid();
        this.username = usuario.getUsername();
        this.name = usuario.getName();
        this.email = usuario.getEmail();
        this.rol = usuario.getRol();
        this.sexo = usuario.getSexo();
        this.activo = usuario.isActivo();
    }

    public Usuario(UsuarioDto usuarioDto,Rol rol, Sexo sexo) throws Exception {
        this.uuid = usuarioDto.getUuid();
        this.username = usuarioDto.getUsername();
        this.name = usuarioDto.getName();
        this.email = usuarioDto.getEmail();
        this.activo = usuarioDto.isActivo();
        this.rol = rol;
        this.sexo = sexo;
    }

    public Usuario(Optional<Usuario> usuario){
        this.uuid = usuario.get().getUuid();
        this.username = usuario.get().getUsername();
        this.name = usuario.get().getName();
        this.email = usuario.get().getEmail();
        this.rol = usuario.get().getRol();
        this.sexo = usuario.get().getSexo();
        this.activo = usuario.get().isActivo();
    }

    @PrePersist
    public void prePersist(){
        String validacion = Validacion.comprobacionValidador(this);
        if (validacion.isBlank() == false)
            throw new UnsupportedOperationException(validacion);
    }

    @PreUpdate
    public void preUpdate(){
        if (this.rol.getRol().equalsIgnoreCase("SuperAdministrador"))
            throw new UnsupportedOperationException("No se puede modificar el usuario Super Administrador");
        String validacion = Validacion.comprobacionValidador(this);
        if (validacion.isBlank() == false){
            throw new UnsupportedOperationException(validacion);
        } else if (this.getUuid() == null) {
            throw new UnsupportedOperationException("El id del usuario no puede ser nulo");
        }
    }

    @PreRemove
    public void preRemove(){
        throw new UnsupportedOperationException("No se puede eliminar usuarios");
    }
}
