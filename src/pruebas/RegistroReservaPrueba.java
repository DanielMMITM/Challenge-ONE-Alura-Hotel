package pruebas;

import controller.ReservaController;
import modelo.Reserva;

import java.sql.Date;

public class RegistroReservaPrueba {
    public static void main(String[] args) {
        ReservaController reservaController = new ReservaController();

        Date fechaEnt = new Date(2023, 9, 12);
        Date fechaLeave = new Date(2023, 9, 17);
        Reserva reservacion = new Reserva(fechaEnt, fechaLeave, 1790, "Tarjeta de Credito");

        var res = reservaController.reservar(reservacion);

        System.out.println("Res:" + res);
    }
}
