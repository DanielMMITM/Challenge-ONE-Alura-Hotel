package controller;

import dao.HuespedDAO;
import factory.ConnectionFactory;

public class HuespedController {
    private HuespedDAO huespedDAO;

    public HuespedController(){
        this.huespedDAO = huespedDAO = new HuespedDAO(new ConnectionFactory().recuperaConexion());
    }

    public void guardar(){
        huespedDAO.guardarHuesped();
    }


}
