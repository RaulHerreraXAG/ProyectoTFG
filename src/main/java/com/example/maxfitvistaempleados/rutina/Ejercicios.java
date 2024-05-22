package com.example.maxfitvistaempleados.rutina;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Ejercicios")
public class Ejercicios implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "cuerpo")
    private String cuerpo;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "observaciones")
    private String observacion;

    @OneToMany(mappedBy = "ejercicios", fetch = FetchType.EAGER)
    private List<Rutina> rutinas = new ArrayList<>();




}
