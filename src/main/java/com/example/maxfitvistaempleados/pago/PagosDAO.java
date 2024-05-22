package com.example.maxfitvistaempleados.pago;

import com.example.maxfitvistaempleados.domain.DAO;
import com.example.maxfitvistaempleados.domain.HibernateUtil;
import com.example.maxfitvistaempleados.ingreso.Ingresos;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PagosDAO implements DAO<Pagos> {

    @Override
    public ArrayList<Pagos> getAll() {
        var salida = new ArrayList<Pagos>(0);

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Pagos> q = s.createQuery("from Pagos ", Pagos.class);
            salida = (ArrayList<Pagos>) q.getResultList();
        }
        return salida;
    }

    @Override
    public Pagos get(Long id) {
        var salida = new Pagos();

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            salida = s.get(Pagos.class, id);
        }

        return salida;
    }

    @Override
    public Pagos save(Pagos data) {
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
    public void update(Pagos data) {
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
        }    }

    @Override
    public void delete(Pagos data) {
        HibernateUtil.getSessionFactory().inTransaction(session -> {
            Pagos pagos = session.get(Pagos.class, data.getId());
            session.remove(pagos);
        });    }

    public static List<Pagos> obtenerIngresosPorTipoPago(String tipoPagoSeleccionado) {
        List<Pagos> resultados = new ArrayList<>();

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Pagos> q = s.createQuery("from Pagos where tipopago = :tipoPago", Pagos.class);
            q.setParameter("tipoPago", tipoPagoSeleccionado);
            resultados = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultados;
    }

    public static List<Pagos> obtenerIngresosPorTipoComprar(String grupo) {
        List<Pagos> resultados = new ArrayList<>();

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Pagos> q = s.createQuery("from Pagos where grupo = :grupo", Pagos.class);
            q.setParameter("grupo", grupo);
            resultados = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultados;
    }
    public static Long countClientes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("select count(p) from Pagos p", Long.class);
            return query.uniqueResult();
        }
    }
}
