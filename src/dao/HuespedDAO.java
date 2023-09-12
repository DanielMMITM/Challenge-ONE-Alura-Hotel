package dao;

import factory.ConnectionFactory;

import java.sql.Connection;

public class HuespedDAO {

    final private Connection con;

    public HuespedDAO(Connection con){
        this.con = con;
    }
}
