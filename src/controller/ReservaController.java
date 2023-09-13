package controller;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;

import java.sql.Date;
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

    public int editar(Integer id, Date fecha_entrada, Date fecha_salida, Integer valor, String forma_de_pago) {
        return reservaDAO.editarReservacion(id, fecha_entrada, fecha_salida, valor, forma_de_pago);
    }

    public int eliminar(int id){
        return reservaDAO.eliminarReservacion(id);
    }
}
