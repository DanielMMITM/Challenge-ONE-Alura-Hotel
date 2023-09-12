package pruebas;

import controller.HuespedController;
import modelo.Huesped;

public class LoginPrueba {
    public static void main(String[] args) {
        Huesped edgar = new Huesped("edgar", "martinez","dan", "123");

        HuespedController huespedController = new HuespedController();

        var response = huespedController.login(edgar.getUsuario(), "123");

        System.out.println("Respuesta: " + response);

    }
}
