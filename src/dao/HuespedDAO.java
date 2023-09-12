package dao;

import factory.ConnectionFactory;

import java.sql.*;

public class HuespedDAO {

    final private Connection con;

    public HuespedDAO(Connection con){
        this.con = con;
    }

    public boolean getCredentials(String usuario, String password){
        try{
            final PreparedStatement statement = con.prepareStatement("SELECT usuario, contrasena FROM huespedes WHERE usuario = ? AND contrasena = ?");

            try(statement) {
                statement.setString(1, usuario);
                statement.setString(2, password);

                ResultSet rs = statement.executeQuery();

                if (rs.next()){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
