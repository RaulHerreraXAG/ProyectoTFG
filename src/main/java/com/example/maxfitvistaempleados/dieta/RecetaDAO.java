package com.example.maxfitvistaempleados.dieta;

import com.example.maxfitvistaempleados.domain.DAO;
import com.example.maxfitvistaempleados.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class RecetaDAO implements DAO<Recetas> {

    @Override
    public ArrayList<Recetas> getAll() {
        var salida = new ArrayList<Recetas>(0);

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Recetas> q = s.createQuery("from Recetas", Recetas.class);
            salida = (ArrayList<Recetas>) q.getResultList();
        }
        return salida;
    }

    @Override
    public Recetas get(Long id) {
        var salida = new Recetas();

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            salida = s.get(Recetas.class, id);
        }

        return salida;
    }

    @Override
    public Recetas save(Recetas data) {
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
    public void update(Recetas data) {
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
    public void delete(Recetas data) {
        HibernateUtil.getSessionFactory().inTransaction(session -> {
            Recetas recetas = session.get(Recetas.class, data.getIdReceta());
            session.remove(recetas);
        });
    }

    //Método para buscar una receta por su ID en la base de datos
    public Recetas findById(Integer id) {
        Recetas receta = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            // Obtener la sesión de Hibernate
            session = HibernateUtil.getSessionFactory().openSession();

            // Realizar la consulta para obtener la receta por su ID
            String hql = "FROM Recetas WHERE id = :recetaId";
            Query<Recetas> query = session.createQuery(hql);
            query.setParameter("recetaId", id);

            // Obtener la receta
            receta = query.uniqueResult();
        } catch (Exception e) {
            // Manejar cualquier excepción
            e.printStackTrace();
        } finally {
            // Cerrar la sesión de Hibernate
            if (session != null) {
                session.close();
            }
        }
        return receta;
    }
}
