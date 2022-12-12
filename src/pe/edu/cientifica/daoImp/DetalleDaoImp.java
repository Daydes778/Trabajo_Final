/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.cientifica.daoImp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pe.edu.cientifica.config.Conexion;
import pe.edu.cientifica.dao.Operaciones;
import pe.edu.cientifica.model.Detalle;
/**
 *
 * @author José
 */
public class DetalleDaoImp implements Operaciones<Detalle> {
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection cx;
    @Override
    public int create(Detalle t) {
        String SQL = "INSERT INTO detalle (precio, fechainicio, fechafinal, modalidad,idcarrera) VALUES(?,?,?,?,?)";
        int x = 0;
        try {
            cx = Conexion.getConexion();
            ps  = cx.prepareStatement(SQL);
            ps.setString(1, t.getPrecio());
            ps.setString(2, t.getFechainicio());
            ps.setString(3, t.getFechafinal());
            ps.setString(4, t.getModalidad());
            ps.setInt(5, t.getIdcarrera());
            x = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: "+e);
        }
        return x;
    }

    @Override
    public int update(Detalle t) {
        String SQL = "UPDATE detalle SET precio=?, fechainicio=?, fechafinal=?, modalidad=?,idcarrera=? WHERE iddetalle=?";
        int x = 0;
        try{
            cx = Conexion.getConexion();
            ps = cx.prepareStatement(SQL);
            ps.setString(1, t.getPrecio());
            ps.setString(2, t.getFechainicio());
            ps.setString(3, t.getFechafinal());
            ps.setString(4, t.getModalidad()); 
            ps.setInt(5, t.getIdcarrera());
            ps.setInt(6, t.getIddetalle());
            x= ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error: "+e);
        }
        return x;
    }

    @Override
    public int delete(int id) {
        String SQL = "DELETE FROM detalle WHERE iddetalle=?";
        int x=0;
        try{
            cx = Conexion.getConexion();
            ps = cx.prepareStatement(SQL);
            ps.setInt(1,id);
            x=ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error: "+e);
        }
        return x;
    }

    @Override
    public Detalle read(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Detalle> readAll() {
        String SQL = "SELECT *FROM detalle";
        List<Detalle> lista = new ArrayList<>();
        try{
            cx = Conexion.getConexion();
            ps = cx.prepareStatement(SQL);
            rs= ps.executeQuery();
            while(rs.next()){
                Detalle c =  new Detalle();
                c.setIddetalle(rs.getInt("iddetalle"));
                c.setPrecio(rs.getString("precio"));
                c.setFechainicio(rs.getString("fechainicio"));
                c.setFechafinal(rs.getString("fechafinal"));
                c.setModalidad(rs.getString("modalidad"));

                lista.add(c);
            }
        }catch(SQLException e){
            System.out.println("Error: "+e);
        }   
        return lista;
    }

    @Override
    public List<Map<String, Object>> readAll2() {
        String SQL = "SELECT c.idcarrera, c.nombre as carrera, p.iddetalle,p.precio, p.fechainicio, p.fechafinal, p.modalidad FROM detalle as p " +
                     "inner join carrera as c on p.idcarrera = c.idcarrera";
        List<Map<String, Object>> lista = new ArrayList<>();
        try {
            cx = Conexion.getConexion();
            ps = cx.prepareStatement(SQL);
            rs = ps.executeQuery();
            while(rs.next()){
                Map<String, Object> map = new HashMap();
                map.put("idcarrera", rs.getInt("idcarrera"));
                map.put("carrera", rs.getString("carrera"));
                map.put("iddetalle", rs.getInt("iddetalle"));
                map.put("precio", rs.getString("precio"));
                map.put("fechainicio", rs.getString("fechainicio"));
                map.put("fechafinal", rs.getString("fechafinal"));
                map.put("modalidad", rs.getString("modalidad"));
                lista.add(map);
            }
        } catch (SQLException e) {
            System.out.println("Error: "+e);
        }
        return lista;
    }
    
}
