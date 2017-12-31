package admin_bulk;

public class BulkUpdateoffers {
	protected String advID;
	protected String offersId;

	public BulkUpdateoffers(String advID , String offersId)
	{
		this.advID=advID;
		this.offersId=offersId;
	}
	public BulkUpdateoffers() {
	}
	public void setadvID(String advID) {
		this.advID=advID;
	}
	public String getadvID() {
		return advID;
	}
	public String getoffersID()
	{
		return offersId;
	}
	public void setoffersID(String offersID )
	{
		this.offersId=offersID;
	}

	public void setHeader(String formatCellValue) {
	}
	@Override
	public String toString() {
		return offersId ;
	}
	public Object[] toData() {
		return new Object[] {
				advID,offersId
		};
	}
	public static String[] toColums() {
		return new String[] {"ADV ID","OfferID"};
	}
}
