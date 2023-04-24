public class LoginView {
    
    public void displayLoginResult(boolean isAuthenticated) {
        if (isAuthenticated) {
            System.out.println("Login successful");
        } else {
            System.out.println("Invalid username or password");
        }
    }
}
