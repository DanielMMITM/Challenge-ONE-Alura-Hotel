package controller;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;

import java.util.List;

public class ReservaController {
    private ReservaDAO reservaDAO;

    public ReservaController(){
        this.reservaDAO = reservaDAO = new ReservaDAO(new ConnectionFactory().recuperaConexion());
    }

    public boolean reservar(Reserva reservacion){
        return reservaDAO.reservar(reservacion);
    }

    public List<Reserva> buscar(int numReserva){
        return reservaDAO.buscarReservacion(numReserva);
    }
}
