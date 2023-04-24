import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Employee {
    private String name = "";
    private String password;
    private String teamId;
    private int Task1;
    private int Task2;
    private int Task3;
    private int ManagerAppraisal;
    private int SMAppraisal;
    private float Nscore;
    private boolean flag;
    
    private static String filename = "./DataFiles/employee_data.txt";
    
    public Employee(String name, String password, String teamId, int task1, int task2, int task3, int ma, int sma, float nscore, boolean flag) {
        this.name = name;
        this.password = password;
        this .teamId = teamId;
        this.Task1 = task1;
        this.Task2 = task2;
        this.Task3 = task3;
        this.ManagerAppraisal = ma;
        this.SMAppraisal = sma;
        this.Nscore = nscore;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getteamId() {
        return teamId;
    }

    public int getTask1() {
        return Task1;
    }

    public int getTask2() {
        return Task2;
    }

    public int getTask3() {
        return Task3;
    }

    public int getManagerAppraisal() {
        return ManagerAppraisal;
    }

    public int getSMAppraisal() {
        return SMAppraisal;
    }

    public float getNscore() {
        return Nscore;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setTask1(int rate){
        this.Task1 = rate;
    }

    public void setTask2(int rate){
        this.Task2 = rate;
    }

    public void setTask3(int rate){
        this.Task3 = rate;
    }

    public void setManagerAppraisal(int rate){
        this.ManagerAppraisal = rate;
    }

    public void setSMAppraisal(int rate){
        this.SMAppraisal = rate;
    }

    public void setNscore(float rate){
        this.Nscore = rate;
    }
    
    public void setFlag(boolean f){
        this.flag = f;
    }
    
    public static List<Employee> getEmployeeList() {
        List<Employee> employees = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0].trim();
                String p = fields[1].trim();
                String teamid = fields[2].trim();
                int t1 = Integer.parseInt(fields[3].trim());
                int t2 = Integer.parseInt(fields[4].trim());
                int t3 = Integer.parseInt(fields[5].trim());
                int ma = Integer.parseInt(fields[6].trim());
                int sma = Integer.parseInt(fields[7].trim());
                float ns = Float.parseFloat(fields[8].trim());
                boolean f = Boolean.parseBoolean(fields[9].trim());
                employees.add(new Employee(name, p, teamid, t1, t2, t3, ma, sma, ns, f));
            }
        }

        catch (IOException e) {
            System.out.println("Error reading employee data file.");
        }

        return employees;
    }

    public static void updateEmployeeDetails(List<Employee> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Employee employee : employees) {
                String line = employee.getName() + "," + employee.getPassword() + ", " + employee.getteamId() + ", "+ employee.getTask1() +
                                ", " + employee.getTask2() + ", " + employee.getTask3() + ", " + employee.getManagerAppraisal() +
                                ", " + employee.getSMAppraisal() + ", " + employee.getNscore() + ", "+employee.getFlag();
                writer.write(line);
                writer.newLine();
            }
        }    

            catch (IOException e) {
                System.out.println("Error writing to employee data file.");
            }
        
        System.out.println("Successfully updated");
    }

    public static void Selfappraisal(String uname, AForm aform, List<Employee> employees) {

        int score1, score2, score3;
        Scanner obj=new Scanner(System.in);
        System.out.println("Task1 \n" + aform.getT1());
        System.out.println("Rate yourself for task1: ");
        score1 = obj.nextInt();
        System.out.println("Task1 \n" + aform.getT2());
        System.out.println("Rate yourself for task2: ");
        score2 = obj.nextInt();
        System.out.println("Task1 \n" + aform.getT3());
        System.out.println("Rate yourself for task3: ");
        score3 = obj.nextInt();

        for(Employee emp: employees){
            if(emp.getName().equals(uname) && emp.getFlag()){
                emp.setTask1(score1);
                emp.setTask2(score2);
                emp.setTask3(score3);
                emp.setFlag(true);
            }

            else if(!emp.getFlag()){
                System.out.println("You've already submitted the form..");
            }
        }
        Employee.updateEmployeeDetails(employees);
    }
}
