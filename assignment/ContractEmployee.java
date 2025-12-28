class ContractEmployee extends Employee{
    // Instance Variable
    private double wagePerHour;

    // Parameterized Constructor
    public ContractEmployee(String employeeName, double wagePerHour){
        super(employeeName); // Call the superclass constructor
        this.wagePerHour = wagePerHour;
    }

    // Implement calculateSalary method
    @Override
    public void calculateSalary(float hoursWorked){
        double salary = this.wagePerHour * hoursWorked;
        if(hoursWorked < 190){
            salary = salary - ((190 - hoursWorked) * this.wagePerHour/2.0);
        }
        super.setSalary(Math.round(salary));
    }

    // Getters and Setters
    public double getWagePerHour(){
        return wagePerHour;
    }
    public void setWagePerHour(double wagePerHour){
        this.wagePerHour = wagePerHour;
    }
	// Override the toString method
	@Override
	public String toString() {
		return "Employee Id: "+getEmployeeId()+", Employee Name: "+getEmployeeName()+", Wage Per Hour: "+getWagePerHour();
	}
}