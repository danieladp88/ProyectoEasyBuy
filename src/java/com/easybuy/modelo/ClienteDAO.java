package com.easybuy.modelo;

import com.easybuy.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


//conecta a los clientes a la base datos(consultas)
public class ClienteDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    //valida si existe el usuario
    public Cliente Validar(String email, String pass) {
        String sql="select * from cliente where Email=? and Password=?";
        Cliente c=new Cliente();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                c.setId(rs.getInt("idCliente"));
                c.setDni(rs.getString("DnI"));
                c.setNombres(rs.getString("Nombres"));
                c.setDireccion(rs.getString("Direccion"));
                c.setEmail(rs.getString("EmaIl"));
                c.setPass(rs.getString("Password"));
            }
        } catch (Exception e) {
        }
        return c;        
    }
    
    //Agrega clientes a la base de datos
    public int AgregarCliente(Cliente c) {
        String sql="INSERT INTO cliente (Dni, Nombres, Direccion, Email, Password)values(?,?,?,?,?)";        
        try {
            con = cn.getConnection();//abre la conexion
            ps = con.prepareStatement(sql);//preparar la consulta para la base de datos(conexion)
            ps.setString(1,c.getDni());
            ps.setString(2, c.getNombres());
            ps.setString(3, c.getDireccion());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getPass());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return 1;        
    }
}
