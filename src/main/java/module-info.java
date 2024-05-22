module com.example.maxfitvistaempleados {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires jasperreports;

    opens com.example.maxfitvistaempleados.clientes;
    opens com.example.maxfitvistaempleados.empleados;
    opens com.example.maxfitvistaempleados.admin;
    opens com.example.maxfitvistaempleados.ingreso;
    opens com.example.maxfitvistaempleados.pago;
    opens com.example.maxfitvistaempleados.rutina;
    opens com.example.maxfitvistaempleados.dieta;



    opens com.example.maxfitvistaempleados to javafx.fxml;
    opens com.example.maxfitvistaempleados.controller to javafx.fxml;

    exports com.example.maxfitvistaempleados;
    exports com.example.maxfitvistaempleados.controller;
}