package dao;

import modelo.Huesped;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HuespedDAO {

    final private Connection con;

    public HuespedDAO(Connection con){
        this.con = con;
    }

    public boolean guardarHuesped(Huesped persona){
        try{
            final PreparedStatement statement = con.prepareStatement("INSERT INTO huespedes(nombre, apellido, fecha_de_nacimiento, " +
                    "nacionalidad, telefono, id_reserva) values(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            try(statement) {
                statement.setString(1, persona.getNombre());
                statement.setString(2, persona.getApellido());
                statement.setDate(3, persona.getFecha_de_nacimiento());
                statement.setString(4, persona.getNacionalidad());
                statement.setString(5, persona.getTelefono());
                statement.setInt(6, persona.getId_reserva());

                statement.execute();

                final ResultSet resultSet = statement.getGeneratedKeys();

                try(resultSet){
                    while(resultSet.next()){
                        persona.setId(resultSet.getInt(1));
                        System.out.println(String.format("Fue insertado el huesped: " + persona.getId().toString()));
                    }
                    return true;
                }
            }

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Huesped> buscarHuesped(String apellido){
        List<Huesped> resultado = new ArrayList<>();

        try{
            final PreparedStatement statement = con.prepareStatement("SELECT * FROM huespedes where apellido = ?");

            try (statement){
                statement.setString(1, apellido);

                statement.execute();

                ResultSet resultSet = statement.getResultSet();

                while(resultSet.next()){
                    Huesped fila = new Huesped(
                            resultSet.getInt("id"),
                            resultSet.getString("nombre"),
                            resultSet.getString("apellido"),
                            resultSet.getDate("fecha_de_nacimiento"),
                            resultSet.getString("nacionalidad"),
                            resultSet.getString("telefono"),
                            resultSet.getInt("id_reserva"));
                    resultado.add(fila);
                }
                return resultado;
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int editarHuesped(Integer id, String nombre, String apellido, Date fecha_de_nacimiento, String nacionalidad, String telefono, Integer id_reserva) {
        try{
            final PreparedStatement statement = con.prepareStatement("UPDATE huespedes SET nombre = ?" + ", apellido = ?" + ", fecha_de_nacimiento = ?"
                    + ", nacionalidad = ?, telefono = ?, id_reserva = ? WHERE id = ?");

            try(statement){
                statement.setString(1, nombre);
                statement.setString(2, apellido);
                statement.setDate(3, fecha_de_nacimiento);
                statement.setString(4, nacionalidad);
                statement.setString(5, telefono);
                statement.setInt(6, id_reserva);
                statement.setInt(7, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int eliminarHuesped(Integer id) {
        try{
            final PreparedStatement statement = con.prepareStatement("DELETE FROM huespedes WHERE id = ?");

            try(statement){
                statement.setInt(1, id);
                statement.execute();

                return statement.getUpdateCount();
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
