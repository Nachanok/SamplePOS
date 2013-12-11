package universalpos.controller;
import universalpos.model.EventRecord;
import universalpos.model.History;
import universalpos.model.SaleRecord;
import android.content.Context;

public class HistoryController {
	private History histoty;
	public HistoryController(Context context){
		this.histoty = new History(context);
	}
	public boolean insertEventRecord(String event) {
		return histoty.insertEventRecord(event);
	}
	public boolean deleteAllEventRecord() {
		return histoty.deleteAllEventRecord();
	}
	public EventRecord[] findAllEventRecord() {
		return histoty.findAllEventRecord();
	}
	
	public boolean insertSaleRecord(SaleRecord saleRecord) {
		return histoty.insertSaleRecord(saleRecord);
	}
	public boolean deleteAllSaleRecord() {
		return histoty.deleteAllSaleRecord();
	}
	public SaleRecord[] findAllSaleRecord() {
		return histoty.findAllSaleRecord();
	}
}
