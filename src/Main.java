import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.*;
import java.util.Scanner;
public class Main
{
    public static void main( String args[] )
    {
        Connection c = null;
        Statement stmt = null;
        Scanner read = new Scanner(System.in);
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:main");
            c.setAutoCommit(true);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM osoba;" );
            System.out.print("Podaj minimalny rocznik : ");
            int rok = read.nextInt();
            while ( rs.next() ) {
                String  imie = rs.getString("imie");
                int rocznik = rs.getInt("rocznik");
                int pensja  = rs.getInt("pensja");
                String upPensja = "UPDATE osoba set pensja = pensja*1.1 where rocznik >= "+rok+";";
                stmt.executeUpdate(upPensja);
            }

            System.out.println("\n");

            rs = stmt.executeQuery( "SELECT * FROM osoba;" );
            while ( rs.next() ) {
                String imie = rs.getString("imie");
                int rocznik = rs.getInt("rocznik");
                int pensja = rs.getInt("pensja");
                if(rocznik>=rok)
                System.out.println("Imie : " + imie + " , rocznik : " + rocznik + " , pensja : " + pensja);
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}
