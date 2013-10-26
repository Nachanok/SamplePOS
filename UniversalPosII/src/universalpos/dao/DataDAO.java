package universalpos.dao;

public interface DataDAO 
{
	public boolean update(String[] x);
	public boolean delete(int id);
	public boolean insert(String[] x);
	public String[] findByKey(String x);
	public String[] findAll();
}
