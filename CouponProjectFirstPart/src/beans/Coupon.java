package beans;
import java.io.Serializable;
import java.time.LocalDate;
/**
 * this class is for building a Coupon object
 * 
 *
 */

@SuppressWarnings("serial")
public class Coupon implements Serializable {

	private int id;
	private int companyID;
	private Category category;
	private String title;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private int amount;
	private double price;
	private String image;
	
	public Coupon() {
		
	}
	
	public Coupon(Category category, String title, 
			String description, LocalDate startDate, LocalDate endDate, int amount, 
			double price, String image)
	{
		setCategory(category);
		setTitle(title);
		setDescription(description);
		setStartDate(startDate);
		setEndDate(endDate);
		setAmount(amount);
		setPrice(price);
		setImage(image);
	}
	
	public Coupon(int companyID, Category category, String title, 
			String description, LocalDate startDate, LocalDate endDate, int amount, 
			double price, String image)
	{
		setCompanyID(companyID);
		setCategory(category);
		setTitle(title);
		setDescription(description);
		setStartDate(startDate);
		setEndDate(endDate);
		setAmount(amount);
		setPrice(price);
		setImage(image);
	}
	public Coupon(int id, int companyID, Category category, String title, 
			String description, LocalDate startDate, LocalDate endDate, int amount, 
			double price, String image)
	{
		setId(id);
		setCompanyID(companyID);
		setCategory(category);
		setTitle(title);
		setDescription(description);
		setStartDate(startDate);
		setEndDate(endDate);
		setAmount(amount);
		setPrice(price);
		setImage(image);
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public String toString()
	{
		return "Class = Coupon, Id = " + id + ", CompanyID = " + companyID + ", Type = " + category +
				", Title = " + title + ", Description = " + description + ", Start date = " + startDate +
				", End date = " + endDate + ", Amount = " + amount + ", Image = " + image;
	}
	public void display() {
		
		System.out.println("Class = Coupon, Id = " + id + ", CompanyID = " + companyID + ", Type = " + category +
				", Title = " + title + ", Description = " + description + ", Start date = " + startDate +
				", End date = " + endDate + ", Amount = " + amount + ", Image = " + image);
	}

}
