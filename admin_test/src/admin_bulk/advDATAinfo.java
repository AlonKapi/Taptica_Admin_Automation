package admin_bulk;

public class advDATAinfo {
	protected String advID;
	protected String advName;
	protected String advXpath;
	public advDATAinfo(String advID, String advName, String advXpath) {
		super();
		this.advID = advID;
		this.advName = advName;
		this.advXpath = advXpath;
	}
	public advDATAinfo() {
	}
	public String getAdvID() {
		return advID;
	}
	public void setAdvID(String advID) {
		this.advID = advID;
	}
	public String getAdvName() {
		return advName;
	}
	public void setAdvName(String advName) {
		this.advName = advName;
	}
	public String getAdvXpath() {
		return advXpath;
	}
	public void setAdvXpath(String advXpath) {
		this.advXpath = advXpath;
	}
	public void setHeader(String formatCellValue) {
	}

}