package com.example.maxfitvistaempleados.dieta;

import com.example.maxfitvistaempleados.domain.DAO;
import com.example.maxfitvistaempleados.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class RecetasDAOAlimentos implements DAO<RecetasAlimentos> {
    @Override
    public ArrayList<RecetasAlimentos> getAll() {
        var salida = new ArrayList<RecetasAlimentos>(0);

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<RecetasAlimentos> q = s.createQuery("from RecetasAlimentos", RecetasAlimentos.class);
            salida = (ArrayList<RecetasAlimentos>) q.getResultList();
        }
        return salida;
    }

    @Override
    public RecetasAlimentos get(Long id) {
        var salida = new RecetasAlimentos();

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            salida = s.get(RecetasAlimentos.class, id);
        }

        return salida;
    }

    @Override
    public RecetasAlimentos save(RecetasAlimentos data) {
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
    public void update(RecetasAlimentos data) {
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
    public void delete(RecetasAlimentos data) {
        HibernateUtil.getSessionFactory().inTransaction(session -> {
            RecetasAlimentos recetasAlimentos = session.get(RecetasAlimentos.class, data.getId_RA());
            session.remove(recetasAlimentos);
        });
    }
    public ArrayList<RecetasAlimentos> getByReceta(Recetas receta) {
        ArrayList<RecetasAlimentos> salida = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<RecetasAlimentos> query = session.createQuery("from RecetasAlimentos where receta = :receta", RecetasAlimentos.class);
            query.setParameter("receta", receta);
            salida.addAll(query.getResultList());
        }

        return salida;
    }
}
