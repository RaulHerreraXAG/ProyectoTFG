package com.example.maxfitvistaempleados.dieta;

import com.example.maxfitvistaempleados.clientes.Clientes;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Data
@Entity
@Table(name = "dietaspred_clientes")
public class Dieta_Pre_Anadir implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_dietas_predeterminadas", referencedColumnName = "id_dieta_predeterminada")
    private Dieta_Predeterminada dietaPredeterminada;

    @ManyToOne
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    private Clientes clientes;

    public Dieta_Pre_Anadir(Integer id, Dieta_Predeterminada dietaPredeterminada, Clientes clientes) {
        this.id = id;
        this.dietaPredeterminada = dietaPredeterminada;
        this.clientes = clientes;
    }
    public Dieta_Pre_Anadir() {

    }

}
