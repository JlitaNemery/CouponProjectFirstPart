package facade;
import java.sql.SQLException;

import dbdao.*;

public abstract class ClientFacade {
	protected CompaniesDAO companiesDAO;
	protected CustomersDAO customersDAO;
	protected CouponsDAO couponsDAO;
	
	public abstract boolean login(String email, String password) throws SQLException, InterruptedException;
	
}
