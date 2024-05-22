package com.example.maxfitvistaempleados.dieta;

import com.example.maxfitvistaempleados.domain.DAO;
import com.example.maxfitvistaempleados.domain.HibernateUtil;
import com.example.maxfitvistaempleados.empleados.Empleado;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class AlimentosDAO implements DAO<Alimentos> {
    @Override
    public ArrayList<Alimentos> getAll() {
        var salida = new ArrayList<Alimentos>(0);

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Alimentos> q = s.createQuery("from Alimentos", Alimentos.class);
            salida = (ArrayList<Alimentos>) q.getResultList();
        }
        return salida;
    }

    @Override
    public Alimentos get(Long id) {
        var salida = new Alimentos();

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            salida = s.get(Alimentos.class, id);
        }

        return salida;
    }

    @Override
    public Alimentos save(Alimentos data) {
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
        public void update (Alimentos data){
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
        public void delete (Alimentos data){
            HibernateUtil.getSessionFactory().inTransaction(session -> {
                Alimentos alimentos = session.get(Alimentos.class, data.getId_alimento());
                session.remove(alimentos);
            });
        }

    }

