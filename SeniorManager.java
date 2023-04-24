import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class SeniorManager implements giveAppraisal, normalisation { 
    @Override
    public void giveAppraisal(List<Employee> employees) {

        List<Employee> finalList;
        String teamId = " ";

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the team id: ");
            teamId = scanner.nextLine();

            // If the user has entered exit, update the employee details

            System.out.println("----------------------------------------------");
            System.out.println("Enter appraisal for all employees in "+ teamId);

        
            for (Employee emp :employees) {
                if(emp.getteamId().equals(teamId)) {
                    System.out.println("\n\nEmployee Name: " + emp.getName());
                    System.out.println("TeamID: "+teamId);
                    System.out.println("Manager Rating for the Employee: "+emp.getManagerAppraisal());
                    System.out.print("Enter your appraisal --- ");
                    int appraisal = scanner.nextInt();
                    emp.setSMAppraisal(appraisal);
                }
            }
            System.out.println("\n*****End of "+ teamId +"*****\n");

        } while (!teamId.equals("exit"));

        Employee.updateEmployeeDetails(employees);
    }

    @Override
    public void normalise(List<Employee> employees) {
       float total = 0;
       for(Employee emp: employees)
       {
            total = total+emp.getSMAppraisal();
            System.out.println(total);
       }

       for(Employee emp: employees)
       {
            emp.setNscore(emp.getSMAppraisal()/total);
       }

       Employee.updateEmployeeDetails(employees);
    }

}