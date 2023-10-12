package test;

import datos.CategoriaDao;
import datos.impl.CategoriaDaoImpl;
import dominio.Categoria;

public class CategoriaTest {

    public static void main(String[] args) {
        insertar();
    }
    
    //Métodos para categoria
    //Insertar

    private static void insertar() {
        CategoriaDao datos = new CategoriaDaoImpl();
        Categoria obj = new Categoria();
        obj.setNombre("Escolares");
        System.out.println(datos.insertar(obj));
    }

    
}
