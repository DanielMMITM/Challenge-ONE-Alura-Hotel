package dao;

import modelo.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Reserva> buscarReservacion(int numReserva){
        List<Reserva> resultado = new ArrayList<>();

        try{
            final PreparedStatement statement = con.prepareStatement("SELECT * FROM reservas where id = ?");

            try (statement){
                statement.setInt(1, numReserva);

                statement.execute();

                ResultSet resultSet = statement.getResultSet();

                while(resultSet.next()){
                    Reserva fila = new Reserva(
                            resultSet.getInt("id"),
                            resultSet.getDate("fecha_entrada"),
                            resultSet.getDate("fecha_salida"),
                            resultSet.getInt("valor"),
                            resultSet.getString("forma_de_pago"));
                    resultado.add(fila);
                }
                return resultado;
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int editarReservacion(Integer id, Date fecha_entrada, Date fecha_salida, Integer valor, String forma_de_pago) {
        try{
            final PreparedStatement statement = con.prepareStatement("UPDATE reservas SET fecha_entrada = ?" + ", fecha_salida = ?" + ", valor = ?"
                    + ", forma_de_pago = ? WHERE id = ?");

            try(statement){
                statement.setDate(1, fecha_entrada);
                statement.setDate(2, fecha_salida);
                statement.setInt(3, valor);
                statement.setString(4, forma_de_pago);
                statement.setInt(5, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int eliminarReservacion(Integer id){
        try{
            final PreparedStatement statement = con.prepareStatement("DELETE FROM reservas WHERE id = ?");

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
