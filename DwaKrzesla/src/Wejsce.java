import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;

public class Wejsce extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton button = new JButton("Ok");
	private JTextField TPassword = new JTextField("");
	private JTextField TUsername = new JTextField("");
	private JLabel LUsername = new JLabel("Username:");
	private JLabel LPassword = new JLabel("Password:");
	
	public Wejsce() {
	    super("Dwa Stula");
	    this.setBounds(100,100,400,300);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    Container container = this.getContentPane();
	    container.setLayout(new GridLayout(3,2,2,2));
	    container.add(LUsername);
	    container.add(TUsername);
	    container.add(LPassword);
	    container.add(TPassword);
	    container.add(button);
	    button.addActionListener(new ButtonEventListener());
	    container.add(button);
	   
	}
	
	class ButtonEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				DbHandler db = DbHandler.getInstance();
				List<User> luser = db.getAllProducts();
				for (User user :luser) {
					//LUsername.setText(TUsername.getText()+" "+user.username+" "+TPassword.getText()+" "+user.password);
					if (TUsername.getText().equals(user.username)) if (TPassword.getText().equals(user.password)) {
						switch(user.dzial) {
						
						case"ksiengowosc": 
						Ksiengowosc Ks = new Ksiengowosc();
						Ks.setVisible(true);
						break;
						case"sprzedaz": 
							Sprzedaz sp = new Sprzedaz();
							sp.setVisible(true);
							break;
						}
						
						
						dispose();
				        break;
				        
					}
						
					
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			
		}
	}

}
