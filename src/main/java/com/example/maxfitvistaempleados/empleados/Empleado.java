package com.example.maxfitvistaempleados.empleados;

import com.example.maxfitvistaempleados.ingreso.Ingresos;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "empleados")
public class Empleado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matricula;
    @Column(name = "nombre")
    private String nombre;
    @Column(name ="apellidos")
    private String apellidos;
    @Column(name = "genero")
    private String genero;
    @Column(name = "telefono")
    private Long telefono;
    @Column(name = "tipotrabajo")
    private String tiposTrabajo;
    @Column(name = "correo")
    private String correo;
    @Column(name = "contrasena")
    private String contrasena;
    @Column(name = "salario")
    private Double salario;





}
