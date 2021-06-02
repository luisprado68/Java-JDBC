
package test;

import datos.PersonaDAO;
import domain.Persona;
import java.util.List;

public class TestManejoPersonas {
    public static void main(String[] args) {
        PersonaDAO personaDAO = new PersonaDAO();
        List<Persona> personas = personaDAO.seleccionar();
        //imprimir(personas);
        System.out.println("");
        Persona p = new Persona("Luis","Prado","luisprado@gmail.com","1550238312");
        
        /*******insertar**********/
        //personaDAO.insertar(p);
        //personas = personaDAO.seleccionar();
        //imprimir(personas);
        
        /*******actualizar**********/
        Persona personaDos = new Persona(6,"Marta","Archield","leonidas@gmail.com","15502321547");
        //personaDAO.actualizar(personaDos);
        // personas = personaDAO.seleccionar();
        //imprimir(personas);
        
        /*******eliminar**********/
        Persona personaTres = new Persona(6);
        personaDAO.eliminar(personaTres);
        personas = personaDAO.seleccionar();
        imprimir(personas);
        
    }
    public static void imprimir(List<Persona> personas){
        personas.forEach(persona -> {
            System.out.println("persona = " + persona);
        });
    }
}
