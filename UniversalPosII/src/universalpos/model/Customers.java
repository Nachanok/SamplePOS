package universalpos.model;
import universalpos.dao.CustomerDAO;
import universalpos.dao.DAOFactory;
import android.content.Context;
public class Customers {

private CustomerDAO customerDAO;
private DAOFactory daoFac;
	public Customers(Context context) {
		daoFac = new DAOFactory(context);
		customerDAO = daoFac.getCustomerDAO();
	}
	public boolean update(int id,Customer customer) {
		if(customer.getCustomerDetail().equals(""))
			customer.setCustomerDetail("N/A");
		if(customer.getCustomerTel().equals(""))
			customer.setCustomerTel("N/A");
		if(customer.getCostomerName().equals("") || customer.getCustomerID().equals(""))
			return false;
		return customerDAO.update(id, customer);
	}
	public boolean delete(String id) {	
		return customerDAO.delete(id);
	}
	public boolean insert(Customer customer){
		if(customer.getCustomerDetail().equals(""))
			customer.setCustomerDetail("N/A");
		if(customer.getCustomerTel().equals(""))
			customer.setCustomerTel("N/A");
		if(customer.getCostomerName().equals("") || customer.getCustomerID().equals(""))
			return false;
		return customerDAO.insert(customer);
	}
	public Customer findByKey(String customerID){
		return customerDAO.findByKey(customerID);
	}
	public Customer[] findAll() {
		return customerDAO.findAll();		
	}
}
