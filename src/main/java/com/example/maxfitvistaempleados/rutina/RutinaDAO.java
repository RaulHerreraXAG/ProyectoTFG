package com.example.maxfitvistaempleados.rutina;

import com.example.maxfitvistaempleados.clientes.Clientes;
import com.example.maxfitvistaempleados.domain.DAO;
import com.example.maxfitvistaempleados.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class RutinaDAO implements DAO<Rutina> {
    @Override
    public ArrayList<Rutina> getAll() {
        var salida = new ArrayList<Rutina>(0);

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Rutina> q = s.createQuery("from Rutina", Rutina.class);
            salida = (ArrayList<Rutina>) q.getResultList();
        }
        return salida;
    }

    @Override
    public Rutina get(Long id) {
        var salida = new Rutina();

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            salida = s.get(Rutina.class, id);
        }

        return salida;
    }

    @Override
    public Rutina save(Rutina data) {
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
    public void update(Rutina data) {
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
    public void delete(Rutina data) {
        HibernateUtil.getSessionFactory().inTransaction(session -> {
            Rutina rutina = session.get(Rutina.class, data.getId());
            session.remove(rutina);
        });
    }

    public ArrayList<Rutina> getByCliente(Clientes cliente) {
        ArrayList<Rutina> salida = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Rutina> query = session.createQuery("from Rutina where clientes = :cliente", Rutina.class);
            query.setParameter("cliente", cliente);
            salida.addAll(query.getResultList());
        }

        return salida;
    }

    public static Long countClientes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("select count(r) from Rutina r", Long.class);
            return query.uniqueResult();
        }
    }

    public void deleteByCliente(Clientes cliente) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            try {
                // Comienza la transacción
                transaction = session.beginTransaction();

                // Crea y ejecuta la consulta para eliminar las dietas asociadas al cliente
                Query<?> query = session.createQuery("DELETE FROM Rutina WHERE clientes = :cliente");
                query.setParameter("cliente", cliente);
                query.executeUpdate();

                // Confirma la transacción
                transaction.commit();
            } catch (Exception e) {
                // Maneja cualquier excepción y revierte la transacción si es necesario
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    public List<Rutina> getByCliente2(Clientes cliente) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Rutina> query = session.createQuery("from Rutina where clientes = :cliente", Rutina.class);
            query.setParameter("cliente", cliente);
            return query.getResultList();
        }
    }


}
