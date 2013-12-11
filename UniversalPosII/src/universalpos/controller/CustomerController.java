package universalpos.controller;
import universalpos.model.Customer;
import universalpos.model.Customers;
import android.content.Context;
public class CustomerController {
	private Customers customers;
	public CustomerController(Context context){
		this.customers = new Customers(context);
	}
	public boolean update(int id,Customer customer){	
		return customers.update(id, customer);
	}
	public boolean delete(String id) {
		return customers.delete(id);
	}
	public boolean insert(Customer customer){
		return customers.insert(customer);
	}
	public Customer findByKey(String customerID) {
		return customers.findByKey(customerID);
	}
	public Customer[] findAll() {
		return customers.findAll();
	}
}
