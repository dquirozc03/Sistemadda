package datos.impl;

import database.Conexion;
import datos.UsuarioDao;
import dominio.Usuario;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class UsuarioDaoImpl implements UsuarioDao<Usuario> {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public UsuarioDaoImpl() {
        CON = Conexion.getInstancia();
    }

    @Override
    public List<Usuario> listar(String texto) {
        List<Usuario> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("Select * from usuarios where nombre like ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return registros;
    }

    @Override
    public boolean insertar(Usuario obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("INSERT INTO usuarios (nombre, email, clave, rol) VALUES (?, ?, ?, ?)");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getEmail());
            ps.setString(3, obj.getClave());
            ps.setString(4, obj.getRol());
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean actualizar(Usuario obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE usuarios SET nombre=? WHERE id=?");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getEmail());
            ps.setString(3, obj.getClave());
            ps.setString(4, obj.getRol());
            ps.setInt(5, obj.getId());
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean eliminar(int id) {
         resp = false;
        try {
            ps = CON.conectar().prepareStatement("DELETE FROM usuarios WHERE id=?");
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }
}
