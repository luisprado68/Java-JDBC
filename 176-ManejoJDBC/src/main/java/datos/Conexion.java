
package datos;

import java.sql.*;



public class Conexion {
    private static final String JBDC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDB_USER = "root";
    private static final String JDB_PASSWORD = "admin";
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JBDC_URL,JDB_USER,JDB_PASSWORD);
    }
    //generamosm sobre carga de metodos para cerrar
    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    public static void close(Statement smtm) throws SQLException{
        smtm.close();
    }
    //prepateStatement tiene mejor perfomance 
    public static void close(PreparedStatement smtm) throws SQLException{
        smtm.close();
    }
    public static void close(Connection conexion) throws SQLException{
        conexion.close();
    }
}
