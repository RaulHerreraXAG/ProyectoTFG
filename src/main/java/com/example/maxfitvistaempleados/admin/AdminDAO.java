package com.example.maxfitvistaempleados.admin;


import com.example.maxfitvistaempleados.domain.DAO;
import com.example.maxfitvistaempleados.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class AdminDAO implements DAO<Admin> {

    @Override
    public ArrayList<Admin> getAll() {
        var salida = new ArrayList<Admin>(0);

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Admin> q = s.createQuery("from Admin", Admin.class);
            salida = (ArrayList<Admin>) q.getResultList();
        }
        return salida;
    }

    @Override
    public Admin get(Long id) {
        var salida = new Admin();

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            salida = s.get(Admin.class, id);
        }

        return salida;
    }

    @Override
    public Admin save(Admin data) {
        return null;
    }

    @Override
    public void update(Admin data) {
        // Implementación para actualizar los datos del Admin en la base de datos.
    }

    @Override
    public void delete(Admin data) {
        // Implementación para eliminar los datos del Admin de la base de datos.
    }

    /**
     * Valida las credenciales de inicio de sesión del Admin.
     *
     * @param email    Email del Admin.
     * @param password Contraseña del Admin.
     * @return El objeto Admin si las credenciales son válidas, de lo contrario, null.
     */
    public Admin validateUser(String email, String password) {
        Admin result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Admin> q = session.createQuery("from Admin where usuario=:u and contrasena=:p", Admin.class);
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
}


