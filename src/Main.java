import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.*;
import java.util.Scanner;

public class Main {


    static void print(int rok) throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:sqlite:main");
        Statement stmt;
        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM osoba;");
        while (rs.next()) {
            String imie = rs.getString("imie");
            int rocznik = rs.getInt("rocznik");
            int pensja = rs.getInt("pensja");
            if(rocznik>= rok)
            System.out.println(imie + " , " + rocznik + " , " + pensja);
        }

    }

    static void upSalary(int rok) throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:sqlite:main");
        Statement stmt;
        stmt = c.createStatement();
        String upMoney = "UPDATE osoba set pensja = pensja*1.1 where rocznik >= "+rok+";";
        stmt.executeUpdate(upMoney);

    }



    public static void main(String[] args) throws SQLException {
        Scanner read = new Scanner(System.in);
        System.out.print("Podaj rok od ktorego sprawdzac ( w gore ) : ");
        int rok = read.nextInt();
        print(rok);
        upSalary(rok);
        System.out.printf("\n");
        print(rok);

    }


}
