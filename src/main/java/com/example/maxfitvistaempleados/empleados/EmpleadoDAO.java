package com.example.maxfitvistaempleados.empleados;

import com.example.maxfitvistaempleados.empleados.Empleado;
import com.example.maxfitvistaempleados.domain.DAO;
import com.example.maxfitvistaempleados.domain.HibernateUtil;
import com.example.maxfitvistaempleados.ingreso.Ingresos;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO implements DAO<Empleado> {

    @Override
    public ArrayList<Empleado> getAll() {
        var salida = new ArrayList<Empleado>(0);

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Empleado> q = s.createQuery("from Empleado", Empleado.class);
            salida = (ArrayList<Empleado>) q.getResultList();
        }
        return salida;
    }

    @Override
    public Empleado get(Long id) {
        var salida = new Empleado();

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            salida = s.get(Empleado.class, id);
        }

        return salida;
    }

    @Override
    public Empleado save(Empleado data) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            try {
                // Comienza la transacción.
                transaction = session.beginTransaction();

                // Guarda el nuevo pedido en la Base de Datos.
                session.save(data);

                // Commit de la transacción.
                transaction.commit();
            } catch (Exception e) {
                // Maneja cualquier excepción que pueda ocurrir durante la transacción.
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
            return data;
        }
    }

    @Override
    public void update(Empleado data) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            // Comienza la transacción.
            transaction = session.beginTransaction();

            // Actualiza el pedido en la Base de Datos.
            session.update(data);

            // Commit de la transacción.
            transaction.commit();
        } catch (Exception e) {
            // Maneja cualquier excepción que pueda ocurrir durante la transacción.
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Empleado data) {
        HibernateUtil.getSessionFactory().inTransaction(session -> {
            Empleado empleado = session.get(Empleado.class, data.getMatricula());
            session.remove(empleado);
        });
    }

    /**
     * Valida las credenciales de inicio de sesión del Empleado.
     *
     * @param email    Email del Empleado.
     * @param password Contraseña del Empleado.
     * @return El objeto Empleado si las credenciales son válidas, de lo contrario, null.
     */
    public Empleado validateUser(String email, String password) {
        Empleado result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Empleado> q = session.createQuery("from Empleado where correo=:u and contrasena=:p", Empleado.class);
            q.setParameter("u", email);
            q.setParameter("p", password);

            try {
                result = q.getSingleResult();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
    // Método para obtener la lista de todos los empleados desde la base de datos
    public static List<Empleado> obtenerTodosLosEmpleados() {
        List<Empleado> listaEmpleados = null;


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Crear una consulta utilizando HQL (Hibernate Query Language)
            Query<Empleado> query = session.createQuery("FROM Empleado", Empleado.class);
            // Ejecutar la consulta y obtener la lista de empleados
            listaEmpleados = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaEmpleados;
    }
}



