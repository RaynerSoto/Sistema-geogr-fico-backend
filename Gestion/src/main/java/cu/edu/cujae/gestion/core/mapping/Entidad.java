package cu.edu.cujae.gestion.core.mapping;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;

import java.sql.Time;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entidades")
public class Entidad {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull(message = "El id de la entidad no puede ser nulo")
    @Column(name = "identidad", nullable = false, updatable = false, unique = true)
    private Long uuid;

    @NotBlank(message = "El nombre no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "El nombre de la entidad no puede ser null")
    @Size(min = 3,max = 100,message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(name = "nombre",nullable = false, unique = true,length = 100)
    private String nombre;

    @Column(name = "entidadmadre",length = 100)
    private String entidadMadre;

    @NotNull(message = "El municipio no puede ser nulo")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idmunicipio")
    public Municipio municipio;

    @NotNull(message = "La provincia no puede ser nulo")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idprovincia")
    public Provincia provincia;

    @NotBlank(message = "La calle principal de la entidad no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "La calle principal de la entidad no puede ser null")
    @Column(name = "callePrincipal",nullable = false)
    public String calle_principal;

    @Column(name = "entrecalle1")
    private String entrecalle1;

    @Column(name = "entrecalle2")
    private String entrecalle2;

    @Column(name = "numero")
    private String numeroCasa;

    @Column(name = "localidad")
    private String localidad;

    @Column(name = "horario_entrada")
    @Temporal(TemporalType.TIME)
    private Time horario_entrada;

    @Column(name = "horario_salida")
    @Temporal(TemporalType.TIME)
    private Time horario_salida;

    @Column(name = "horario_propuesto_entrada")
    @Temporal(TemporalType.TIME)
    private Time horario_propuesto_entrada;

    @Column(name = "horario_propuesto_salida")
    @Temporal(TemporalType.TIME)
    private Time horario_propuesto_salida;

    @Column(name = "datosAdicionales")
    private String datos;

    @Column(name = "zona_transporte")
    private Long zona_transporte;

    @Column(name = "areaSalud")
    private Long areaSalud;

    @Column(name = "distcentroasalud")
    private Double distcentroasalud;

    @Column(name = "distcentrozonat")
    private Double distcentrozonat;

    @Column(name = "distcentromunic")
    private Double distcentromunic;

    @Column(name = "distcentroprov")
    private Double distcentroprov;

    @Column(name = "geolocalizacion",columnDefinition = "geometry")
    private Geometry geometry;

    @ManyToMany
    @JoinTable(name = "entidadpersonal"
            ,joinColumns = @JoinColumn(name = "identidad")
            ,inverseJoinColumns = @JoinColumn(name = "idpersonal"))
    private List<Empleado> personal;
}
