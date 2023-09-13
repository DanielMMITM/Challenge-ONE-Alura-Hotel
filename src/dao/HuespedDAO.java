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
            final PreparedStatement statement = con.prepareStatement("INSERT INTO huesped(nombre, apellido, fecha_de_nacimiento, " +
                    "nacionalidad, telefono, id_reserva) values(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            try(statement) {
                statement.setString(1, persona.getNombre());
                statement.setString(1, persona.getApellido());
                statement.setDate(1, (java.sql.Date) persona.getFecha_de_nacimiento());
                statement.setString(3, persona.getNacionalidad());
                statement.setString(4, persona.getTelefono());
                statement.setInt(4, persona.getId_reserva());

                final ResultSet resultSet = statement.getGeneratedKeys();

                try(resultSet){
                    while(resultSet.next()){
                        persona.setId(resultSet.getInt(1));
                        System.out.println(String.format("Fue insertado el producto: " + persona.getId().toString()));
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
