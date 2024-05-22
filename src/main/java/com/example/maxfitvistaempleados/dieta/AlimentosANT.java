/*
package com.example.maxfitvistaempleados.dieta;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "alimento")
public class AlimentosANT implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "cantidad")
    private Double cantidad;
    @Column (name = "kcalXgramo")
    private Double kcalXgramo;
    @Column (name = "carbohidratos")
    private Double carbohidratos;
    @Column (name = "grasas")
    private Double grasas;
    @Column (name = "lugardecompra")
    private String lugardecompra;

    @OneToMany
    @JoinColumn(name = "dietas",referencedColumnName = "id")
    private List<Dietas> dietas;
}
*/