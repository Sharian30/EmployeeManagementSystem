public class Employee {
    public String ID;
    public String Name;
    public String Address;
    public String Department;
    public String Phone;
    public String Email;
    public String Password;
    public String Salary;
    public String BloodGroup;
    public Employee(String ID,String Name,String Address,String Department,String Phone,String Email,String Salary,String BloodGroup)
    {


        this.ID = ID;
        this.Name = Name;
        this.Address = Address;
        this.Department = Department;
        this.Phone = Phone;
        this.Email = Email;
        this.Salary = Salary;
        this.BloodGroup = BloodGroup;
    }

    public Employee() {

    }
    public String getID() {
        return ID;
    }

    public Object getName() {
        return  Name;
    }
    public Object getAddress() {
        return  Address;
    }
    public Object getDepartment() {
        return Department;
    }
    public String getPhone()
    {
        return Phone;
    }

    public String getEmail()
    {
        return Email;
    }

    public Object getSalary() {
        return Salary;
    }
    public Object getBlood_Group() {
        return  BloodGroup;
    }

}
/*
public class User {
    public String name;
    public  String username;
    public  String password;
    public String confirm;
    public  String employeeID;
    public  String Email;
    public  String  PhoneNo;
    public  String Dept_name;
    public  String Salary;
    public  String District;
    public String Blood_Group;


    public User(String employeeID,String name,String Email,String PhoneNo,String Dept_name,String Salary,String District,String Blood_Group)
    {


        this.employeeID = employeeID;
        this.name = name;
        this.Email = Email;
        this.PhoneNo = PhoneNo;
        this.Dept_name = Dept_name;
        this.Salary = Salary;
        this.District = District;
        this.Blood_Group = Blood_Group;
    }

    public User() {

    }
public String getemployeeID() {
    return employeeID;
}
    public Object getname() {
        return  name;
    }


    public String getEmail()
    {
        return Email;
    }

    public String getPhoneNo()
    {
        return PhoneNo;
    }



    public Object getDept_name() {
        return Dept_name;
    }

    public Object getSalary() {
        return Salary;
    }



    public Object getDistrict() {
        return  District;
    }

    public Object getBlood_Group() {
        return  Blood_Group;
    }
}

 */
