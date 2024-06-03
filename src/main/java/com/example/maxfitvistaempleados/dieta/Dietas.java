package com.example.maxfitvistaempleados.dieta;

import com.example.maxfitvistaempleados.clientes.Clientes;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "dietas")
public class Dietas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_dieta;

    @Column(name = "dia")
    private String dia;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "matricula")
    private Clientes clientes;

    @ManyToOne
    @JoinColumn(name = "id_receta", referencedColumnName = "id_receta")
    private Recetas receta;

    @Column(name = "momento_dia")
    private String momento;
}

