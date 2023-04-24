import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

interface Login {
    boolean authenticate(String username, String password);
}

class EmployeeLogin implements Login {
    private static final String FILE_NAME = "./DataFiles/employee_data.txt";
    private Map<String, String> userData;

    public EmployeeLogin() {
        userData = new HashMap<>();
        loadUserData();
    }

    private void loadUserData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                userData.put(tokens[0], tokens[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean authenticate(String username, String password) {
        String expectedPassword = userData.get(username);
        return expectedPassword != null && expectedPassword.equals(password);
    }
}

class ManagerLogin implements Login {
    private static final String FILE_NAME = "./DataFiles/manager_data.txt";
    private Map<String, String> userData;

    public ManagerLogin() {
        userData = new HashMap<>();
        loadUserData();
    }

    private void loadUserData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                userData.put(tokens[0], tokens[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean authenticate(String username, String password) {
        String expectedPassword = userData.get(username);
        return expectedPassword != null && expectedPassword.equals(password);
    }
}
class SeniorManagerLogin implements Login {
    private static final String FILE_NAME = "./DataFiles/seniormanager_data.txt";
    private Map<String, String> userData;

    public SeniorManagerLogin() {
        userData = new HashMap<>();
        loadUserData();
    }

    private void loadUserData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                userData.put(tokens[0], tokens[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean authenticate(String username, String password) {
        String expectedPassword = userData.get(username);
        return expectedPassword != null && expectedPassword.equals(password);
    }
}
class hrLogin implements Login {
    private static final String FILE_NAME = "./DataFiles/hr_data.txt";
    private Map<String, String> userData;

    public hrLogin() {
        userData = new HashMap<>();
        loadUserData();
    }

    private void loadUserData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                userData.put(tokens[0], tokens[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean authenticate(String username, String password) {
        String expectedPassword = userData.get(username);
        return expectedPassword != null && expectedPassword.equals(password);
    }
}
class LoginFactory {
    public Login createLogin(String type) {
        switch (type) {
            case "employee":
                return new EmployeeLogin();
            case "manager":
                return new ManagerLogin();
            case "seniormanager":
                return new SeniorManagerLogin();
            case "hr" :
                return new hrLogin();
            default:
                throw new IllegalArgumentException("Invalid login type: " + type);
        }
    }
}
public class Main {

    public static boolean isInitiated = false;
    public static AForm aform;

    public static void main(String[] args) throws IOException, InterruptedException {

        LoginView view = new LoginView();
        LoginFactory factory = new LoginFactory();
        LoginController controller = new LoginController(view, factory);
        Scanner obj=new Scanner(System.in);

        int ch;
        do{
            TimeUnit.SECONDS.sleep(3);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            
            System.out.println("--------------------------------------------------");
            System.out.println("WELCOME TO AUTOMATED PERFORMANCE APPRAISAL SYSTEM ");
            System.out.println("--------------------------------------------------");
            System.out.println("WHO DO YOU WANT TO LOGIN AS ? ");
            System.out.println("--------------------------------------------------");
            System.out.println("1.EMPLOYEE");
            System.out.println("2.MANAGER");
            System.out.println("3.SENIOR MANAGER");
            System.out.println("4.HR");
            System.out.println("5.EXIT");
            System.out.println("--------------------------------------------------");
            System.out.println("Please enter your choice");
            System.out.println("--------------------------------------------------");
            ch=obj.nextInt();
        
            switch(ch)
            {
                case 1 : System.out.print("Enter username : ");
                         String uname=obj.next();
                         System.out.print("Enter password : ");
                         String password=obj.next();

                        if(controller.login("employee",uname,password) && isInitiated)
                        {
                            List<Employee> employees = Employee.getEmployeeList();
                            System.out.println("Review has been initiated. Kindly fill in the appraisal form.");
                            Employee.Selfappraisal(uname, aform, employees);
                        }
                        
                        if(!isInitiated)
                        {
                            System.out.println("REVIEW IS NOT INITIATED");
                        }

                        break;

                case 2 : System.out.print("Enter username : ");
                         uname=obj.next();
                         System.out.print("Enter password : ");
                         password=obj.next();

                        if(controller.login("manager",uname,password) && isInitiated)
                        {
                            Manager manager = new Manager(uname);
                            List<Employee> employees = Employee.getEmployeeList();
                            System.out.println("Review has been initiated. Kindly fill in the appraisal form.");
                            manager.giveAppraisal(employees);
                        }

                        if(!isInitiated)
                        {
                            System.out.println("REVIEW IS NOT INITIATED");
                        }

                        break;

                case 3 : System.out.print("Enter username : ");
                        uname=obj.next();
                        System.out.print("Enter password : ");
                        password=obj.next();

                        if(controller.login("seniormanager",uname,password) && isInitiated)
                        {
                            SeniorManager SM = new SeniorManager();
                            List<Employee> employees = Employee.getEmployeeList();
                            int opt;
                            System.out.println("***Enter your choice***\n1.Give appraisal\n2.Normalise scores");
                            opt = obj.nextInt();
                            switch(opt) {

                                case 1: SM.giveAppraisal(employees); break;
                                case 2: SM.normalise(employees); break;
                                default: System.out.println("Invalid Choice");break;
                            }
                        }

                        if(!isInitiated)
                        {
                            System.out.println("REVIEW IS NOT INITIATED");
                        }

                        break;

                case 4 : System.out.print("Enter username : ");
                        uname=obj.next();
                        System.out.print("Enter password : ");
                        password=obj.next();
                        if(controller.login("hr",uname,password))
                        {
                            HR hr = new HR();
                            int opt;
                            System.out.println("***Enter your choice***\n1.Initiate Review\n2.Give Hikes");
                            opt = obj.nextInt();
                            switch(opt) {

                                case 1: isInitiated = hr.initiateReview(); 
                                    aform = hr.setTasks();
                                    break;

                                case 2: 
                                List<Employee> employees = Employee.getEmployeeList();
                                hr.giveHikes(employees); 
                                break;

                                default: System.out.println("Invalid Choice"); break;
                            }
                        }
                        break;

                case 5 : System.out.println("Thank you BYE");
                         System.exit(0);
                         break;
                default: System.out.println("Please enter a valid choice"); 
            }
        }while(ch!=5);
    }
}

