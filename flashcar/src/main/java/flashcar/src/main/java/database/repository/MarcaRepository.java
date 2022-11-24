package database.ewpository;

import br.unipar.flashcar.model.Marca;
import br.unipar.flashcar.model.Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MarcaRepository {
    
    private String INSERT = "INSERT INTO MARCA (descricao) VALUES(?);";
    private String UPDATE = "UPDATE MARCA SET descricao = ? WHERE id = ?;";
    private String DELETE = "DELETE MARCA WHERE id = ?;";
    private String FIND_BY_ID = "SELECT id, descricao FROM MARCA WHERE id = ?;";
    private String FIND_ALL = "SELECT id, descricao FROM MARCA;";
    
    public void insert(Marca marca) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new database.DatabaseConnection().getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, marca.getDescricao());
            ps.execute();
        }finally{
            if(ps != null)
                ps.close();
            if(conn != null)
                conn.close();
        }
    }
    
    public ArrayList<Marca> findAll() throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null; 
        ResultSet rs = null;
        ArrayList<Marca> listaResultado = new ArrayList<>();
        try {
            conn = new database.DatabaseConnection().getConnection();
            ps = conn.prepareStatement(FIND_ALL);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Marca marca = new Marca();
                marca.setId(rs.getInt("id"));
                marca.setDescricao(rs.getString("descricao"));
                listaResultado.add(marca);
            }
        }finally{
            if(rs != null)
                rs.close();
            if(ps != null)
                ps.close();
            if(conn != null)
                conn.close();
        }
        return listaResultado;
    }
    
    public void delete(Marca marca) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new database.DatabaseConnection().getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, marca.getId());
            ps.execute();
        }finally{
            if(ps != null)
                ps.close();
            if(conn != null)
                conn.close();
        }
    }
    
    public void update(Marca marca) throws SQLException{
        
        Connection conn = null;
        PreparedStatement ps = null;
                
        try {

            conn = new database.DatabaseConnection().getConnection();

            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, marca.getDescricao());
            ps.setInt(2, marca.getId());
            ps.execute();
        } finally {
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
    } 
    
    public Marca findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Marca resultado = new Marca();
                
        try {

            conn = new database.DatabaseConnection().getConnection();

            ps = conn.prepareStatement(FIND_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while(rs.next()){   
                resultado.setDescricao(rs.getString("descricao"));
                resultado.setId(rs.getInt("id"));   
            }
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
        
        return resultado;

    }
}
