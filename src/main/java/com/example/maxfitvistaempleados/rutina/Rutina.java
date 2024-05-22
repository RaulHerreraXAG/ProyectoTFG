package com.example.maxfitvistaempleados.rutina;

import com.example.maxfitvistaempleados.clientes.Clientes;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Entity
@Table(name = "Rutina")
public class Rutina implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "dia")
    private String dia;
    @Column(name = "series")
    private Integer series;
    @Column(name = "repeticiones")
    private Integer repeticiones;



    @ManyToOne
    @JoinColumn(name = "clientes", referencedColumnName = "matricula")
    @ToString.Exclude
    private Clientes clientes;

    @ManyToOne
    @JoinColumn(name = "ejercicio", referencedColumnName = "id")
    @ToString.Exclude
    private Ejercicios ejercicios;



}
