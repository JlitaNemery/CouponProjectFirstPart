package facade;
import java.sql.SQLException;
import java.util.ArrayList;
import beans.*;
import dbdao.*;
import exceptions.*;

/**
 * 
 * this class handles company functions
 *
 */
public class CompanyFacade extends ClientFacade{
	public CompanyFacade() {
		companiesDAO = new CompaniesDBDAO();
		customersDAO = new CustomersDBDAO();
		couponsDAO = new CouponsDBDAO();
	}

	private int companyID;
	
	public int getCompanyID() {
		return this.companyID;
	}
	
	@Override
	public boolean login(String email, String password) throws SQLException,
	InterruptedException {
		Company company = companiesDAO.getOneCompanyByEmail(email);
		if(companiesDAO.isCompanyExists(company.getEmail(), company.getPassword())){ 
			if((company.getPassword()).equals(password)){
				this.companyID = company.getId();
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}

	public void addCoupon(Coupon coupon) throws SQLException,
	InterruptedException, CouponAlreadyExistsForCompanyException {
		ArrayList<Coupon> couponArr =
				couponsDAO.getAllCouponsFromCompany(this.companyID);
		for(Coupon couponFromArr:couponArr) {
			if((couponFromArr.getTitle()).equals(coupon.getTitle())) {
				throw new CouponAlreadyExistsForCompanyException();
			}			
		}
		coupon.setCompanyID(this.companyID);
		couponsDAO.addCoupon(coupon);	
	}

	public void updateCoupon (Coupon coupon) throws SQLException, InterruptedException, 
	CouponIDAndCouponCompanyIDCantBeChangedException, CouponDoesntExistException {
		Coupon tempCoupon = couponsDAO.getOneCoupon(coupon.getId());
			if((tempCoupon.getCompanyID()==coupon.getCompanyID()) && (tempCoupon.getId()==coupon.getId())){
				couponsDAO.updateCoupon(coupon);
			}
			else {
				throw new CouponIDAndCouponCompanyIDCantBeChangedException();
			}
	}

	public void deleteCoupon (int couponID) throws SQLException, 
	InterruptedException {
		ArrayList<Coupon> allCompanyCoupons = couponsDAO.getAllCouponsFromCompany(this.companyID);
		for(Coupon tempCoupon:allCompanyCoupons) {
			if(tempCoupon.getId() == couponID) {
				couponsDAO.deleteCoupon(couponID);
				couponsDAO.deleteCouponPurchaseByCoupon(couponID);
			}
		}
	}

	public ArrayList<Coupon> getCompanyCoupons() throws SQLException, InterruptedException{
		return couponsDAO.getAllCouponsFromCompany(this.companyID);
	}

	public ArrayList<Coupon> getCompanyCoupons(Category category) throws SQLException, InterruptedException{
		ArrayList<Coupon> tempCouponArray = couponsDAO.getAllCouponsFromCompany(this.companyID);
		ArrayList<Coupon> returnedCouponArray = new ArrayList<>();
		for(Coupon coupon:tempCouponArray) {
			if(coupon.getCategory()==category) {
				returnedCouponArray.add(coupon);
			}
		}
		return returnedCouponArray;
	}

	public ArrayList<Coupon> getCompanyCoupons(double maxPrice) throws SQLException, InterruptedException{
		ArrayList<Coupon> tempCouponArray = couponsDAO.getAllCouponsFromCompany(this.companyID);
		ArrayList<Coupon> returnedCouponArray = new ArrayList<>();
		for(Coupon coupon:tempCouponArray) {
			if(coupon.getPrice()<=maxPrice) {
				returnedCouponArray.add(coupon);
			}
		}
		return returnedCouponArray;
	}

	public Company getCompanyDetails() throws SQLException, InterruptedException {
		return companiesDAO.getOneCompany(this.companyID);
	}
	
	public String toString() {
		return "company facade";
	}

}
