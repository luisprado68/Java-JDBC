
package test;

import datos.Conexion;
import datos.PersonaDAO;
import domain.Persona;
import java.sql.*;
import java.util.List;


public class TestManejoPersonas {
    public static void main(String[] args) {
        
        Connection con = null;
        try {
            
            con =  Conexion.getConnection();
            
            if(con.getAutoCommit()){
                con.setAutoCommit(false);//evitamos el autocomit de manera automatica sino manual
            }
            PersonaDAO personaDAO = new PersonaDAO(con);
            Persona persona = new Persona(5,"Sol","Prado","kary@gmail.com","1524154885");
             personaDAO.actualizar(persona);
             
            Persona p = new Persona();
            p.setNombre("Richard");
            p.setApellido("Ramoa");
            p.setEmail("richarlb@gmail.com");
            p.setTelefono("1547878985");
            
            personaDAO.insertar(p);
           
            
            con.commit();
            //imprimir(personaDAO.seleccionar());
            
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                con.rollback();
            } catch (SQLException ex1) {
               ex.printStackTrace(System.out);
            }
        }
        
       
    }
    public static void imprimir(List<Persona> personas){
        personas.forEach(persona -> {
            System.out.println("persona = " + persona);
        });
    }
}
