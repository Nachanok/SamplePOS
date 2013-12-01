package universalpos.model;
import universalpos.dao.DAOFactory;
import universalpos.dao.InventoryDAO;
import android.content.Context;
public class Inventory {
	private InventoryDAO inventoryDao;
	private DAOFactory daoFac;
	public Inventory(Context context) {
		daoFac = new DAOFactory(context);
		inventoryDao = daoFac.getInventoryDAO();
	}
	public boolean update(int id,Product product,int qnty) {
		if(product.getProductID().equals("") || product.getProductName().equals("") || product.getPrice()<0 || product.getCost()<0)
			return false;
		if(qnty<0)
			qnty = 0;
		if(product.getProductDetail().equals(""))
			product.setProductDetail("N/A");
		return inventoryDao.update(id, product, qnty);
	}
	public boolean delete(String id) {	
		return inventoryDao.delete(id);
	}
	public boolean insert(Product product,int qnty){
		if(product.getProductID().equals("") || product.getProductName().equals("") || product.getPrice()<0 || product.getCost()<0)
			return false;
		if(qnty<0)
			qnty = 0;
		if(product.getProductDetail().equals(""))
			product.setProductDetail("N/A");
		SaleLineItem stockLineItem = findByKey(product.getProductID());
		Product stockProduct = null;
		if(stockLineItem != null)
			stockProduct = stockLineItem.getProduct();
		if(stockProduct != null){
			if(stockProduct.getProductID().equals(product.getProductID())){
				inventoryDao.update(stockProduct.getId(),product,stockLineItem.getQnty()+qnty);
			}
		}
		else{
			inventoryDao.insert(product,qnty);
		}
		return true;
	}
	public SaleLineItem findByKey(String productID){
		return inventoryDao.findByKey(productID);
	}
	public SaleLineItem[] findAll() {
		return inventoryDao.findAll();		
	}
}
