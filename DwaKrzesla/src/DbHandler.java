
import org.sqlite.JDBC; 
import java.sql.*;
import java.util.*;
 
public class DbHandler {
 
    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:D:/myfin.db";
 
    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса DbHandler
    private static DbHandler instance = null;
 
    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }
 
    // Объект, в котором будет храниться соединение с БД
    private Connection connection;
    
 
    private DbHandler() throws SQLException {
        // Регистрируем драйвер, с которым будем работать
        // в нашем случае Sqlite
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(CON_STR);
    }
 
    public List<User> getAllProducts() {
 
        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши продукты, полученные из БД
            List<User> luser = new ArrayList<User>();
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT id_us, name, surename, pesel, telefon, plec, dzial, username, password FROM User");
            // Проходимся по нашему resultSet и заносим данные в products
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
            // Возвращаем наш список
            return luser;
 
        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }
 
    // Добавление продукта в БД
    public void addProduct(User product) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
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
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    // Удаление продукта по id
    public void deleteProduct(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM User WHERE id_us = ?")) {
            statement.setObject(1, id);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
public void Createtable() throws SQLException {
    this.connection.createStatement().execute("CREATE TABLE if not exists 'User' ('id_us' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'surename' text, `pesel` text, `telefon` text, `plec` text, `dzial` text, `username` text, password text);");	
    }
}