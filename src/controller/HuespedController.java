package controller;

import dao.HuespedDAO;
import factory.ConnectionFactory;
import modelo.Huesped;

import java.sql.Date;
import java.util.List;

public class HuespedController {
    private HuespedDAO huespedDAO;

    public HuespedController(){
        this.huespedDAO = huespedDAO = new HuespedDAO(new ConnectionFactory().recuperaConexion());
    }

    public boolean guardar(Huesped persona){
        return huespedDAO.guardarHuesped(persona);
    }

    public List<Huesped> buscar(String apellido){
        return huespedDAO.buscarHuesped(apellido);
    }


    public int editar(Integer id, String nombre, String apellido, Date fecha_de_nacimiento, String nacionalidad, String telefono, Integer id_reserva) {
        return huespedDAO.editarHuesped(id, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva);
    }

    public int eliminar(Integer id) {
        return huespedDAO.eliminarHuesped(id);
    }
}
