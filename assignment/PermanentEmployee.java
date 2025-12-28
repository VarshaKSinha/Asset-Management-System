import java.util.ArrayList;
import java.util.List;

class PermanentEmployee extends Employee{
    // Instance Variables
    private double basicPay;
    private String[] salaryComponents;
    private float experience;
    private Asset[] assets;

    // Parameterized Constructor
    public PermanentEmployee(String employeeName, double basicPay, String[] salaryComponents, Asset[] assets){
        super(employeeName);
        this.basicPay = basicPay;
        this.salaryComponents = salaryComponents;
        this.assets = assets;
    }

    // Implement calculateBonus method
    public double calculateBonus(float experience) throws InvalidExperienceException {
        if (experience < 2.5) {
            throw new InvalidExperienceException("A minimum of 2.5 years is required for bonus!");
        }
        if (experience >= 12) return 13000.0;
        if (experience >= 8) return 8750.0;
        if (experience >= 4) return 5000.0;
        return 2550.0;
    }

    // Implement calculateSalary method
    @Override
    public void calculateSalary(float experience){
        this.experience = experience;
        if(this.salaryComponents != null){
            double bonus = 0.0;
            try{
                bonus = this.calculateBonus(experience);
            }catch(InvalidExperienceException e){
                System.out.println(e.getMessage());
            }

            double salary = this.basicPay + bonus;
            for(String component : this.salaryComponents){
                String[] parts = component.split("-");
                String type = parts[0];
                double value = Double.parseDouble(parts[1]);
                if(type.equals("DA")){
                    salary += (this.basicPay * value / 100);
                }else if(type.equals("HRA")){
                    salary += (this.basicPay * value / 100);
                }
            }
            super.setSalary(Math.round(salary));
        }  
    }

    // Implement getAssetsByDate method
    public Asset[] getAssetsByDate(String lastDate) throws InvalidAssetsException{
        // 1. Parse the input lastDate string
        String[] dateParts = lastDate.split("-");
        int lastYear = Integer.parseInt(dateParts[0]);
        int lastMonth = Resources.getMonth(dateParts[1]);
        int lastDay = Integer.parseInt(dateParts[2]);

        // Create a LocalDate object for easier comparison
        java.time.LocalDate targetDate = java.time.LocalDate.of(lastYear, lastMonth, lastDay);
        List<Asset> filteredList = new ArrayList<>();

        // 2. Iterate through allocated assets (assuming an instance variable 'allocatedAssets')
        for (Asset asset : this.assets){
            if (asset != null) {
                String[] expiryParts = asset.getAssetExpiry().split("-");
                int expYear = Integer.parseInt(expiryParts[0]);
                int expMonth = Resources.getMonth(expiryParts[1]);
                int expDay = Integer.parseInt(expiryParts[2]);
                java.time.LocalDate expiryDate = java.time.LocalDate.of(expYear, expMonth, expDay);
                // 3. Check if expiry is on or before the target date
                if (!expiryDate.isAfter(targetDate)) {
                    filteredList.add(asset);
                }
            }
        }

        // 4. Handle Empty Results
        if (filteredList.isEmpty()) {
            throw new InvalidAssetsException("No assets found for the given criteria!");
        }

        // 5. Return array of same length as total allocated assets
        Asset[] result = new Asset[this.assets.length];
        for (int i = 0; i < filteredList.size(); i++) {
            result[i] = filteredList.get(i);
        }

        return result;
    }

    // Getters and Setters
    public double getBasicPay(){
        return basicPay;
    }
    public void setBasicPay(double basicPay){
        this.basicPay = basicPay;
    }

    public String[] getSalaryComponents(){
        return salaryComponents;
    }
    public void setSalaryComponents(String[] salaryComponents){
        this.salaryComponents = salaryComponents;
    }

    public float getExperience(){
        return experience;
    }
    public void setExperience(float experience){
        this.experience = experience;
    }

    public Asset[] getAssets(){
        return assets;
    }
    public void setAssets(Asset[] assets){
        this.assets = assets;
    }
	
	// Override the toString method
	@Override
	public String toString() {
		return "Employee Id: "+getEmployeeId()+", Employee Name: "+getEmployeeName()+", Basic Pay: "+getBasicPay()+", Salary Components: "+getSalaryComponents()+", Assets: "+getAssets();
	}
}