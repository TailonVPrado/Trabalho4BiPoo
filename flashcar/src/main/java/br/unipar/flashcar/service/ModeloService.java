/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.flashcar.service;

import br.unipar.flashcar.exception.DescricaoInvalidaException;
import br.unipar.flashcar.model.Modelo;
import database.ewpository.ModeloRepository;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeloService {
    
    private void valida(Modelo modelo) throws DescricaoInvalidaException, Exception{
        if (modelo.getDescricao() == null)
            throw new DescricaoInvalidaException();
        if (modelo.getDescricao().trim().length() == 0)
            throw new DescricaoInvalidaException();
        if (modelo.getDescricao().trim().length() >= 60)
            throw new DescricaoInvalidaException();
        if (modelo.getMarca() == null)
            throw new Exception("É necessario que seja informado uma marca para este modelo.");
    }
    
    
    public void insert(Modelo modelo) throws DescricaoInvalidaException, SQLException, Exception{
        valida(modelo);
        ModeloRepository modeloRepository = new ModeloRepository();
        modeloRepository.insert(modelo);
    }
    
    public ArrayList<Modelo> findAll() throws SQLException{
        ModeloRepository modeloRepository = new ModeloRepository();
        return modeloRepository.findAll();
    }
    
    public void delete(Modelo modelo) throws DescricaoInvalidaException, SQLException{
        ModeloRepository modeloRepository = new ModeloRepository();
        modeloRepository.delete(modelo);
    }
    
    public void update(Modelo modelo) throws SQLException, DescricaoInvalidaException, Exception {
        valida(modelo);
        
        ModeloRepository modeloRepository = new ModeloRepository();
        modeloRepository.update(modelo);
    }
    
    public Modelo findById(int id) throws SQLException {
        ModeloRepository modeloRepository = new ModeloRepository();
        return modeloRepository.findById(id);
    }
}
