class Asset {
    // Attributes declaration
	private String assetId;
    private String assetName;
    private String assetExpiry;

    // Parameterized Constructor
    public Asset(String assetId, String assetName, String assetExpiry){
        this.setAssetId(assetId);
        this.assetName = assetName;
        this.assetExpiry = assetExpiry;
    }

    // Getters and Setters
    public String getAssetId(){
        return assetId;
    }
    public void setAssetId(String assetId){
        // Regex for the specified rules
        String regex = "^(DSK|LTP|IPH)-\\d{6}[hHlL]$";
        // Validate and set the instance variable
        if(assetId != null && assetId.matches(regex)) {
            this.assetId = assetId;
        }
    }

    public String getAssetName(){
        return assetName;
    }
    public void setAssetName(String assetName){
        this.assetName = assetName;
    }

    public String getAssetExpiry(){
        return assetExpiry;
    }
    public void setAssetExpiry(String assetExpiry){
        this.assetExpiry = assetExpiry;
    }

	// Override toString() method
	@Override
	public String toString() {
		return "Asset Id: "+getAssetId()+", Asset Name: "+getAssetName()+", Asset Expiry: "+getAssetExpiry();
	}
}