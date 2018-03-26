

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
       /* try {
            // Создаем экземпляр по работе с БД
            DbHandler dbHandler = DbHandler.getInstance();
          dbHandler.Createtable();
            // Добавляем запись
           // dbHandler.addProduct(new User(0,"Admin","Admin","nopesel","notelefon","noplec","ksiengowosc","admin","admin" ));
            // Получаем все записи и выводим их на консоль
            List<User> luser = dbHandler.getAllProducts();
            for (User user : luser) {
                System.out.println(user.toString());
            }
           //Удаление записи с id = 8
           // dbHandler.deleteProduct(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    	Wejsce app = new Wejsce();
    	app.setVisible(true);
    }
}