abstract class Employee {
	// Instance variables
    private String employeeId;
    private String employeeName;
    private double salary;
    // Static Variables
    private static int contractIdCounter;
    private static int permenantIdCounter;
    static{
        contractIdCounter = 10000;
        permenantIdCounter = 10000;
    }

    // Parameterized Constructor
    public Employee(String employeeName){
        this.setEmployeeName(employeeName);
        if(this instanceof ContractEmployee){
            this.employeeId = "C" + (++contractIdCounter);
        } else if(this instanceof PermanentEmployee){
            this.employeeId = "E" + (++permenantIdCounter);
        }
        this.salary = 0.0;
    }

    // Calculate Salary Method
    public abstract void calculateSalary(float salaryFactor);
	
	// Getters and Setters
    public String getEmployeeId(){
        return employeeId;
    }
    public void setEmployeeId(String employeeId){
        this.employeeId = employeeId;
    }

    public String getEmployeeName(){
        return employeeName;
    }
    public void setEmployeeName(String employeeName){
        String regex = "^[A-Z][a-zA-Z]+(\\s[A-Z][a-zA-Z]+)+$";
        if(employeeName != null && employeeName.matches(regex)){
            this.employeeName = employeeName;
        }
    }

    public double getSalary(){
        return salary;
    }
    public void setSalary(double salary){
        if(salary > 0){
            this.salary = salary;
        }else{
            this.salary = 0.0;
        }
    }

    // Static methods 
    public static int getContractIdCounter(){
        return Employee.contractIdCounter;
    }
    public static void setContractIdCounter(int contractIdCounter){
        Employee.contractIdCounter = contractIdCounter;
    }

    public static int getPermenantIdCounter(){
        return Employee.permenantIdCounter;
    }
    public static void setPermenantIdCounter(int permenantIdCounter){
        Employee.permenantIdCounter = permenantIdCounter;
    }

	// Override the toString() method
	@Override
	public String toString() {
		return "Employee Id: "+getEmployeeId()+", Employee Name: "+getEmployeeName();
	}
}