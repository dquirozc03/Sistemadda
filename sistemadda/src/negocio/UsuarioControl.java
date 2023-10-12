package negocio;

import datos.UsuarioDao;
import datos.impl.UsuarioDaoImpl;
import dominio.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class UsuarioControl {

    private final UsuarioDao DATOS;
    private Usuario obj;

    public UsuarioControl() {
        this.DATOS = new UsuarioDaoImpl();
        this.obj = new Usuario();
    }

    private DefaultTableModel modeloTabla;

    public DefaultTableModel listar(String texto) {
        List<Usuario> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto));
        //Establecemos la columna del tableModel
        String[] titulos = {"ID", "NOMBRE", "EMAIL", "CLAVE", "ROL"};
        //Declaramos un vector que será el que agreguemos como registro al DefaultTableModel
        String[] registro = new String[5];
        //agrego los títulos al DefaultTableModel
        this.modeloTabla = new DefaultTableModel(null, titulos);
        //Recorrer toda mi lista y la pasare al DefaultTableModel
        for (Usuario item : lista) {
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            registro[2] = item.getEmail();
            registro[3] = item.getClave();
            registro[4] = item.getRol();
            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }

    public String insertar(Usuario obj) {
        if (DATOS.insertar(obj)) {
            return "OK";
        } else {
            return "Error en el ingreso de datos.";
        }
    }

    public String actualizar(Usuario obj) {
        if (DATOS.actualizar(obj)) {
            return "OK";
        } else {
            return "Error en la actualización de datos.";
        }
    }

    public String eliminar(int id) {
        if (DATOS.eliminar(id)) {
            return "OK";
        } else {
            return "Error en la eliminación de datos.";
        }
    }
}
