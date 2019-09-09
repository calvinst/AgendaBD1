import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void connect(){
        java.sql.Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:db.sqlite");
            Statement stmt = conn.createStatement();
            String tGroups = " CREATE TABLE Groups (" +
                    "group_id int NOT NULL," +
                    "description VARCHAR(200) NOT NULL," +
                    "CONSTRAINT PK_GroupID PRIMARY KEY (group_id))";

            String tContacts = " CREATE TABLE Contacts (" +
                    "contact_id int NOT NULL," +
                    "first_name VARCHAR(200) NOT NULL," +
                    "last_name VARCHAR(200) NOT NULL," +
                    "email VARCHAR(200) NOT NULL," +
                    "CONSTRAINT PK_ContactID PRIMARY KEY (contact_id))";

            String tPhones = " CREATE TABLE Phones (" +
                    "phone_id int NOT NULL," +
                    "phone VARCHAR(200) NOT NULL," +
                    "CONSTRAINT PK_PhoneID PRIMARY KEY (phone_id))";

            String tGroupContact = " CREATE TABLE GroupContact (" +
                    "group_id int NOT NULL," +
                    "contact_id int NOT NULL," +
                    "CONSTRAINT FK_GroupContact FOREIGN KEY (group_id) references Groups(group_id)," +
                    "CONSTRAINT FK_ContactGroup FOREIGN KEY (contact_id) references Contact(contact_id))";

            String tContactPhone = " CREATE TABLE ContactPhone (" +
                    "phone_id int NOT NULL," +
                    "contact_id int NOT NULL," +
                    "CONSTRAINT FK_PhoneContact FOREIGN KEY (phone_id) references Phones(phone_id)," +
                    "CONSTRAINT FK_ContactPhone FOREIGN KEY (contact_id) references Contact(contact_id))";

            String str = "INSERT INTO Phones(phone_id, phone)" +
                    "VALUES(1, '(43) 999882200')";

            stmt.execute(str);

        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }


    public static void main(String args[]){
        System.out.println("Hello World");
        connect();
    }
}
