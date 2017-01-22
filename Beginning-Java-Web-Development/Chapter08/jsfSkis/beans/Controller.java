package beans;

import java.sql.DriverManager;    
import java.sql.Connection;      
import java.sql.PreparedStatement;
import java.sql.ResultSet;       
import java.sql.SQLException;   
import java.util.Properties;    
import java.util.List;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/* Scope options -- determines how long a @ManagedBean instance lives

   ResponseScoped:     the default -- lives through a single request/response cycle

   ViewScoped:         lives through interactions with the same view via postbacks without, 
                       however, any explicit redirects

   FlowScoped:         lives through transitions from one page to another in specified 'page flow', 
                       which has a specified start and end page

   SessionScoped:      lives throughout the client session

   ApplicationScoped:  lives from throughout the web app's lifetime (from load to unload),
                       implements the 'singleton' pattern
 */

@ManagedBean(eager = true) // encourage the container to load the instance right away
@ViewScoped                // scope remains active so long as we're dealing with the same view
public class Controller {
    private String[] equipmentTypes = {  
	"Alpine Boot",
	"Cross Country Boot",
	"Telemark Boot",
	"Alpine Skis",
	"Alpine Touring Skis",
	"Cross Country Skis",
	"Telemark Skis",
    };
    private String selectedType;
    private List<SelectItem> menuChoices;      

    public Controller() {
	init();
    }

    private void init() {
	menuChoices = new ArrayList<SelectItem>();
	for (String type : equipmentTypes) {
	    SelectItem menuChoice = new SelectItem(type);
	    menuChoices.add(menuChoice);
	}
	selectedType = equipmentTypes[0];
    }

    public void setSelectedType(String selectedType) {
	this.selectedType = selectedType;
    }
    public String getSelectedType() {
	return selectedType;
    }

    public List<SelectItem> getMenuChoices() {  
	return menuChoices;
    }

    public List<SkiEquipItem> getItems() {
	return getSkiEquipFromDB();
    }

    private List<SkiEquipItem> getSkiEquipFromDB() {
	List<SkiEquipItem> items = new ArrayList<SkiEquipItem>();
	Connection conn = getConnection();
	try {
	    String sql = "select * from skisetc where category = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, selectedType);
	    ResultSet rs = stmt.executeQuery();

	    while (rs.next()) {
		SkiEquipItem item = new SkiEquipItem();
		item.setId(rs.getInt("id"));
		item.setProduct(rs.getString("product"));
		item.setCategory(rs.getString("category"));
		item.setPrice(rs.getBigDecimal("price"));
		items.add(item);
	    }
	    rs.close();
	    stmt.close();
	    conn.close();
	}
	catch(SQLException e) { }
	return items;
    }

    private Connection getConnection() {
	String uri = "jdbc:postgresql://localhost/skistuff"; 
	Properties props = setLoginForDB("fred", "secret");
	Connection conn = null;
	try {
	    Class.forName("org.postgresql.Driver"); //*** load the PostreSQL driver
	    conn = DriverManager.getConnection(uri, props);
	}
	catch(ClassNotFoundException e) { }
	catch(SQLException e) { }
	return conn;
    }

    private Properties setLoginForDB(final String uname, final String passwd) {
	Properties props = new Properties();
	props.setProperty("user", uname);
	props.setProperty("password", passwd);
	return props;
    }
}
