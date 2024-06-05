package com.example.maxfitvistaempleados.dieta;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "recetas")
public class Recetas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receta")
    private Long idReceta;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tiempo_preparacion")
    private Integer tiempoPreparacion;

    @Column(name = "kcal_total")
    private Double kcalTotal;

    @Column(name = "carbohidratos_total")
    private Double carbohidratosTotal;

    @Column(name = "grasas_total")
    private Double grasasTotal;

    @Column(name = "Pasos")
    private String pasos;

    @Column(name = "alimentos")
    private String alimentos;

    @Column(name = "momento_dia")
    private String momento_dia;
}
