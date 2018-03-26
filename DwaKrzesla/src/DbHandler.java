
import org.sqlite.JDBC; 
import java.sql.*;
import java.util.*;
 
public class DbHandler {
 
    // ���������, � ������� �������� ����� �����������
    private static final String CON_STR = "jdbc:sqlite:D:/myfin.db";
 
    // ���������� ������ ��������, ����� �� ������� ���������
    // ����������� ������ DbHandler
    private static DbHandler instance = null;
 
    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }
 
    // ������, � ������� ����� ��������� ���������� � ��
    private Connection connection;
    
 
    private DbHandler() throws SQLException {
        // ������������ �������, � ������� ����� ��������
        // � ����� ������ Sqlite
        DriverManager.registerDriver(new JDBC());
        // ��������� ����������� � ���� ������
        this.connection = DriverManager.getConnection(CON_STR);
    }
 
    public List<User> getAllProducts() {
 
        // Statement ������������ ��� ����, ����� ��������� sql-������
        try (Statement statement = this.connection.createStatement()) {
            // � ������ ������ ����� ��������� ���� ��������, ���������� �� ��
            List<User> luser = new ArrayList<User>();
            // � resultSet ����� ��������� ��������� ������ �������,
            // ������� ����������� �������� statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT id_us, name, surename, pesel, telefon, plec, dzial, username, password FROM User");
            // ���������� �� ������ resultSet � ������� ������ � products
            while (resultSet.next()) {
                luser.add(new User(resultSet.getInt("id_us"),
                                            resultSet.getString("name"),
                                            resultSet.getString("surename"),
                                            resultSet.getString("pesel"),
                                            resultSet.getString("telefon"),
                                            resultSet.getString("plec"),
                                            resultSet.getString("dzial"),
                                            resultSet.getString("username"),resultSet.getString("password")));
            }
            // ���������� ��� ������
            return luser;
 
        } catch (SQLException e) {
            e.printStackTrace();
            // ���� ��������� ������ - ���������� ������ ���������
            return Collections.emptyList();
        }
    }
 
    // ���������� �������� � ��
    public void addProduct(User product) {
        // �������� �������������� ���������, ����� �������� SQL-��������
        try (PreparedStatement statement = this.connection.prepareStatement(
                        "INSERT INTO User(`name`, `surename`, `pesel`, `telefon`, `plec`, `dzial`, `username`, password ) " +
                         "VALUES(?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setObject(1, product.name);
            statement.setObject(2, product.surename);
            statement.setObject(3, product.pesel);
            statement.setObject(4, product.telefon);
            statement.setObject(5, product.plec);
            statement.setObject(6, product.dzial);
            statement.setObject(7, product.username);
            statement.setObject(8, product.password);
            // ��������� ������
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    // �������� �������� �� id
    public void deleteProduct(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM User WHERE id_us = ?")) {
            statement.setObject(1, id);
            // ��������� ������
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
public void Createtable() throws SQLException {
    this.connection.createStatement().execute("CREATE TABLE if not exists 'User' ('id_us' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'surename' text, `pesel` text, `telefon` text, `plec` text, `dzial` text, `username` text, password text);");	
    }
}