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

                System.out.println(reservacion.getFecha_entrada());
                System.out.println(reservacion.getFecha_salida());
                System.out.println(reservacion.getValor());
                System.out.println(reservacion.getForma_de_pago());

                statement.execute();

                final ResultSet resultSet = statement.getGeneratedKeys();

                System.out.println("results: " + resultSet);
                try(resultSet){
                    System.out.println("resultset IN");
                    while(resultSet.next()){
                        System.out.println("resultset IN while");
                        reservacion.setId(resultSet.getInt(1));
                        System.out.println(String.format("Fue insertado el producto %s", reservacion.getId()));
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
