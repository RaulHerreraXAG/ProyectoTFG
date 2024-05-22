package com.example.maxfitvistaempleados.pago;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "pago")
public class Pagos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tipopago")
    private String tipopago;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "grupo")
    private String grupo;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "dinero")
    private Double dinero;

    @Column(name = "empleado")
    private String empleado;


}
