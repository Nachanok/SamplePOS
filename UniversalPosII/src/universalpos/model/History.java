package universalpos.model;

import universalpos.dao.DAOFactory;
import universalpos.dao.EventRecordDAO;
import universalpos.dao.InventoryDAO;
import universalpos.dao.SaleRecordDAO;
import android.content.Context;

public class History {
	private SaleRecordDAO saleRecordDAO;
	private EventRecordDAO eventRecordDAO;
 	private DAOFactory daoFac;
	public History(Context context) {
		daoFac = new DAOFactory(context);
		saleRecordDAO = daoFac.getSaleRecordDAO();
		eventRecordDAO = daoFac.getEventRecordDAO();
	}
	public boolean deleteAllEventRecord() {	
		return eventRecordDAO.deleteAll();
	}
	public boolean insertEventRecord(String event){
		return eventRecordDAO.insert(event);
	}
	public EventRecord[] findAllEventRecord() {
		return eventRecordDAO.findAll();		
	}
	
	public boolean deleteAllSaleRecord() {	
		return saleRecordDAO.deleteAll();
	}
	public boolean insertSaleRecord(SaleRecord saleRecord){
		return saleRecordDAO.insert(saleRecord);
	}
	public SaleRecord[] findAllSaleRecord() {
		return saleRecordDAO.findAll();		
	}
}
