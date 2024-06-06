package com.example.maxfitvistaempleados;

import com.example.maxfitvistaempleados.admin.Admin;
import com.example.maxfitvistaempleados.clientes.Clientes;
//import com.example.maxfitvistaempleados.dieta.Alimentos;
//import com.example.maxfitvistaempleados.dieta.Dietas;
import com.example.maxfitvistaempleados.dieta.Dieta_Pre_Anadir;
import com.example.maxfitvistaempleados.dieta.Dietas;
import com.example.maxfitvistaempleados.dieta.Recetas;
import com.example.maxfitvistaempleados.empleados.Empleado;
import com.example.maxfitvistaempleados.ingreso.Ingresos;
import com.example.maxfitvistaempleados.pago.Pagos;
import com.example.maxfitvistaempleados.rutina.Ejercicios;
import com.example.maxfitvistaempleados.rutina.Rutina;
import lombok.Getter;
import lombok.Setter;

public class Sesion {
    @Getter
    @Setter
    private static Clientes cliente;

    @Getter
    @Setter
    private static Empleado empleado;

    @Getter
    @Setter
    private static Admin admin;

    @Getter
    @Setter
    private static Ingresos ingresos;


    @Getter
    @Setter
    private static Pagos pagos;

    @Getter
    @Setter
    private static Rutina rutina;

    @Getter
    @Setter
    private static Ejercicios ejercicios;

    @Getter
    @Setter
    private static Dietas dietas;



    @Getter
    @Setter
    private static Recetas recetas;

    @Getter
    @Setter
    private static Dieta_Pre_Anadir dietaPreAnadir;


}

