public class LoginController {
    private LoginView view;
    private LoginFactory factory;

    public LoginController(LoginView view, LoginFactory factory) {
        this.view = view;
        this.factory = factory;
    }

    public boolean login(String type, String username, String password) {
        Login login = factory.createLogin(type);
        boolean isAuthenticated = login.authenticate(username, password);
        view.displayLoginResult(isAuthenticated);
        return isAuthenticated;
    }
    
}
