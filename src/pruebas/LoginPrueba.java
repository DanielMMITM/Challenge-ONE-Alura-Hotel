package pruebas;

import controller.HuespedController;
import controller.PersonalController;
import modelo.Huesped;
import modelo.Personal;

public class LoginPrueba {
    public static void main(String[] args) {
        Personal edgar = new Personal("dan", "123");

        PersonalController personalController = new PersonalController();

        var response = personalController.login(edgar.getUsuario(), "123");

        System.out.println("Respuesta: " + response);

    }
}
