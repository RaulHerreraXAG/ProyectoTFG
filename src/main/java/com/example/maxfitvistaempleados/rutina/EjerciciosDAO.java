package com.example.maxfitvistaempleados.rutina;

import com.example.maxfitvistaempleados.domain.DAO;
import com.example.maxfitvistaempleados.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class EjerciciosDAO implements DAO<Ejercicios> {
    @Override
    public ArrayList<Ejercicios> getAll() {
        var salida = new ArrayList<Ejercicios>(0);

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Ejercicios> q = s.createQuery("from Ejercicios", Ejercicios.class);
            salida = (ArrayList<Ejercicios>) q.getResultList();
        }
        return salida;
    }

    @Override
    public Ejercicios get(Long id) {
        var salida = new Ejercicios();

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            salida = s.get(Ejercicios.class, id);
        }

        return salida;
    }

    @Override
    public Ejercicios save(Ejercicios data) {
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
    public void update(Ejercicios data) {
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
    public void delete(Ejercicios data) {
        HibernateUtil.getSessionFactory().inTransaction(session -> {
            Ejercicios ejercicios = session.get(Ejercicios.class, data.getId());
            session.remove(ejercicios);
        });
    }

    // Método para obtener solo los nombres de los ejercicios
    public List<Ejercicios> getAllNames() {
        List<Ejercicios> salida = new ArrayList<>();

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Ejercicios> q = s.createQuery("select e.nombre from Ejercicios e", Ejercicios.class);
            salida = q.getResultList();
        }
        return salida;
    }
}
