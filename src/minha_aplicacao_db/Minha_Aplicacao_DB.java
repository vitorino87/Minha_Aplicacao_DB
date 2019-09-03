/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minha_aplicacao_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 *
 * @author tiago.lucas
 */
public class Minha_Aplicacao_DB {

    private static final String URL = "jdbc:mysql://localhost/tiago";

    public static void main(String[] args) {        
        Scanner sc = new Scanner(System.in);
        String[] a = {"Digite o login: ", "Digite a senha: "};
        String[] b = new String[2];
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
            b[i] = sc.nextLine();
        }
        Connection conn = conectarDB(b);
        if (conn != null) {
            selectAll(conn);
            selectSpecified(conn);
            fecharConexao(conn);            
        }
    }

    private static Connection conectarDB(String[] s) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, s[0], s[1]);
        } catch (Exception e) {
            System.out.println("Acesso negado!");
        }
        return conn;
    }

    private static void selectAll(Connection conn) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from escola1");
            ResultSet rs = ps.executeQuery();
            printSearch(rs);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    private static void printSearch(ResultSet rs) {
        try {
            for (int i = 0; i < 44; i++) {System.out.print("=");}
            System.out.println("");
            System.out.println(String.format("|%-20s|", "RGM")
                    + String.format("|%-20s|", "Nome"));
            while (rs.next()) {
                System.out.println(String.format("|%20s", rs.getString(1))
                        + "|" + String.format("|%20s", rs.getString(2)) + "|");
            }
            for (int i = 0; i < 44; i++) {System.out.print("=");}
            System.out.println("");
        } catch (Exception e) {
            System.out.println("Não foi impossível imprimir");
        }
    }

    private static void selectSpecified(Connection conn) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from escola1"
                    + " where nome=? and rgm=?");
            ps.setString(1, "tiago");
            ps.setString(2, "1234");
            ResultSet rs = ps.executeQuery();
            printSearch(rs);
        } catch (Exception e) {}
    }

    private static void fecharConexao(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {

        }
    }
}
