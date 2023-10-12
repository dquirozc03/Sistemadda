package test;

import datos.UsuarioDao;
import datos.impl.UsuarioDaoImpl;
import dominio.Usuario;


public class UsuarioTest {

    public static void main(String[] args) {
        insertar();
    }
    
    //Métodos para categoria
    //Insertar

    private static void insertar() {
        UsuarioDao datos = new UsuarioDaoImpl();
        Usuario obj = new Usuario();
        obj.setNombre("Daniel");
        obj.setEmail("dquirozc03@gmail.com");
        obj.setClave("123456");
        obj.setRol("Administrador");
        System.out.println(datos.insertar(obj));
    }

    
}
