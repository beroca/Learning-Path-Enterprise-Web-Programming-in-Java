package beans;

import java.math.BigDecimal;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class SkiEquipItem {
    private int id; 
    private String product;
    private String category;
    private BigDecimal price;

    public SkiEquipItem() { }

    public void setId(int id) {
	this.id = id;
    }
    public int getId() {
	return id;
    }

    public void setCategory(String category) {
	this.category = category;
    }
    public String getCategory() {
	return category;
    }

    public void setProduct(String product) {
	this.product = product;
    }
    public String getProduct() {
	return product;
    }
    
    public void setPrice(BigDecimal price) {
	this.price = price;
    }
    public BigDecimal getPrice() {
	return price;
    }
}