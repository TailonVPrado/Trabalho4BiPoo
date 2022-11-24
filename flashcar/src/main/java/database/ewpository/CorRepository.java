    package database.ewpository;

import br.unipar.flashcar.model.Cor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CorRepository {
    private String INSERT = "INSERT INTO COR (descricao) VALUES(?);";
    private String UPDATE = "UPDATE COR SET descricao = ? WHERE id = ?;";
    private String DELETE = "DELETE COR WHERE id = ?;";
    private String FIND_BY_ID = "SELECT id, descricao FROM COR WHERE id = ?;";
    private String FIND_ALL = "SELECT id, descricao FROM COR;";
    
    public void insert(Cor cor) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new database.DatabaseConnection().getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, cor.getDescricao());
            ps.execute();
        }finally{
            if(ps != null)
                ps.close();
            if(conn != null)
                conn.close();
        }
    }
    
    public ArrayList<Cor> findAll() throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null; 
        ResultSet rs = null;
        ArrayList<Cor> listaResultado = new ArrayList<>();
        try {
            conn = new database.DatabaseConnection().getConnection();
            ps = conn.prepareStatement(FIND_ALL);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Cor cor = new Cor();
                cor.setId(rs.getInt("id"));
                cor.setDescricao(rs.getString("descricao"));
                listaResultado.add(cor);
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
    
    public void delete(Cor cor) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new database.DatabaseConnection().getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, cor.getId());
            ps.execute();
        }finally{
            if(ps != null)
                ps.close();
            if(conn != null)
                conn.close();
        }
    }
    
    public void update(Cor cor) throws SQLException{
        
        Connection conn = null;
        PreparedStatement ps = null;
                
        try {

            conn = new database.DatabaseConnection().getConnection();

            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, cor.getDescricao());
            ps.setInt(2, cor.getId());
            ps.execute();

        } finally {
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
    } 
    
    public Cor findById(int id) throws SQLException {   

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cor resultado = new Cor();
                
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