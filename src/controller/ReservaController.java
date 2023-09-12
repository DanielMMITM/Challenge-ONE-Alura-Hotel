package controller;

import dao.ReservaDAO;
import factory.ConnectionFactory;

public class ReservaController {
    private ReservaDAO reservaDAO;

    public ReservaController(){
        this.reservaDAO = reservaDAO = new ReservaDAO(new ConnectionFactory().recuperaConexion());
    }
}
