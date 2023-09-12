package controller;

import dao.HuespedDAO;
import dao.PersonalDAO;
import factory.ConnectionFactory;

public class PersonalController {

    private PersonalDAO personalDAO;

    public PersonalController(){
        this.personalDAO = personalDAO = new PersonalDAO(new ConnectionFactory().recuperaConexion());
    }

    public boolean login(String usuario, String password){
        return personalDAO.getCredentials(usuario, password);
    }

}
