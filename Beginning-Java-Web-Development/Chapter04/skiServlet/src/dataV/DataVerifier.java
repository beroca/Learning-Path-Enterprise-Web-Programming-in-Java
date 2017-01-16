package dataV;

import java.math.BigDecimal;                   // for prices
import java.sql.DriverManager;                 // handles communication with the DB
import java.sql.Connection;                    // a connection to the DB
import java.sql.Statement;
import java.sql.PreparedStatement;             // an SQL statement for the DB to execute
import java.sql.ResultSet;                     // a table of rows generated from an SQL query
import java.sql.SQLException;                  // what's thrown when the JDBC operations fail
import java.util.Properties;                   // key/value pairs
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;

public class DataVerifier extends HttpServlet {
    private static final int MinLength = 8;

    public DataVerifier() { }

    public void doPost(HttpServletRequest req, HttpServletResponse res) {
	Connection conn = getConnection();
	verifyUserInputsAndUpdateDB(conn, req, res);
	try {
	    conn.close();
	}
	catch(SQLException e) { }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
	/** The 'nuclear option':
	    throw new HTTPException(HttpServletResponse.SC_METHOD_NOT_ALLOWED); // HTTP 405
	*/
	sendResponse(req, res, "Only POST requests are accepted.", true);
    }

    private void verifyUserInputsAndUpdateDB(Connection conn, 
					     HttpServletRequest req, 
					     HttpServletResponse res) {
	String productN = req.getParameter("product");
	String categoryN = req.getParameter("category");
	String price = req.getParameter("price");

	// data verification checks
	if (productN == null || categoryN == null || price == null) { // bad inputs?
	    sendResponse(req, res, "One or more bad input items.", true);
	    return;
	}

	if (productN.length() < MinLength) {
	    sendResponse(req, res, "A product's name must be at least " + MinLength + " characters.", true);
	    return;
	}

	if (categoryN.length() < MinLength) {
	    sendResponse(req, res, "A category's name must be at least " + MinLength + " characters.", true);
	    return;
	}

	BigDecimal priceBD = convertPrice(price);
	if (priceBD == null) {
	    sendResponse(req, res, "Price entered contains invalid characters: +/-, ., and decimal digits only.", true);
	    return;
	}

	// Capitalize product name and category name parts.
	productN = capitalizeParts(productN);
	categoryN = capitalizeParts(categoryN);

	if (req.getParameter("id") == null) { // create rather than edit
	    if (productNameInUse(conn, productN)) 
		sendResponse(req, res, "The name '" + productN + "' is already in use." , true);
	    else if (handleCreate(conn, 
				  res, 
				  productN,
				  categoryN,
				  priceBD))
		sendResponse(req, res, productN + " added to the DB.", false);
	    else
		sendResponse(req, res, "Problem saving " + productN + " to the DB.", true);
	}
	else { // edit rather than create
	    if (handleEdit(conn, 
			   res,
			   req.getParameter("id"),
			   productN,
			   categoryN,
			   priceBD))
		sendResponse(req, res, productN + " updated successfully.", false);
	    else
		sendResponse(req, res, "Problem updating " + productN, true);
	}
    }

    private boolean handleCreate(Connection conn, 
				 HttpServletResponse res, 
				 String product, 
				 String category,
				 BigDecimal price) {
	boolean flag = false;
	String sql = "INSERT INTO skisEtc(product, category, price) VALUES (?, ?, ?)";

	try {
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, product);
	    pstmt.setString(2, category);
	    pstmt.setBigDecimal(3, price);
	    pstmt.executeUpdate();
	    flag = true;
	}
	catch (SQLException e) { }
	return flag;
    }

    private boolean handleEdit(Connection conn, 
			       HttpServletResponse res, 
			       String idString,
			       String product, 
			       String category,
			       BigDecimal price) {
       	boolean flag = false;
	String sql = "UPDATE skisEtc SET product = ?, category = ?, price = ? where id = ?";
	
	try {
	    int id = Integer.parseInt(idString.trim());
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, product);
	    pstmt.setString(2, category);
	    pstmt.setBigDecimal(3, price);
	    pstmt.setInt(4, id);
	    pstmt.executeUpdate();
	    flag = true;
	}
	catch (NumberFormatException e) { }
	catch (SQLException e) { }
	return flag;
    }

    private void sendResponse(HttpServletRequest req,
			      HttpServletResponse res, 
			      String msg, 
			      boolean error) {
	try {
	    HttpSession session = req.getSession();
	    session.setAttribute("result", msg);
	    if (error)
		res.sendRedirect("badResult.jsp");
	    else
		res.sendRedirect("goodResult.jsp");
	}
	catch(IOException e) { }
    }

    private BigDecimal convertPrice(String price) {
	BigDecimal result = null;
	try {
	    result = new BigDecimal(price);
	}
	catch (NumberFormatException e) { }
	return result;
    }

    private String capitalizeParts(String str) {
	if (str.length() < 1) return str;
	String[] parts = str.split(" ");  // split on blanks
	String result = "";
	
	for (String part : parts) {
	    if (part.length() > 0)
		result += new String(Character.toUpperCase(part.charAt(0)) + part.substring(1) + " ");
	}
	result = result.trim();
	return result;
    }  

    private boolean productNameInUse(Connection conn, String name) {
	boolean flag = false;
	try {
	    //*** There's a better way--an exercise waiting to happen.
	    Statement stmt = conn.createStatement();
	    String sql =  "SELECT product FROM skisEtc";
	    ResultSet rs = stmt.executeQuery(sql);
	    while (rs.next()) {
		String product = rs.getString("product");
		if (product.equalsIgnoreCase(name)) {
		    flag = true;
		    break;
		}
	    }
	    rs.close();
	    stmt.close();
	}
	catch(SQLException e) { }
	return flag;
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
