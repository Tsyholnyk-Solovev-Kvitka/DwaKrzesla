import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class Ksiengowosc extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton ListaPrac = new JButton("Lista pracownikow");
	private static JTextField Password = new JTextField("Password");
	private static JTextField Username = new JTextField("Username");
	private static JTextField Name = new JTextField("Name");
	private static JTextField Plec = new JTextField("Plec");
	private static JTextField Pesel = new JTextField("Pesel");
	private static JTextField Telefon = new JTextField("Telefon");
	private static JTextField Surename = new JTextField("Surename");
	private static JTextField Dzial = new JTextField("Dzial");
	private JButton Rej = new JButton("Rejstracja");
	private JButton UsUz = new JButton("Usunocz uzera po id "); 
	JTextField ids;
	
	public Ksiengowosc() {
	    super("Ksiengowosc");
	    this.setBounds(100,100,400,300);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JPanel panelnorth = new JPanel();
	    panelnorth.setLayout(new FlowLayout());
	    ListaPrac.addActionListener(new ListaListner());
	    UsUz.addActionListener(new UsUzListner());
	    panelnorth.add(ListaPrac);
	    panelnorth.add(UsUz);
	    this.getContentPane().add(panelnorth,BorderLayout.NORTH);
	    Rej.addActionListener(new RejstracjaListner());
	    JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9,1,2,2));
        panel.add(Name);
        panel.add(Surename);
        panel.add(Plec);
        panel.add(Dzial);
        panel.add(Pesel);
        panel.add(Telefon);
        panel.add(Username);
        panel.add(Password);
        panel.add(Rej);
        this.getContentPane().add(panel, BorderLayout.CENTER);
	    
	}
	
	class UsUzListner implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JDialog dialog = new JDialog();
	        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        dialog.setSize(180, 90);
	        ids = new JTextField("Proszhe Id");
	        JButton but = new JButton("ok");
	        but.addActionListener(new UsUzbut());
	        JPanel panel = new JPanel();
	        panel.setLayout(new FlowLayout());
	        panel.add(ids);
	        panel.add(but);
	        dialog.getContentPane().add(panel);
	        dialog.setVisible(true);
			
		}
		class UsUzbut implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					DbHandler db = DbHandler.getInstance();
					db.deleteProduct(Integer.parseInt(ids.getText()));
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		}
	} 
	
	 
	
	 class ListaListner implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				String s = "";
				DbHandler db = DbHandler.getInstance();
				List<User> luser = db.getAllProducts();
				for (User user : luser) {
	                s+=user.toString()+"\n";   
	            }
				JDialog dialog = new JDialog();
		        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		        dialog.setSize(500, 600);
		        JTextArea lab = new JTextArea(s);
		        dialog.getContentPane().add(lab);
		        dialog.setVisible(true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
			
		}	
	}
	
		 class RejstracjaListner implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		 try {
			DbHandler db = DbHandler.getInstance();
			List<User> luser = db.getAllProducts();
			boolean norm = true ;
			for (User user  : luser) {
				if (user.username.equals(Username.getText())) norm=false;
			}
			if (norm) db.addProduct(new User(Name.getText(),Surename.getText(),Pesel.getText(),Telefon.getText(),Plec.getText(),Dzial.getText(),Username.getText(),Password.getText()));
			else {
				JDialog dialog = new JDialog();
		        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		        dialog.setSize(180, 90);
		        JLabel lab = new JLabel("Juz je taki username");
		        dialog.getContentPane().add(lab);
		        dialog.setVisible(true);
		        
			}
			} catch (SQLException e1) {
			e1.printStackTrace();
		}	
			
		}
	}
	 
    public static void main(String[] args) {
    Ksiengowosc ks = new Ksiengowosc();
    ks.setVisible(true);
    }
    
}

