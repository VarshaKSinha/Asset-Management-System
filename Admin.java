class Admin {
    // Implement generateSalarySlip method
    public void generateSalarySlip(Employee[] employees, float[] salaryFactor){
        if(employees == null || salaryFactor == null || employees.length != salaryFactor.length){
            System.out.println("Invalid employees or salary factors data!");
            return;
        }

        for(int i = 0; i < employees.length; i++){
            Employee emp = employees[i];
            float factor = salaryFactor[i];

            if(emp != null){
                emp.calculateSalary(factor);
            }
        }
    }

    // Implements generateAssetsReport method
    public int generateAssetsReport(Employee[] employees, String lastDate) {
        int totalCount = 0;
        // Safety check for null input
        if (employees == null || lastDate == null) 
            return -1;
        for (Employee emp : employees) {
            // Only permanent employees have assets via getAssetsByDate()
            if (emp instanceof PermanentEmployee) {
                PermanentEmployee permEmp = (PermanentEmployee) emp;
                try {
                    // Invoke getAssetsByDate of PermanentEmployee
                    Asset[] assetsByDate = permEmp.getAssetsByDate(lastDate);
                    // If assets are found, increment the total count based on the array length
                    if (assetsByDate != null) {
                        for (Asset asset : assetsByDate) {
                            if (asset != null)
                                totalCount++;
                        }
                    }
                } catch (InvalidAssetsException e) {// If InvalidAssetsException is thrown, handle it and return -1
                    return -1;
                }
            }
        }
        // Return the final aggregated count
        return totalCount;
    }

    // Implement generateAssetsReport method
    public String[] generateAssetsReport(Employee[] employees, char assetCategory) {
        // 1. Initialize result array with length thrice the employees array
        String[] assetReport = new String[employees.length * 3];
        int reportIndex = 0;

        // 2. Iterate through each employee
        for (Employee emp : employees) {
            if(emp instanceof ContractEmployee) {
                continue; // Skip contract employees
            }
            if (emp instanceof PermanentEmployee) {
                PermanentEmployee permEmp = (PermanentEmployee) emp;
                Asset[] assets = permEmp.getAssets();
    
                // 3. Iterate through each asset of the permanent employee
                if (assets != null) {
                    for (Asset asset : assets) {
                        if (asset.getAssetId().charAt(0) == Character.toUpperCase(assetCategory)) {
                            assetReport[reportIndex++] = asset.getAssetId();
                        }
                    }
                }
            }
        }
        return assetReport;
    }
}