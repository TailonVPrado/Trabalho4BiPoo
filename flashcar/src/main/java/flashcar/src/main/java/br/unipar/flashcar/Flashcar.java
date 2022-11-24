package br.unipar.flashcar;

import br.unipar.flashcar.exception.DescricaoInvalidaException;
import br.unipar.flashcar.model.Cor;
import br.unipar.flashcar.model.Marca;
import br.unipar.flashcar.model.Modelo;
import br.unipar.flashcar.service.CorService;
import br.unipar.flashcar.service.MarcaService;
import br.unipar.flashcar.service.ModeloService;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class Flashcar {

    public static void main(String[] args) {
        try { 
            /*COR*/            
            Cor azul = new Cor();
            azul.setDescricao("roxo");
            
            CorService corService = new CorService();
            corService.insert(azul);
            
            ArrayList<Cor> listaCores = corService.findAll();
            System.out.println(listaCores.toString());
            
            
            Cor vermelho = new Cor();
            vermelho.setId(2);
            vermelho.setDescricao("vermelho");
            corService.delete(vermelho);
            
            
            listaCores = corService.findAll();
            System.out.println(listaCores.toString());
            
            azul.setId(3);
            azul.setDescricao("roxooooooooooooooooooo");
            corService.update(azul);
            
            
            listaCores = corService.findAll();
            System.out.println(listaCores.toString());
            
            azul = corService.findById(3);
            System.out.println(azul.toString());
            /*******************************************/
           
            /*MARCA*/
            
            Marca ford = new Marca();
            ford.setDescricao("Ford");
            
            MarcaService marcaService = new MarcaService();
            marcaService.insert(ford);
            
            ArrayList<Marca> listaMarcas = marcaService.findAll();
            System.out.println(listaMarcas.toString());
            
            
            Marca fiat = new Marca();
            fiat.setId(2);
            fiat.setDescricao("Fiat");
            marcaService.delete(fiat);
            
            
            listaMarcas = marcaService.findAll();
            System.out.println(listaMarcas.toString());
            
            ford.setId(3);
            ford.setDescricao("FORD");
            marcaService.update(ford);
            
            
            listaMarcas = marcaService.findAll();
            System.out.println(listaMarcas.toString());
            
            ford = marcaService.findById(3);
            System.out.println(ford.toString());
            
            /*******************************************/
            
            /*MODELO*/
            Modelo focus = new Modelo();
            focus.setDescricao("focus");
            focus.setMarca(ford);
            
            ModeloService modeloService = new ModeloService();
            modeloService.insert(focus);
            
            ArrayList<Modelo> listaModelos = modeloService.findAll();
            System.out.println(listaModelos.toString());
            
            
            Modelo ranger = new Modelo();
            ranger.setId(2);
            ranger.setDescricao("ranger");
            modeloService.delete(ranger);
            ranger.setMarca(ford);
            
            listaModelos = modeloService.findAll();
            System.out.println(listaModelos.toString());
            
            focus.setId(3);
            focus.setDescricao("RANGER");
            modeloService.update(ranger);
            
            
            listaModelos = modeloService.findAll();
            System.out.println(listaModelos.toString());
            
            focus = modeloService.findById(3);
            System.out.println(focus.toString());

            System.out.println("Sucess");
        } catch (DescricaoInvalidaException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
