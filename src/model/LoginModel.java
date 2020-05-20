/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.util.*;
import database.KoneksiDatabase;


/**
 *
 * @author Devandi
 */
public class LoginModel {
    //1. deklarasi variabel yang dibutuhkan
    private KoneksiDatabase database;
    private ResultSet rsLogin, rsOperator, rsUpdate;
    private String query;
    private boolean status;
    private List<LoginModel> listOperator;

    //2. instance ke class koneksi
    public LoginModel() {
        database = new KoneksiDatabase();
        database.getConn();
    }

    //3. deklarasi variabel sesuai dengan tabel
    private int id;
    private String nama;
    private String username;
    private String password;
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    //untuk menampung hasil login user
    private static int idOperator;
    private static String namaOperator;
    private static String userOperator;
    private static String passwordOperator;

    public static int getIdOperator() {
        return idOperator;
    }

    public static void setIdOperator(int idOperator) {
        LoginModel.idOperator = idOperator;
    }

    public static String getNamaOperator() {
        return namaOperator;
    }

    public static void setNamaOperator(String namaOperator) {
        LoginModel.namaOperator = namaOperator;
    }

    public static String getUserOperator() {
        return userOperator;
    }

    public static void setUserOperator(String userOperator) {
        LoginModel.userOperator = userOperator;
    }

    public static String getPasswordOperator() {
        return passwordOperator;
    }

    public static void setPasswordOperator(String passwordOperator) {
        LoginModel.passwordOperator = passwordOperator;
    }

    //4. method untuk login
    public boolean login(){
        query = "SELECT * "
                + "FROM operator "
                + "WHERE username = '"+ username +"' AND password = '"+password+"'";
        status = database.eksekusiQuery(query, true);

        if(status){
            rsLogin = database.getRs();
            try {
                rsLogin.next();
                idOperator = rsLogin.getInt(1);
                namaOperator = rsLogin.getString(2);
                userOperator = rsLogin.getString(3);
                passwordOperator = rsLogin.getString(4);
                if(namaOperator == null){
                    status = false;
                }else{
                    status = true;
                }
            } catch (SQLException se) {
                status = false;
            }
        }
        return status;
    }

    //method untuk seleksi dan pencarian data operator
    public List selectOperator(String id, String nama){
        query = "SELECT id, nama, username, MD5(password)"
                + " FROM operator"
                + " WHERE id LIKE '%"+id+"%' OR nama LIKE '%"+nama+"%'";
        status = database.eksekusiQuery(query, true);
        if(status){
            rsLogin = database.getRs();
            listOperator = new ArrayList<LoginModel>();
            try {
                while(rsLogin.next()){
                    LoginModel model = new LoginModel();
                    model.setId(rsOperator.getInt(1));
                    model.setNama(rsOperator.getString(2));
                    model.setUsername(rsOperator.getString(3));
                    model.setPassword(rsOperator.getString(4));
                    listOperator.add(model);
                }
                rsLogin.close();
                return listOperator;
            } catch (SQLException se) {
                return null;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new LoginModel().selectOperator("", ""));
    }
   
}
