package universalpos.dao;

import universalpos.model.Product;

public interface DataDAO 
{
	public boolean update(String[] x);
	public boolean delete(String id);
	public boolean insert(String[] x);
	public String[] findByKey(String x);
	public String[][] findAll();
	boolean insert(Product product, int qnty);
}
