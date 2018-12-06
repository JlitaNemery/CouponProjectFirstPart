package beans;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * this class is for building a Company object
 * 
 *
 */

@SuppressWarnings("serial")
public class Company implements Serializable{

	private int id;
	private String name, email, password;
	private ArrayList<Coupon> coupon;
	
	public Company(String name, String email, String password, ArrayList<Coupon> coupon) {
		setName(name);
		setEmail(email);
		setPassword(password);
		setCoupon(coupon);
	}
	
	public Company(int id, String name, String email, String password, ArrayList<Coupon> coupon) {
		setId(id);
		setName(name);
		setEmail(email);
		setPassword(password);
		setCoupon(coupon);
	}
	
	
	public Company() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Coupon> getCoupon() {
		return coupon;
	}

	public void setCoupon(ArrayList<Coupon> coupon) {
		this.coupon = coupon;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
	
	public void display() {
		System.out.println("Class = Company, " +" Name = " + name + ", Email: " + email+", id = "+ id +", password: " + password) ;
		System.out.println("----coupon arrayList----");
		for (Coupon couponInArr : coupon) 
		{
			System.out.println(couponInArr.toString());
		}

		System.out.println("------------------------------------------------");
	
	}
	
}
