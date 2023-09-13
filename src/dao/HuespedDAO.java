package dao;

import modelo.Huesped;

import java.sql.*;

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
}
