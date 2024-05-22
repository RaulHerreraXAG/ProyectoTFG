package com.example.maxfitvistaempleados.clientes;

//import com.example.maxfitvistaempleados.dieta.Dietas;
import jakarta.persistence.*;
import lombok.Data;
import com.example.maxfitvistaempleados.rutina.Rutina;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Clientes")
public class Clientes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matricula")
    private Long matricula;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "edad")
    private int edad;
    @Column(name = "genero")
    private String genero;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "altura")
    private Double altura;
    @Column(name = "peso")
    private Double peso;
    @Column(name = "correo")
    private String correo;
    @Column(name = "contrasena")
    private String contrasena;
    @Column(name = "fechaInicio")
    private LocalDate fechaInicio;
    @Column(name = "fechaFin")
    private LocalDate fechaFin;

    @OneToMany(mappedBy = "clientes", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Rutina> rutinas = new ArrayList<>();
/*
    @OneToMany(mappedBy = "dietas", fetch = FetchType.EAGER)
    private List<Dietas> dietas = new ArrayList<>();
 */
}
