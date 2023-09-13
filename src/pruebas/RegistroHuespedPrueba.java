package pruebas;


import controller.HuespedController;
import modelo.Huesped;

import java.sql.Date;

public class RegistroHuespedPrueba {
    public static void main(String[] args) {
        Date fechaN = new Date(1997, 3, 10);
        Huesped huesped = new Huesped("Marta", "Ocampo", fechaN, "mexicano-mexicana", "44332231", 1);

        HuespedController huespedController = new HuespedController();

        huespedController.guardar(huesped);


    }
}
