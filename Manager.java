import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager implements giveAppraisal { 

    String uname;
    Boolean flag;

    public Manager(String name) {
        this.uname = name;
        this.flag = false;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean f){
        this.flag = f;
    }
    
    @Override
    public void giveAppraisal(List<Employee> employees) {
        String teamId = " "; 
        
        switch(uname){
            case "manager1": teamId = "team1";
                             break;
            case "manager2": teamId = "team2";
                             break;
            case "manager3": teamId = "team3";
                             break;
        }

        System.out.println("----------------------------------------------");
        System.out.println("Enter appraisal for all employees in "+ teamId);

        Scanner scanner = new Scanner(System.in);
        
        for (Employee emp : employees) {
            if(emp.getteamId().equals(teamId))
            {
                System.out.println(emp.getFlag());
                if(! emp.getFlag() || emp.getName().isEmpty()) {
                    System.out.println("Member of " + teamId + " yet to complete self appraisal.");
                    return;
                }

                System.out.println("\n\nEmployee Name: " + emp.getName());
                System.out.println("TeamID: "+teamId);
                System.out.print("Enter your appraisal --- ");
                int appraisal = scanner.nextInt();
                emp.setManagerAppraisal(appraisal);
            }
        }
        System.out.println("\n*****End of "+ teamId +"*****\n");
        this.flag = true;
        Employee.updateEmployeeDetails(employees);
    }

}
