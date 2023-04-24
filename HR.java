import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class HR implements normalisation {
    public boolean initiateReview() {
        System.out.println("Review initiated.");
        return true;
    }

    public AForm setTasks() {
        Scanner ip = new Scanner(System.in);
        System.out.print("Enter task 1: ");
        String t1 = ip.nextLine();
        System.out.print("Enter task 2: ");
        String t2 = ip.nextLine();
        System.out.print("Enter task 3: ");
        String t3 = ip.nextLine();
        AForm aform = new AForm(t1, t2, t3);
        return aform;
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

    
    public void giveHikes(List<Employee> employees) {
        String name = "";
        for (Employee emp: employees) 
        {
            int top = 0;
            if (emp.getNscore() > top) 
                name = emp.getName();
        }
        System.out.println("Employee " + name + " got a hike. Congratulations!");
    }
}







