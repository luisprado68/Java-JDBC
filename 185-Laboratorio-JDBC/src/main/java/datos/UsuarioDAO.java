
package datos;

import domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private static final String SQL_SELECT = "SELECT id_usuario, usuario,password FROM usuarios";
    private static final String SQL_INSERT = "INSERT INTO usuarios (usuario,password) VALUES (?,?)";
    private static final String SQL_UPDATE = "UPDATE usuarios SET usuario = ?, password = ? WHERE id_usuario = ?;";
    private static final String SQL_DELETE = "DELETE FROM usuarios WHERE id_usuario = ?";
    
    public List<Usuario> seleccionar(){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<>();
        
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int idUsuario = rs.getInt("id_usuario");
                String nombre = rs.getString("usuario");
                String password = rs.getString("password");
                usuario = new Usuario(idUsuario,nombre,password);
                usuarios.add(usuario);
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                Conexion.close(con);
            } catch (SQLException ex) {
               ex.printStackTrace(System.out);
            }
            
        }
        return usuarios;
    }
    
    public int insertar(Usuario usuario){
        Connection con = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_INSERT);
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getPassword());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            
        }
        finally{
             try {
                
                Conexion.close(stmt);
                Conexion.close(con);
            } catch (SQLException ex) {
               ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    public int  actualizar(Usuario usuario){
        Connection con = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getPassword());
            stmt.setInt(3, usuario.getIdUsuario());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            
        }
        finally{
             try {
                
                Conexion.close(stmt);
                Conexion.close(con);
            } catch (SQLException ex) {
               ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int eliminar(Usuario usuario){
        Connection con = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_DELETE);
            
            stmt.setInt(1, usuario.getIdUsuario());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            
        }
        finally{
             try {
                
                Conexion.close(stmt);
                Conexion.close(con);
            } catch (SQLException ex) {
               ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
}
