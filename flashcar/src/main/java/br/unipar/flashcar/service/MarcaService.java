/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.flashcar.service;

import br.unipar.flashcar.exception.DescricaoInvalidaException;
import br.unipar.flashcar.model.Marca;
import database.ewpository.MarcaRepository;
import java.sql.SQLException;
import java.util.ArrayList;

public class MarcaService {
    
    private void valida(Marca marca) throws DescricaoInvalidaException{
        if (marca.getDescricao() == null)
            throw new DescricaoInvalidaException();
        if (marca.getDescricao().trim().length() == 0)
            throw new DescricaoInvalidaException();
        if (marca.getDescricao().trim().length() >= 60)
            throw new DescricaoInvalidaException();
    }
    
    
    public void insert(Marca marca) throws DescricaoInvalidaException, SQLException{
        valida(marca);
        MarcaRepository marcaRepository = new MarcaRepository();
        marcaRepository.insert(marca);
    }
    
    public ArrayList<Marca> findAll() throws SQLException{
        MarcaRepository marcaRepository = new MarcaRepository();
        return marcaRepository.findAll();
    }
    
    public void delete(Marca marca) throws DescricaoInvalidaException, SQLException{
        MarcaRepository marcaRepository = new MarcaRepository();
        marcaRepository.delete(marca);
    }
    
    public void update(Marca marca) throws SQLException, DescricaoInvalidaException {
        valida(marca);
        MarcaRepository marcaRepository = new MarcaRepository();
        marcaRepository.update(marca);
    }
    
    public Marca findById(int id) throws SQLException {
        MarcaRepository marcaRepository = new MarcaRepository();
        return marcaRepository.findById(id);
    }
    
}
