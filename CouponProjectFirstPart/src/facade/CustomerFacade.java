package facade;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import beans.*;
import dbdao.*;
import exceptions.*;

/**
 * 
 * this class handles customer functions
 *
 */
public class CustomerFacade extends ClientFacade {
	public CustomerFacade() {
		companiesDAO = new CompaniesDBDAO();
		customersDAO = new CustomersDBDAO();
		couponsDAO = new CouponsDBDAO();
	}

	public int getCustomerID() {
		return this.customerID;
	}

	public static LocalDate now = LocalDate.now();

	private int customerID;

	@Override
	public boolean login(String email, String password) throws SQLException, InterruptedException {
		Customer customer = customersDAO.getOneCustomerByEmail(email);
		if(customersDAO.isCustomerExists(customer.getEmail(), customer.getPassword())) {
			if((customer.getPassword()).equals(password)){
				this.customerID = customer.getId();
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}


	public void purchaseCoupon(Coupon coupon) throws SQLException, InterruptedException,
	CouponExpiredException, CouponDepletedException {

		LocalDate now = LocalDate.now();
		if(coupon.getAmount()>0) {
			if(coupon.getEndDate().isAfter(now)) {
				couponsDAO.addCouponPurchase(this.customerID, coupon.getId());
				coupon.setAmount(coupon.getAmount()-1);
				couponsDAO.updateCoupon(coupon);
				return;
			}
			else {
				throw new CouponExpiredException();
			}
		}
		else {
			throw new CouponDepletedException();
		}
	}

	public ArrayList<Coupon> getCustomerCoupons() throws InterruptedException, SQLException{
		return couponsDAO.getCouponByCustomerID(this.customerID);
	}

	public ArrayList<Coupon> getCustomerCoupons(Category category) throws SQLException, InterruptedException{
		ArrayList<Coupon> tempCouponArray = couponsDAO.getCouponByCustomerID(this.customerID);
		ArrayList<Coupon> returnedCouponArray = new ArrayList<>();
		for(Coupon coupon:tempCouponArray) {
			if(coupon.getCategory()==category) {
				returnedCouponArray.add(coupon);
			}
		}
		return returnedCouponArray;
	}

	public ArrayList<Coupon> getCustomerCoupons(double maxPrice) throws SQLException, InterruptedException{
		ArrayList<Coupon> tempCouponArray = couponsDAO.getCouponByCustomerID(this.customerID);
		ArrayList<Coupon> returnedCouponArray = new ArrayList<>();
		for(Coupon coupon:tempCouponArray) {
			if(coupon.getPrice()<=maxPrice) {
				returnedCouponArray.add(coupon);
			}
		}
		return returnedCouponArray;
	}
	public Customer getCustomerDetails() throws SQLException, InterruptedException {
		return customersDAO.getOneCustomer(this.customerID);
	}

	public String toString() {
		return"customer facade";
	}
}
