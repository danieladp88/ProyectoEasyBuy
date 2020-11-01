package com.easybuy.modelo;

import com.easybuy.config.Conexion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;


//conecta los productos de la base de datos
public class ProductoDAO extends Conexion{

    Connection con;//conecta(utilizando la clase conexion
    Conexion cn = new Conexion();
    PreparedStatement ps;//envia la consulta
    ResultSet rs;//trae el listado 
    int r=0;
    
     public Producto buscarprod(int id) {
        Producto p = new Producto ();
        String sql = "select * from producto where Nombres like="+id;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setImagen(rs.getString(3));
//                p.setFoto(rs.getBinaryStream(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));                
                
            }
        } catch (Exception e) {
        }
        return p;
     }
     
     public int actualizarstock(int id, int stock){
         String sql = "update producto set Stock=? where idproducto=?";
         try{
             con = cn.getConnection();
             ps =con.prepareStatement(sql);
             ps.setInt(1,stock);
             ps.setInt(2,id);
             ps.executeUpdate();
         }catch(SQLException e){
             
         }
         return r;
     }
    
    
    public List buscar(String nombre) {
        List list=new ArrayList();
        String sql = "select * from producto where Nombres like '%"+nombre+"%'";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p=new Producto();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setImagen(rs.getString(3));
//                p.setFoto(rs.getBinaryStream(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));                
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }

    //lista por id(para imagenes)
    public Producto listarId(int id) {
        Producto p = new Producto();
        String sql = "select * from producto where IdProducto=" + id;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("idProducto"));
                p.setNombres(rs.getString("Nombres"));
                p.setImagen(rs.getString("Foto"));
                p.setDescripcion(rs.getString("Descripcion"));
                p.setPrecio(rs.getDouble("Precio"));
                p.setStock(rs.getInt("Stock"));                
            }
        } catch (Exception e) {
        }
        return p;
    }

    
    public List listar() {
        List lista = new ArrayList();
        String sql = "select * from producto";
        try {            
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setImagen(rs.getString(3));
//                p.setFoto(rs.getBinaryStream(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));                
                lista.add(p);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public void listarImg(int id, HttpServletResponse response) {
        String sql = "select * from producto where IdProducto=" + id;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        response.setContentType("image/*");
        try {
            outputStream = response.getOutputStream();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                inputStream = rs.getBinaryStream("Foto");
            }
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            int i = 0;
            while ((i = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(i);
            }
        } catch (Exception e) {
        }
    }
    public int AgregarNuevoProducto(Producto p){
        String sql="insert into producto(Nombres,Foto,Descripcion,Precio,Stock)values(?,?,?,?,?)";
        try {
            ps=getConnection().prepareStatement(sql);
            ps.setString(1, p.getNombres());
            ps.setString(2, p.getImagen());
            ps.setString(3, p.getDescripcion());
            ps.setDouble(4, p.getPrecio());
            ps.setInt(5, p.getStock());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
     public void delete(int id) {
        String sql = "delete from producto where IdProducto=" + id;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();//Elimina la categoria que a seleccionado
        } catch (Exception e) {
            System.out.println("Error al eliminar" + e.getMessage());
        }
    }
}
