package controller;

import dao.HuespedDAO;
import factory.ConnectionFactory;
import modelo.Huesped;

import java.util.List;

public class HuespedController {
    private HuespedDAO huespedDAO;

    public HuespedController(){
        this.huespedDAO = huespedDAO = new HuespedDAO(new ConnectionFactory().recuperaConexion());
    }

    public boolean guardar(Huesped persona){
        return huespedDAO.guardarHuesped(persona);
    }

    public List<Huesped> buscar(String apellido){
        return huespedDAO.buscarHuesped(apellido);
    }


}
