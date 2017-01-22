package convert;

import javax.faces.bean.ManagedBean;

// The @ManagedBean annotation provides JSF services over and above what would
// provided with a standard JavaBean (POJO). In JSF, the back-end POJO classes
// should be @ManagedBean classes.
@ManagedBean
public class C2F {
    private float centigrade = 0.0F;
    private float fahrenheit = 0.0F;
    private boolean show     = false;

    public C2F() { }
  
    //*** properties
    // read-write
    public float getCentigrade() { 
	return centigrade; 
    }
    public void setCentigrade(float centigrade) { 
	this.centigrade = centigrade; 
    }
 
    // read-only
    public float getFahrenheit() { 
	return fahrenheit; 
    }
    
    // read-only
    public boolean getShow() { 
	return show; 
    }

    // Restore initial state.
    public void reset () {
	show = false;
	centigrade = fahrenheit = 0.0F;
    }
   
    // Do the conversion.
    public String convert() {
	show = true;
	fahrenheit = (centigrade * (9.0F / 5.0F)) + 32.0F;
	return "success";
    }
} 
