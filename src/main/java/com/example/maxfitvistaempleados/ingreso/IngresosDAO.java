package com.example.maxfitvistaempleados.ingreso;

import com.example.maxfitvistaempleados.domain.DAO;
import com.example.maxfitvistaempleados.domain.HibernateUtil;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class IngresosDAO implements DAO<Ingresos> {


    @Override
    public ArrayList<Ingresos> getAll() {
        var salida = new ArrayList<Ingresos>(0);

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Ingresos> q = s.createQuery("from Ingresos", Ingresos.class);
            salida = (ArrayList<Ingresos>) q.getResultList();
        }
        return salida;
    }

    @Override
    public Ingresos get(Long id) {
        var salida = new Ingresos();

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            salida = s.get(Ingresos.class, id); // Aquí se pasa el ID proporcionado como argumento
        }

        return salida;
    }


    @Override
    public Ingresos save(Ingresos data) {
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
    public void update(Ingresos data) {
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
    public void delete(Ingresos data) {
        HibernateUtil.getSessionFactory().inTransaction(session -> {
            Ingresos ingresos = session.get(Ingresos.class, data.getId());
            session.remove(ingresos);
        });

    }

    public static List<Ingresos> obtenerIngresosPorTipoPago(String tipoPagoSeleccionado) {
        List<Ingresos> resultados = new ArrayList<>();

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Ingresos> q = s.createQuery("from Ingresos where tipopago = :tipoPago", Ingresos.class);
            q.setParameter("tipoPago", tipoPagoSeleccionado);
            resultados = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultados;
    }

    public static List<Ingresos> obtenerIngresosPorTipoComprar(String grupo) {
        List<Ingresos> resultados = new ArrayList<>();

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Ingresos> q = s.createQuery("from Ingresos where grupo = :grupo", Ingresos.class);
            q.setParameter("grupo", grupo);
            resultados = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultados;
    }

    public static Long countClientes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("select count(i) from Ingresos i", Long.class);
            return query.uniqueResult();
        }
    }

    public Double obtenerTotalDinero() {
        Double totalDinero = 0.0;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Double> q = s.createQuery("select sum(i.dinero) from Ingresos i", Double.class);
            totalDinero = q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalDinero;
    }



}
