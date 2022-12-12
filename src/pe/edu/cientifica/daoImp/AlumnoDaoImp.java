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
import pe.edu.cientifica.model.Alumno;
/**
 *
 * @author José
 */
public class AlumnoDaoImp implements Operaciones<Alumno>{
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection cx;
    @Override
    public int create(Alumno t) {
        String SQL = "INSERT INTO alumno (nombre, apellido, ciclo,distrito,dni,idcarrera) VALUES(?,?,?,?,?,?)";
        int x = 0;
        try {
            cx = Conexion.getConexion();
            ps  = cx.prepareStatement(SQL);
            ps.setString(1, t.getNombre());
            ps.setString(2, t.getApellido());
            ps.setString(3, t.getCiclo());
            ps.setString(4, t.getDistrito());
            ps.setString(5, t.getDni());
            ps.setInt(6, t.getIdcarrera());
            x = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: "+e);
        }
        return x;
    }

    @Override
    public int update(Alumno t) {
        String SQL = "UPDATE alumno SET nombre=?, apellido=?, ciclo=?, distrito=?, dni=?, idcarrera=? WHERE idalumno=?";
        int x = 0;
        try{
            cx = Conexion.getConexion();
            ps = cx.prepareStatement(SQL);
            ps.setString(1, t.getNombre());
            ps.setString(2, t.getApellido());
            ps.setString(3, t.getCiclo());
            ps.setString(4, t.getDistrito());
            ps.setString(5, t.getDni());
            ps.setInt(6, t.getIdcarrera());
            ps.setInt(7, t.getIdalumno());
            x= ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error: "+e);
        }
        return x;
    }

    @Override
    public int delete(int id) {
        String SQL = "DELETE FROM alumno WHERE idalumno=?";
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
    public Alumno read(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Alumno> readAll() {
        String SQL = "SELECT *FROM alumno";
        List<Alumno> lista = new ArrayList<>();
        try{
            cx = Conexion.getConexion();
            ps = cx.prepareStatement(SQL);
            rs= ps.executeQuery();
            while(rs.next()){
                Alumno c =  new Alumno();
                c.setIdalumno(rs.getInt("idalumno"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setCiclo(rs.getString("ciclo"));
                c.setDistrito(rs.getString("distrito"));
                c.setDni(rs.getString("dni"));                
                lista.add(c);
            }
        }catch(SQLException e){
            System.out.println("Error: "+e);
        }   
        return lista;
    }

    @Override
    public List<Map<String, Object>> readAll2() {
        String SQL = "SELECT c.idcarrera, c.nombre as carrera, p.idalumno, p.nombre as alumno, p.apellido, p.ciclo, p.distrito, p.dni FROM alumno as p " +
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
                map.put("idalumno", rs.getInt("idalumno"));
                map.put("alumno", rs.getString("alumno"));
                map.put("apellido", rs.getString("apellido"));
                map.put("ciclo", rs.getString("ciclo"));
                map.put("distrito", rs.getString("distrito"));
                map.put("dni", rs.getString("dni"));
                lista.add(map);
            }
        } catch (SQLException e) {
            System.out.println("Error: "+e);
        }
        return lista;
    }
    
}
