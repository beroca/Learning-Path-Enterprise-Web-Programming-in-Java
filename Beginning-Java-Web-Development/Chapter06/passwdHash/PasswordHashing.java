/** Password hashing: covers the basic steps of checking for a strong password,
    generating 'salt' to add to this password, hashing the salted password, and then
    saving the user's email address (hard-wired for this test), salt, and digested
    password to the database.

    To compile: javac PasswordHashing.java
    To run:     java -cp .:postgresql-jdbc.jar PasswordHashing  ## on Windows: -cp .;postgresql-jdbc.jar
 */

import java.math.BigInteger;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.sql.DriverManager; 
import java.sql.Connection;    
import java.sql.Statement;  
import java.sql.PreparedStatement;   
import java.sql.ResultSet;     
import java.util.Properties;   
import java.util.Arrays;

public class PasswordHashing {
    private static final String PwordPattern = "^((?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$!%]).{7,21})$";
    private static final int SaltSize = 128; // number of bits from the generator
    private static final int base = 32;      // base in which to encode the generated bits
    private static final SecureRandom randGenerator = new SecureRandom(); 
    private static MessageDigest messageDigest = null; 

    public PasswordHashing() {
	try {
	    messageDigest = MessageDigest.getInstance("SHA-256"); // SHA = 'Secure Hash Algorithm'
	}
	catch(Exception e) { System.exit(0); }
    }

    public static void main(String[ ] args) {
	new PasswordHashing().test();
    }

    private void test() {
	Pattern pattern = Pattern.compile(PwordPattern); // for efficiency
	String password = "quit";
	Scanner scanner = new Scanner(System.in);
	byte[ ] digest = null;

	// Test until user tires.
	do {
	    System.out.print("Password ('quit' to quit): ");
	    password = scanner.nextLine();
	    if (password.equalsIgnoreCase("quit"))
		break;

	    Matcher matcher = pattern.matcher(password);
	    if (matcher.matches()) 
		System.out.println(password + " is sufficiently strong.");
	    else
		System.out.println(password + " is not sufficiently strong.");
	} while (true);

	// Run a test by inserting a record in the DB and then checking
	// a computed passwd hash against the stored passwd hash.
	String userEmail = "alice@someEmail.org";
	String passwd = "apples123A!";
	String salt = getSalt();
	digest = getHashedPassword(passwd, salt);
	testAgainstDB(userEmail, salt, digest);
	simulateLogin(userEmail, passwd);
    }

    private String getSalt() {
	return new BigInteger(SaltSize, randGenerator).toString(base); 
    }

    private byte[ ] getHashedPassword(String pword, String salt) {
	byte[ ] digest = null;
	try {
	    String saltedPword = pword + salt;
	    messageDigest.update(saltedPword.getBytes());
	    digest = messageDigest.digest();
	}
	catch(Exception e) { }
	return digest;
    }

    private void printDigest(byte[ ] digest) {
	System.out.println("### bytes == digested salted password with length " + digest.length);
	for (int i = 0; i < digest.length; i++) 
	    System.out.format("%x ", digest[i]);
	System.out.println();
    }

    private void simulateLogin(String userEmail, String passwd) {
	try {
	    Connection conn = setUp();

	    // First, get the stored digest from the DB table.
	    String sql = "Select * from users where email = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, userEmail);
	    ResultSet rs = stmt.executeQuery();
	    rs.next(); // move to record
	    byte[ ] storedDigest = rs.getBytes("digest");
	    
	    // Second, compare this digest against the newly computed digest.
	    String salt = rs.getString("salt");  // the stored 'salt'
	    byte[ ] computedDigest = getHashedPassword(passwd, salt);

	    // Third, print the two arrays for emphasis and a confirmation message.
	    System.out.println();
	    printDigest(storedDigest);
	    printDigest(computedDigest);

	    String msg = "The login is not confirmed. Try again.";
	    if (Arrays.equals(storedDigest, computedDigest))
		msg = "Login confirmed.";
	    System.out.println(msg);
	    
	    // Clean up.
	    rs.close();
	    stmt.close();
	    conn.close();
	}
	catch(Exception e) { }
    }

    private void testAgainstDB(String email, String salt, byte[ ] digest) {
	Connection conn = setUp();
	try {
	    // Insert a test record, if if it's not there already.
	    if (!recordExistsAlready(conn)) {
		String sql = "Insert into users (email, salt, digest) values (?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, email);
		stmt.setString(2, salt);
		stmt.setBytes(3, digest);
		stmt.executeUpdate();
		stmt.close();
	    }
	    conn.close();
	}
	catch(Exception e) { }
    }

    private boolean recordExistsAlready(Connection conn) {
	boolean flag = false;
	try {
	    Statement stmt = conn.createStatement();
	    String sql = "select count(*) as total from users";
	    ResultSet rs = stmt.executeQuery(sql);
	    rs.next();
	    if (rs.getInt("total") > 0)
		flag = true;
	}
	catch(Exception e) { e.printStackTrace(); }
	return flag;
    }

    private Connection setUp() {
	String uri = "jdbc:postgresql://localhost/skistuff";  //*** skistuff is the database's name
	Properties props = setLoginForDB();
	Connection conn = null;

	try {
	    Class.forName("org.postgresql.Driver"); //*** load the PostreSQL driver
	    conn = DriverManager.getConnection(uri, props);
	}
	catch(Exception e) { e.printStackTrace(); }
	return conn;
    }

    private Properties setLoginForDB() {
	Properties props = new Properties();
	props.setProperty("user", "fred");
	props.setProperty("password", "secret");
	return props;
    }
}
