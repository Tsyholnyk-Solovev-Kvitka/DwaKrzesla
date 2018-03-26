public class User {
	    // Поля класса
	    public int id_us;
	    public String name;
	    public String surename;
	    public String pesel;
	    public String telefon;
	    public String plec;
	    public String dzial;
	    public String username;
	    public String password;
	 
	    // Конструктор
	    public User( String xname, String xsurename ,String xpesel, String xtelefon,String xplec,String xdzial,String xusername, String xpassword) {
	        this.name = xname;
	        this.pesel = xpesel;
	        this.surename = xsurename;
	        this.telefon=xtelefon;
	        this.plec=xplec;
	        this.dzial=xdzial;
	        this.password= xpassword;
	        this.username = xusername;
	    }
	    public User(int id , String xname, String xsurename ,String xpesel, String xtelefon,String xplec,String xdzial,String xusername, String xpassword) {
	    	this.id_us= id;
	    	this.name = xname;
	        this.pesel = xpesel;
	        this.surename = xsurename;
	        this.telefon=xtelefon;
	        this.plec=xplec;
	        this.dzial=xdzial;
	        this.password= xpassword;
	        this.username = xusername;
	    }
	 
	    // Выводим информацию по продукту
	    @Override
	    public String toString() {
	        return String.format("ID: %s | Name: %s | Surename: %s | pesel: %s | telefon: %s | plec: %s dzial: %s",
	                this.id_us, this.name, this.surename, this.pesel, this.telefon, this.plec, this.dzial, this.username );
	    }
	}

