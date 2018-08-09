/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs;
import datos.Conexion;
import java.sql.*;
/**
 *
 * @author Gustavo
 */
public class TestFunciones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int empleadoId=100;
        try{
            Connection conn= Conexion.getConnection();
            CallableStatement cstmt = null;
            double salarioMensual;
            cstmt = conn.prepareCall("{ ? = call get_employee_salary(?) }");
            cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
            cstmt.setInt(2, empleadoId);
            cstmt.execute();
            salarioMensual = cstmt.getDouble(1);
            cstmt.close();
            System.out.println("Empleado con id: "+empleadoId);
            System.out.println("Salario empleado: "+salarioMensual);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
}
