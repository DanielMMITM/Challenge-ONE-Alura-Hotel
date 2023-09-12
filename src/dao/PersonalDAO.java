package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalDAO {

    final private Connection con;

    public PersonalDAO(Connection con){
        this.con = con;
    }

    public boolean getCredentials(String usuario, String password){
        try{
            final PreparedStatement statement = con.prepareStatement("SELECT usuario, contrasena FROM personal WHERE usuario = ? AND contrasena = ?");

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
