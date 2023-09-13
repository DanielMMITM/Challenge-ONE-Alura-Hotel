package controller;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;

public class ReservaController {
    private ReservaDAO reservaDAO;

    public ReservaController(){
        this.reservaDAO = reservaDAO = new ReservaDAO(new ConnectionFactory().recuperaConexion());
    }

    public int reservar(Reserva reservacion){
        return reservaDAO.reservar(reservacion);
    }
}
