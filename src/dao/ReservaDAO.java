package dao;

import modelo.Reserva;

import java.sql.*;

public class ReservaDAO {

    final private Connection con;

    public ReservaDAO(Connection con){
        this.con = con;
    }

    public int reservar(Reserva reservacion){
        try{
            final PreparedStatement statement = con.prepareStatement("INSERT INTO reservas(fecha_de_entrada, fecha_de_salida, valor, forma_de_pago) " +
                    "values(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            try(statement) {
                statement.setDate(1, (java.sql.Date) reservacion.getFecha_entrada());
                statement.setDate(2, (java.sql.Date) reservacion.getFecha_salida());
                statement.setInt(3, reservacion.getValor());
                statement.setString(4, reservacion.getForma_de_pago());

                final ResultSet resultSet = statement.getGeneratedKeys();

                try(resultSet){
                    while(resultSet.next()){
                        reservacion.setId(resultSet.getInt(1));
                        System.out.println(String.format("Fue insertado el producto %s", reservacion.getId()));
                    }
                }

            }
            return reservacion.getId();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
