package database.ewpository;

import br.unipar.flashcar.model.Marca;
import br.unipar.flashcar.model.Modelo;
import br.unipar.flashcar.service.MarcaService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeloRepository {
    
    
    private String INSERT = "INSERT INTO MODELO (descricao, id_marca) VALUES(?, ?);";
    private String UPDATE = "UPDATE MODELO SET descricao = ?, id_marca = ?  WHERE id = ?;";
    private String DELETE = "DELETE MODELO WHERE id = ?;";
    private String FIND_BY_ID = "SELECT id, descricao, id_marca FROM MODELO WHERE id = ?;";
    private String FIND_ALL = "SELECT id, descricao, id_marca  FROM MODELO;";
    
    public void insert(Modelo modelo) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new database.DatabaseConnection().getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, modelo.getDescricao());
            ps.setInt(2, modelo.getMarca().getId());
            ps.execute();
        }finally{
            if(ps != null)
                ps.close();
            if(conn != null)
                conn.close();
        }
    }
    
    public ArrayList<Modelo> findAll() throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null; 
        ResultSet rs = null;
        ArrayList<Modelo> listaResultado = new ArrayList<>();
        try {
            conn = new database.DatabaseConnection().getConnection();
            ps = conn.prepareStatement(FIND_ALL);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Modelo modelo = new Modelo();
                modelo.setId(rs.getInt("id"));
                modelo.setDescricao(rs.getString("descricao"));
                
                MarcaService marcaService = new MarcaService();
                modelo.setMarca(marcaService.findById(modelo.getId()));
                
                listaResultado.add(modelo);
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
    
    public void delete(Modelo modelo) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new database.DatabaseConnection().getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, modelo.getId());
            ps.execute();
        }finally{
            if(ps != null)
                ps.close();
            if(conn != null)
                conn.close();
        }
    }
    
    public void update(Modelo modelo) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
                
        try {

            conn = new database.DatabaseConnection().getConnection();

            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, modelo.getDescricao());
            ps.setInt(2, modelo.getMarca().getId());
            ps.setInt(3, modelo.getId());
            ps.execute();
            
        } finally {
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
    } 
    
    public Modelo findById(int id) throws SQLException {   

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Modelo resultado = new Modelo();
                
        try {
        //private String FIND_BY_ID = "SELECT descricao, id_marca FROM MODELO WHERE id = ?;";
            conn = new database.DatabaseConnection().getConnection();

            ps = conn.prepareStatement(FIND_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while(rs.next()){
                resultado.setDescricao(rs.getString("descricao"));
                resultado.setId(rs.getInt("id"));
                
                MarcaService marcaService = new MarcaService();
                
                resultado.setMarca(marcaService.findById(resultado.getId()));
                
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
