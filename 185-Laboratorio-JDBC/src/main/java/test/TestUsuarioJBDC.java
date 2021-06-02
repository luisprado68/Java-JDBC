
package test;

import datos.UsuarioDAO;
import domain.Usuario;
import java.util.List;


public class TestUsuarioJBDC {
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        Usuario usu = new Usuario(8,"Cecilia","1596");
        //suarioDAO.insertar(usu);
        
        //usuarioDAO.actualizar(usu);
        Usuario usuDos = new Usuario(4);
        
        usuarioDAO.eliminar(usuDos);
        List<Usuario> usuarios = usuarioDAO.seleccionar();
        imprimir(usuarios);
    }
    
    public static void imprimir(List<Usuario> usuarios){
        usuarios.forEach(usuario -> {
            System.out.println("usuario = " + usuario);
        });
    }
}
