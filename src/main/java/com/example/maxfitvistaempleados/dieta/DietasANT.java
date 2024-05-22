package com.example.maxfitvistaempleados.dieta;/*package com.example.maxfitvistaempleados.dieta;

import com.example.maxfitvistaempleados.clientes.Clientes;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;


@Data
@Entity
@Table(name = "dietas")
public class Dietas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dia")
    private String dia;
    @Column(name = "tPreparacion")
    private Integer tPreparacion;
    @Column(name = "comida")
    private String comida;
    @Column(name = "receta")
    private String receta;
    @Column(name = "kcal")
    private Double kcal;
    @Column(name = "carbohidratos")
    private Double carbohidratos;
    @Column(name = "grasas")
    private Double grasas;


    @ManyToOne
    @JoinColumn(name = "clientes", referencedColumnName = "matricula")
    private Clientes clientes;

    @ManyToOne
    @JoinColumn(name = "alimento", referencedColumnName = "id")
    private Alimentos alimentos;
}

*/
