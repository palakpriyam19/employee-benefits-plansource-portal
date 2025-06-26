package models;

public class Employee {
    private final String firstName;
    private final String lastName;
    private final String password;
    private final String ssn;
    private final String hireDate;
    private final String eligibleDate;
    private final String address1;
    private final String city;
    private final String state;
    private final String zipCode;
    private final String country;
    private final String birthDate;
    private final String gender;
    private final String maritalStatus;
    private final String employmentLevel;
    private final String currentSalary;
    private final String benefitSalary;
    private final String payrollSchedule;
    private final String location;

    public Employee(String firstName, String lastName, String password, String ssn, String hireDate,
                    String eligibleDate, String address1, String city, String state,
                    String zipCode, String country, String birthDate, String gender,
                    String maritalStatus, String employmentLevel, String currentSalary,
                    String benefitSalary, String payrollSchedule, String location) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.hireDate = hireDate;
        this.eligibleDate = eligibleDate;
        this.address1 = address1;
        this.city = city;
        this.country = country;
        this.state = state;
        this.zipCode = zipCode;
        this.birthDate = birthDate;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.employmentLevel = employmentLevel;
        this.currentSalary = currentSalary;
        this.benefitSalary = benefitSalary;
        this.payrollSchedule = payrollSchedule;
        this.location = location;
    }

    // Getters
    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public String getHireDate() {
        return hireDate;
    }

    public String getEligibleDate() {
        return eligibleDate;
    }

    public String getAddress1() {
        return address1;
    }

    public String getLocation(){
        return location;
    }

    public String getPayrollSchedule(){
        return payrollSchedule;
    }

    public String getBenefitSalary(){
        return benefitSalary;
    }

    public String getCurrentSalary(){
        return currentSalary;
    }

    public String getEmploymentLevel(){
        return employmentLevel;
    }

    public String getMaritalStatus(){
        return maritalStatus;
    }

    public String getCity(){
        return city;
    }

    public String getState(){
        return state;
    }

    public String getZipCode(){
        return zipCode;
    }

    public String getCountry(){
        return country;
    }

    public String getBirthDate(){
        return birthDate;
    }

    public String getGender(){
        return gender;
    }
}
