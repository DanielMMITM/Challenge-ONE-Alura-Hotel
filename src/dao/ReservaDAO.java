package dao;

import modelo.Reserva;

import java.sql.*;

public class ReservaDAO {

    final private Connection con;

    public ReservaDAO(Connection con){
        this.con = con;
    }

    public boolean reservar(Reserva reservacion){
        try{
            final PreparedStatement statement = con.prepareStatement("INSERT INTO reservas(fecha_entrada, fecha_salida, valor, forma_de_pago) " +
                    "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            try(statement) {
                statement.setDate(1, reservacion.getFecha_entrada());
                statement.setDate(2, reservacion.getFecha_salida());
                statement.setInt(3, reservacion.getValor());
                statement.setString(4, reservacion.getForma_de_pago());

                statement.execute();

                final ResultSet resultSet = statement.getGeneratedKeys();

                try(resultSet){
                    while(resultSet.next()){
                        reservacion.setId(resultSet.getInt(1));
                        System.out.println(String.format("Fue insertada la reservación %s", reservacion.getId()));
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
