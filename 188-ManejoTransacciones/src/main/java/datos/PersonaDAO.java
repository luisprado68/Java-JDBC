
package datos;
//dao data acces object

import domain.Persona;
import java.sql.*;
import java.util.*;



public class PersonaDAO {
    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email, telefono FROM personas";
    private static final String SQL_INSERT = "INSERT INTO personas (nombre,apellido,email,telefono) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE personas SET nombre = ?, apellido = ? , email = ?, telefono = ?WHERE id_persona = ?;";
    private static final String SQL_DELETE = "DELETE FROM personas WHERE id_persona = ?";
    private Connection conexionTrasnsaccional;
    
    public PersonaDAO(){
        
    }
    public PersonaDAO(Connection conexionTrasnsaccional){
        this.conexionTrasnsaccional = conexionTrasnsaccional;
    }
    
    public List<Persona> seleccionar() throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();
        
        try {
            con = this.conexionTrasnsaccional != null ? this.conexionTrasnsaccional : Conexion.getConnection() ;
            stmt = con.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int idPersona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                persona = new Persona(idPersona,nombre,apellido,email,telefono);
                personas.add(persona);
            }
           
        } 
        finally{
            
                Conexion.close(rs);
                Conexion.close(stmt);
                if(this.conexionTrasnsaccional == null){
                    Conexion.close(con);
                }

        }
        return personas;
    }
    
    public int insertar(Persona persona) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            con = this.conexionTrasnsaccional != null ? this.conexionTrasnsaccional : Conexion.getConnection() ;
            stmt = con.prepareStatement(SQL_INSERT);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            registros = stmt.executeUpdate();
            System.out.println("registros  actualizados= " + registros);
        }finally{
             
            Conexion.close(stmt);
            if(this.conexionTrasnsaccional == null){
                Conexion.close(con);
            }
        
        }
        return registros;
    }
    public int  actualizar(Persona persona) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            con = this.conexionTrasnsaccional != null ? this.conexionTrasnsaccional : Conexion.getConnection() ;
            stmt = con.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getIdPersona());
            registros = stmt.executeUpdate();
            System.out.println("registros  actualizados= " + registros);
        } 
        finally{
             
                
            Conexion.close(stmt);
             if(this.conexionTrasnsaccional == null){
                Conexion.close(con);
                
            } 
        }
        return registros;
    }
    
    public int eliminar(Persona persona) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            con = this.conexionTrasnsaccional != null ? this.conexionTrasnsaccional : Conexion.getConnection() ;
            stmt = con.prepareStatement(SQL_DELETE);
            
            stmt.setInt(1, persona.getIdPersona());
            registros = stmt.executeUpdate();
            System.out.println("registros  actualizados= " + registros);
        } 
        finally{
             
                
            Conexion.close(stmt);
            if(this.conexionTrasnsaccional == null){
                Conexion.close(con);
            }
            
        }
        return registros;
    }
}
