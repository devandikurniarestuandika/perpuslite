/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.*;
import java.util.List;
import javax.swing.*;
import model.LoginModel;
import view.FormLogin;

/**
 *
 * @author Devandi
 */
public class LoginController {
    //1. Membuat object
    LoginModel model;
    FormLogin view;
    
    //2. Instance
    public LoginController(FormLogin view) {
        this.view = view;
        model = new LoginModel();
    }
    
    //3. input data
    public void inputOperator(int id, String nama, String username, String password){
        model.setId(id);
        model.setNama(nama);
        model.setUsername(username);
        model.setPassword(password);
    }

    //4. method login
    public boolean login(String username, String password){
        model.setUsername(username);
        model.setPassword(password);
        return model.login();
    }

    //5. output
    public static int getIdOperator(){
        return LoginModel.getIdOperator();
    }

    public static String getNamaOperator(){
        return LoginModel.getNamaOperator();
    }

    public static String getUserOperator(){
        return LoginModel.getUserOperator();
    }

    public static String getPasswordOperator(){
        return LoginModel.getPasswordOperator();
    }

    //method seleksi data
    public List selectOperator(String nik, String nama){
        return model.selectOperator(nik, nama);
    }
}
