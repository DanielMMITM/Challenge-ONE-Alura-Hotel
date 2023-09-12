package dao;

import java.sql.Connection;

public class ReservaDAO {

    final private Connection con;

    public ReservaDAO(Connection con){
        this.con = con;
    }
}
