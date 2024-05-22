package com.example.maxfitvistaempleados.dieta;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "alimentos")
public class Alimentos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_alimento;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "kcal_x_gramo")
    private Double kcalXGramo;

    @Column(name = "kcal_x_carbohidrato")
    private Double kcalXCarbohidrato;

    @Column(name = "kcal_x_grasa")
    private Double kcalXGrasa;

    @Column(name = "lugar_compra")
    private String lugarCompra;

    @OneToMany(mappedBy = "alimento", cascade = CascadeType.ALL)
    private Set<RecetasAlimentos> recetasAlimentos;
}


