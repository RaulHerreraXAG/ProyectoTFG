package com.example.maxfitvistaempleados.dieta;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Data
@Entity
@Table(name = "dietas_predeterminadas")
public class Dieta_Predeterminada implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_dieta_predeterminada;

    @ManyToOne
    @JoinColumn(name = "id_receta", referencedColumnName = "id_receta")
    private Recetas receta;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "momento_dia")
    private String momento_dia;

    @Column(name = "dia_semana")
    private String dia_semana;

}
