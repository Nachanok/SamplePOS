package universalpos.model;
import universalpos.activity.InventoryPage;
import universalpos.activity.InventoryPage_add;
import universalpos.dao.DAOFactory;
import universalpos.dao.DataDAO;
import universalpos.dao.InventoryDAO;
import universalpos.dao.ProductCatalogDAO;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Inventory 
{
	private InventoryDAO inventoryDao;
	private ProductCatalogDAO productCatalogDao;
	private DAOFactory daoFac;
	public Inventory(Context context) 
	{
		daoFac = new DAOFactory(context);
		inventoryDao = daoFac.getInventoryDAO();
		productCatalogDao = daoFac.getProductCatalogDAO();
	}
	public boolean update(String[] x) {
		// TODO complete it
		return false;
	}
	public boolean delete(String id) {	
		// TODO also delete in product catalog
		return inventoryDao.delete(id);
	}
	public boolean insert(Product product,int qnty){
		if(product.getProductID().equals("") || product.getProductName().equals("") || product.getPrice()<0 || product.getCost()<0)
			return false;
		if(qnty<0)
			qnty = 0;
		if(product.getProductDetail().equals(""))
			product.setProductDetail("N/A");
		// TODO not duplicate items
		if(findByKey(product.getProductID())!= null){
			if(findByKey(product.getProductID()).getProductID().equals(product.getProductID())){
				// TODO Edit qnty
			}
		}
		else{
			inventoryDao.insert(product,qnty);
		}
		// TODO add product to product catalog
		return true;
	}
	public Product findByKey(String productID){
		// TODO complete it
		return null;
	}
	public SaleLineItem[] findAll() {
		return inventoryDao.findAll();		
	}
}
