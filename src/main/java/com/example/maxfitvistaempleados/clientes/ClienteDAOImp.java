package com.example.maxfitvistaempleados.clientes;

import com.example.maxfitvistaempleados.domain.DAO;
import com.example.maxfitvistaempleados.domain.HibernateUtil;
import com.example.maxfitvistaempleados.domain.DAO;
import com.example.maxfitvistaempleados.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

/**
 * Implementación de la interfaz ClientesDAO para acceder y gestionar datos de Clientess en una base de datos.
 */
public class ClienteDAOImp implements DAO<Clientes> {

    @Override
    public ArrayList<Clientes> getAll() {
        var salida = new ArrayList<Clientes>(0);

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Clientes> q = s.createQuery("from Clientes", Clientes.class);
            salida = (ArrayList<Clientes>) q.getResultList();
        }
        return salida;
    }

    @Override
    public Clientes get(Long id) {
        var salida = new Clientes();

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            salida = s.get(Clientes.class, id);
        }

        return salida;
    }

    @Override
    public Clientes save(Clientes data) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            try {
                // Comienza la transacción.
                transaction = session.beginTransaction();

                // Guarda el nuevo ítem en la Base de Datos.
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
    public void update(Clientes data) {
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
    public void delete(Clientes data) {
        HibernateUtil.getSessionFactory().inTransaction(session -> {
            Clientes cliente = session.get(Clientes.class, data.getMatricula());
            session.remove(cliente);
        });
    }

    /**
     * Valida las credenciales de inicio de sesión del Clientes.
     *
     * @param email    Email del Clientes.
     * @param password Contraseña del Clientes.
     * @return El objeto Clientes si las credenciales son válidas, de lo contrario, null.
     */
    public Clientes validateUser(String email, String password) {
        Clientes result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Clientes> q = session.createQuery("from Clientes where correo=:u and contrasena=:p", Clientes.class);
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

    public static Long countClientes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("select count(c) from Clientes c", Long.class);
            return query.uniqueResult();
        }
    }
}

