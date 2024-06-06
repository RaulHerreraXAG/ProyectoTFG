package com.example.maxfitvistaempleados.dieta;

import com.example.maxfitvistaempleados.clientes.Clientes;
import com.example.maxfitvistaempleados.domain.DAO;
import com.example.maxfitvistaempleados.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Dieta_Pre_AnadirDAO implements DAO<Dieta_Pre_Anadir> {
    @Override
    public ArrayList<Dieta_Pre_Anadir> getAll() {
        var salida = new ArrayList<Dieta_Pre_Anadir>(0);

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Dieta_Pre_Anadir> q = s.createQuery("from Dieta_Pre_Anadir", Dieta_Pre_Anadir.class);
            salida = (ArrayList<Dieta_Pre_Anadir>) q.getResultList();
        }
        return salida;
    }

    @Override
    public Dieta_Pre_Anadir get(Long id) {
        var salida = new Dieta_Pre_Anadir();

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            salida = s.get(Dieta_Pre_Anadir.class, id);
        }

        return salida;
    }

    @Override
    public Dieta_Pre_Anadir save(Dieta_Pre_Anadir data) {
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
    public void update(Dieta_Pre_Anadir data) {
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
    public void delete(Dieta_Pre_Anadir data) {
        HibernateUtil.getSessionFactory().inTransaction(session -> {
            Dieta_Pre_Anadir dietas = session.get(Dieta_Pre_Anadir.class, data.getId());
            session.remove(dietas);
        });
    }
/*
    public void addDietasToCliente(Long idCliente, String tipoDieta) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Consulta para obtener los id_dieta_predeterminada según el tipo de dieta
            Query<Long> query = session.createQuery(
                    "SELECT id FROM Dieta_Predeterminada WHERE tipo = :tipoDieta", Long.class);
            query.setParameter("tipoDieta", tipoDieta);
            List<Long> dietasIds = query.getResultList();

            // Obtener el cliente
            Clientes cliente = session.get(Clientes.class, idCliente);

            // Añadir cada dieta al cliente en la tabla dietaspred_clientes.
            for (Long idDieta : dietasIds) {
                Dieta_Pre_Anadir dietaCliente = new Dieta_Pre_Anadir();
                dietaCliente.setClientes(cliente);
                Dieta_Predeterminada dietaPredeterminada = session.get(Dieta_Predeterminada.class, idDieta.longValue());
                dietaCliente.setDietaPredeterminada(dietaPredeterminada);
                session.save(dietaCliente);
            }

            transaction.commit();
        }
    }


 */
public void addDietasToCliente(Long idCliente, String tipoDieta) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        Transaction transaction = session.beginTransaction();

        // Consulta para obtener los id_dieta_predeterminada según el tipo de dieta
        Query<Long> query = session.createQuery(
                "SELECT id FROM Dieta_Predeterminada WHERE tipo = :tipoDieta", Long.class);
        query.setParameter("tipoDieta", tipoDieta);
        List<Long> dietasIds = query.getResultList();

        // Obtener el cliente
        Clientes cliente = session.get(Clientes.class, idCliente);

        // Añadir cada dieta al cliente en la tabla dietaspred_clientes.
        for (Long idDieta : dietasIds) {
            // Crear la relación entre el cliente y la dieta predeterminada
            Dieta_Pre_Anadir dietaCliente = new Dieta_Pre_Anadir();
            dietaCliente.setClientes(cliente);

            // Obtener la dieta predeterminada por su ID
            Dieta_Predeterminada dietaPredeterminada = session.get(Dieta_Predeterminada.class, idDieta);
            dietaCliente.setDietaPredeterminada(dietaPredeterminada);

            // Guardar la relación en la base de datos
            session.save(dietaCliente);
        }

        transaction.commit();
    }
}


    public List<Dieta_Pre_Anadir> getByCliente(Clientes cliente) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Dieta_Pre_Anadir> query = session.createQuery(
                    "FROM Dieta_Pre_Anadir WHERE clientes = :cliente", Dieta_Pre_Anadir.class);
            query.setParameter("cliente", cliente);
            return query.getResultList();
        }
    }
}
