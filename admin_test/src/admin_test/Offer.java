package admin_test;

import java.util.ArrayList;

public class Offer {
	protected int elements = 13; // without tracking link (for table)
	protected String offerName;
	protected String offerClass;
	protected String offerEndDate;
	protected String offerTrackingLink;
	protected String offerRevenue;
	protected String offerPayout;
	protected String offerBudgetType;
	protected String offerBudgetLimit;
	protected String offerBudgetStartDate;
	protected String offerDailyCap;
	protected ArrayList<String> offerCountries;
	protected ArrayList<String> offerPlatforms;
	protected String offerRequirements;
	protected String offerKPI;
	
	public Offer() {
		offerCountries = new ArrayList<String>();
		offerPlatforms = new ArrayList<String>();
	}
	
	public Offer(String offerName, String offerClass, String offerEndDate, String offerTrackingLink,
			String offerRevenue, String offerPayout, String offerBudgetType, String offerBudgetLimit,
			String offerBudgetStartDate, String offerDailyCap, ArrayList<String> offerCountries,
			ArrayList<String> offerPlatforms, String offerRequirements, String offerKPI) {
		this.offerName = offerName;
		this.offerClass = offerClass;
		this.offerEndDate = offerEndDate;
		this.offerTrackingLink = offerTrackingLink;
		this.offerRevenue = offerRevenue;
		this.offerPayout = offerPayout;
		this.offerBudgetType = offerBudgetType;
		this.offerBudgetLimit = offerBudgetLimit;
		this.offerBudgetStartDate = offerBudgetStartDate;
		this.offerDailyCap = offerDailyCap;
		this.offerCountries = offerCountries;
		this.offerPlatforms = offerPlatforms;
		this.offerRequirements = offerRequirements;
		this.offerKPI = offerKPI;
	}
	
	public String[] getOfferAsRow() {
		String[] row = {offerName, offerClass, offerEndDate, offerBudgetType, offerBudgetLimit, offerBudgetStartDate, offerDailyCap,
				offerRevenue, offerPayout, offerCountries.toString(), offerPlatforms.toString(), offerRequirements, offerKPI};
		
		return row;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getOfferClass() {
		return offerClass;
	}

	public void setOfferClass(String offerClass) {
		this.offerClass = offerClass;
	}

	public String getOfferEndDate() {
		return offerEndDate;
	}

	public void setOfferEndDate(String offerEndDate) {
		this.offerEndDate = offerEndDate;
	}

	public String getOfferTrackingLink() {
		return offerTrackingLink;
	}

	public void setOfferTrackingLink(String offerTrackingLink) {
		this.offerTrackingLink = offerTrackingLink;
	}

	public String getOfferRevenue() {
		return offerRevenue;
	}

	public void setOfferRevenue(String offerRevenue) {
		this.offerRevenue = offerRevenue;
	}

	public String getOfferPayout() {
		return offerPayout;
	}

	public void setOfferPayout(String offerPayout) {
		this.offerPayout = offerPayout;
	}

	public String getOfferBudgetType() {
		return offerBudgetType;
	}

	public void setOfferBudgetType(String offerBudgetType) {
		this.offerBudgetType = offerBudgetType;
	}

	public String getOfferBudgetLimit() {
		return offerBudgetLimit;
	}

	public void setOfferBudgetLimit(String offerBudgetLimit) {
		this.offerBudgetLimit = offerBudgetLimit;
	}

	public String getOfferBudgetStartDate() {
		return offerBudgetStartDate;
	}

	public void setOfferBudgetStartDate(String offerBudgetStartDate) {
		this.offerBudgetStartDate = offerBudgetStartDate;
	}

	public String getOfferDailyCap() {
		return offerDailyCap;
	}

	public void setOfferDailyCap(String offerDailyCap) {
		this.offerDailyCap = offerDailyCap;
	}

	public ArrayList<String> getOfferCountries() {
		return offerCountries;
	}

	public void setOfferCountries(ArrayList<String> offerCountries) {
		this.offerCountries = offerCountries;
	}

	public ArrayList<String> getOfferPlatforms() {
		return offerPlatforms;
	}

	public void setOfferPlatforms(ArrayList<String> offerPlatforms) {
		this.offerPlatforms = offerPlatforms;
	}

	public String getOfferRequirements() {
		return offerRequirements;
	}

	public void setOfferRequirements(String offerRequirements) {
		this.offerRequirements = offerRequirements;
	}

	public String getOfferKPI() {
		return offerKPI;
	}

	public void setOfferKPI(String offerKPI) {
		this.offerKPI = offerKPI;
	}
}
