

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
       /* try {
            // ������� ��������� �� ������ � ��
            DbHandler dbHandler = DbHandler.getInstance();
          dbHandler.Createtable();
            // ��������� ������
           // dbHandler.addProduct(new User(0,"Admin","Admin","nopesel","notelefon","noplec","ksiengowosc","admin","admin" ));
            // �������� ��� ������ � ������� �� �� �������
            List<User> luser = dbHandler.getAllProducts();
            for (User user : luser) {
                System.out.println(user.toString());
            }
           //�������� ������ � id = 8
           // dbHandler.deleteProduct(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    	Wejsce app = new Wejsce();
    	app.setVisible(true);
    }
}