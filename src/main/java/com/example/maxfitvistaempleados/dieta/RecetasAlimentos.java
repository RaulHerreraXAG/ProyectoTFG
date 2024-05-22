package com.example.maxfitvistaempleados.dieta;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "recetas_alimentos")
public class RecetasAlimentos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_RA;

    @ManyToOne
    @JoinColumn(name = "id_alimento2" , referencedColumnName = "id_alimento")
    private Alimentos alimento;


    @ManyToOne
    @JoinColumn(name = "id_receta2" , referencedColumnName = "id_receta")
    private Recetas receta;

    @Column(name = "cantidad")
    private Double cantidad;

    @Column(name = "unidad")
    private String unidad;
}

