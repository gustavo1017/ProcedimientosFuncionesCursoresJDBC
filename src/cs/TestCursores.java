/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs;
import datos.Conexion;

import java.sql.*;
import oracle.jdbc.*;
/**
 *
 * @author Gustavo
 */
public class TestCursores {
  public static void main(String []args){
        OracleCallableStatement oraCallStmt = null;
    OracleResultSet deptResultSet=null;
    try{
        Connection conn = Conexion.getConnection();
        oraCallStmt = (OracleCallableStatement) conn.prepareCall("{ ? = call ref_cursor_package.get_dept_ref_cursor(?) }");
        oraCallStmt.registerOutParameter(1, OracleTypes.CURSOR); //parametro 1
        oraCallStmt.setInt(2, 200); //parametro 2
        oraCallStmt.execute();
        
        //Recuperamos el resulset y lo convertimos a un tipo oracle 
        deptResultSet = (OracleResultSet)oraCallStmt.getCursor(1);
        while(deptResultSet.next()){
            System.out.println("Id_departamento: "+
                    deptResultSet.getInt(1));
            System.out.println(", Nombre_departamento: "+
                    deptResultSet.getString(2));
            System.out.println(", Ubicacion_id "+
                    deptResultSet.getString(3));
            System.out.println();       
        }
        oraCallStmt.close();     
    }catch(SQLException ex){
    ex.printStackTrace();
}
    
  }
    
}
