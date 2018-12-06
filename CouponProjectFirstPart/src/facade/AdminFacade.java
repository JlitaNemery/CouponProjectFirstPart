package facade;
import java.sql.SQLException;
import java.util.ArrayList;
import beans.*;
import dbdao.*;
import exceptions.*;

/**
 * 
 * this class handles admin functions
 * 
 *
 */
public class AdminFacade extends ClientFacade{
	public AdminFacade() {
		companiesDAO = new CompaniesDBDAO();
		customersDAO = new CustomersDBDAO();
		couponsDAO = new CouponsDBDAO();
	}

	@Override
	public boolean login(String email, String password) {
		return email.equals("admin@admin.com") && password.equals("admin");

	}


	public void addCompany(Company company) throws SQLException, 
	InterruptedException, CompanyNameAlreadyExistsException, 
	CompanyEmailAlreadyExistsExeption {
		ArrayList<Company> allCompanies = companiesDAO.getAllCompanies();

		for(Company comp : allCompanies) {
			if(comp.getName().equals(company.getName())) {
				throw new CompanyNameAlreadyExistsException();
			}
			if(comp.getEmail().equals(company.getEmail())){
				throw new CompanyEmailAlreadyExistsExeption();
			}
		}
		companiesDAO.addCompany(company);
	}

	public void updateCompany (Company company) throws SQLException, InterruptedException, 
	CompanyNameCantBeChangedException, CompanyDoesntExistException {
		Company tempCompany = companiesDAO.getOneCompany(company.getId());
		if(tempCompany.getName().equals(company.getName()) && tempCompany.getId()==company.getId()) {
			companiesDAO.updateCompany(company);
		}
		else {
			throw new CompanyNameCantBeChangedException();
		}
	}


	public void deleteCompany (Company company) throws SQLException, InterruptedException,
	CompanyDoesntExistException {
		ArrayList<Coupon> couponArr =
				couponsDAO.getAllCouponsFromCompany(company.getId());
		for(Coupon coupon:couponArr) {
			couponsDAO.deleteCoupon(coupon.getId());
			couponsDAO.deleteCouponPurchaseByCoupon(coupon.getId());
		}
		companiesDAO.deleteCompany(company.getId());
	}

	public ArrayList<Company> getAllCompanies() throws SQLException, InterruptedException{
		return companiesDAO.getAllCompanies();
	}
	public Company getOneCompany(int companyID) throws SQLException, 
	InterruptedException, CompanyDoesntExistException{
		return companiesDAO.getOneCompany(companyID);
	}

	public void addCustomer(Customer customer) throws SQLException, InterruptedException, 
	CustomerEmailAlreadyExistsExeption {
		ArrayList<Customer> allCustomers = customersDAO.getAllCustomers();

		for(Customer cust : allCustomers) {
			if(cust.getEmail().equals(customer.getEmail())){
				throw new CustomerEmailAlreadyExistsExeption();
			}
		}
		customersDAO.addCustomer(customer);
	}

	
	public void updateCustomer (Customer customer) throws SQLException, InterruptedException,
	CustomerIDCantBeChangedException {
		Customer tempCustomer = customersDAO.getOneCustomer(customer.getId());
		if(tempCustomer.getId() == customer.getId()) {
			customersDAO.updateCustomer(customer);
		}
		else {
			throw new CustomerIDCantBeChangedException();
		}
	}

	public void deleteCustomer (Customer customer) throws SQLException, InterruptedException,
	CompanyNameCantBeChangedException, CustomerDoesntExistException {
		couponsDAO.deleteCouponPurchaseByCustomer(customer.getId());
		customersDAO.deleteCustomer(customer.getId());

	}

	public ArrayList<Customer> getAllCustomers() throws SQLException, InterruptedException{
		return customersDAO.getAllCustomers();
	}
	public Customer getOneCustomer(int costumerID) throws SQLException, InterruptedException{
		return customersDAO.getOneCustomer(costumerID);
	}

	public String toString() {
		return "adminFacade";
	}


}
