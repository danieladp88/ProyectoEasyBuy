package com.easybuy.config;

import java.sql.Connection;
import java.sql.DriverManager;

//realiza la conexion a la base de datos(Mysql)
public class Conexion {
    Connection con;
    String url="jdbc:mysql://localhost:3306/bdcarritocompras";//conexion a la base
    String user="root";//usuario en xampp
    String pass="";// xampp no utiliza contraseña
    
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");//se conecta a la base de datos a traves de el conector
            con=DriverManager.getConnection(url,user,pass);//conecta a traves de los datos pasados
        } catch (Exception e) {
        }   //en el caso de error emite un mensaje
        return con;
    }
}
