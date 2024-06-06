package com.example.maxfitvistaempleados.dieta;

import com.example.maxfitvistaempleados.Sesion;
import com.example.maxfitvistaempleados.clientes.Clientes;
import com.example.maxfitvistaempleados.domain.DAO;
import com.example.maxfitvistaempleados.domain.HibernateUtil;
import com.example.maxfitvistaempleados.rutina.Rutina;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class DietasDAO implements DAO<Dietas> {

    static RecetaDAO recetaDAO = new RecetaDAO();
    @Override
    public ArrayList<Dietas> getAll() {
        var salida = new ArrayList<Dietas>(0);

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Dietas> q = s.createQuery("from Dietas", Dietas.class);
            salida = (ArrayList<Dietas>) q.getResultList();
        }
        return salida;
    }

    @Override
    public Dietas get(Long id) {
        var salida = new Dietas();

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            salida = s.get(Dietas.class, id);
        }

        return salida;
    }

    @Override
    public Dietas save(Dietas data) {
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
    public void update(Dietas data) {
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
    public void delete(Dietas data) {
        HibernateUtil.getSessionFactory().inTransaction(session -> {
            Dietas dietas = session.get(Dietas.class, data.getId_dieta());
            session.remove(dietas);
        });
    }

    public ArrayList<Dietas> getByCliente(Clientes cliente) {
        ArrayList<Dietas> salida = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Dietas> query = session.createQuery("from Dietas where clientes = :cliente", Dietas.class);
            query.setParameter("cliente", cliente);
            salida.addAll(query.getResultList());
        }

        return salida;
    }
    public static Dietas findById(Integer id) {
        // Código para obtener la dieta desde la base de datos
        // Asegúrate de que también se obtenga la receta asociada y se asigne a la dieta
        Dietas dieta = null;
        try {
            // Supongamos que tienes una consulta SQL que obtiene los datos de la dieta
            // reemplaza 'obtenerDatosDietaPorId' con el método o consulta que uses para obtener la dieta de la base de datos
            dieta = obtenerDatosDietaPorId(id);

            // Supongamos que en la tabla de dietas hay una columna que almacena el ID de la receta asociada
            Integer recetaId = Math.toIntExact(dieta.getReceta().getIdReceta());

            // Luego, supongamos que tienes un DAO (Objeto de Acceso a Datos) para las recetas, llamado recetaDAO
            Recetas receta = recetaDAO.findById(recetaId);

            // Asignamos la receta obtenida a la dieta
            dieta.setReceta(receta);
        } catch (Exception e) {
            // Manejo de excepciones, como conexión a la base de datos fallida, etc.
            e.printStackTrace();
        }
        return dieta;
    }
    // Método para obtener los datos de la dieta desde la base de datos
    private static Dietas obtenerDatosDietaPorId(Integer id) {
        // Aquí iría la lógica para conectarse a la base de datos y obtener los datos de la dieta con el id proporcionado
        // Supongamos que la dieta se obtiene de una tabla llamada 'dietas' y que hay un método en la capa de acceso a datos (DAO) para esto
        // Reemplaza 'dietaDAO' con tu DAO real
        return DietasDAO.findById(id);
    }

    public static Long countClientes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("select count(d) from Dietas d", Long.class);
            return query.uniqueResult();
        }
    }
    public List<Dietas> getByCliente2(Clientes cliente) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Dietas> query = session.createQuery("FROM Dietas WHERE clientes = :cliente", Dietas.class);
            query.setParameter("cliente", cliente);
            return query.getResultList();
        }
    }

    // Obtener dietas predeterminadas por cliente
    public List<Dietas> getPredeterminadasByCliente(Clientes cliente) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Dietas> query = session.createQuery("FROM Dieta_Pre_Anadir WHERE clientes = :cliente", Dietas.class);
            query.setParameter("cliente", cliente);
            return query.getResultList();
        }
    }


    public void deleteByCliente(Clientes cliente) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                // Eliminar dietas predeterminadas
                Query<?> deleteDietasPredQuery = session.createQuery("DELETE FROM Dieta_Pre_Anadir WHERE clientes = :cliente");
                deleteDietasPredQuery.setParameter("cliente", cliente);
                deleteDietasPredQuery.executeUpdate();

                // Eliminar dietas regulares
                Query<?> deleteDietasQuery = session.createQuery("DELETE FROM Dietas WHERE clientes = :cliente");
                deleteDietasQuery.setParameter("cliente", cliente);
                deleteDietasQuery.executeUpdate();

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

}
