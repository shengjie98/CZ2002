package stars;


public class LoginController {
    public boolean verifyLogin(String username, String password) {
        boolean adminVerification, studentVerification;
        adminVerification = checkAdmin(username, password);
        if (adminVerification) {
            AdminUI adminUI = new AdminUI();
            adminUI.displayMenu();
        } else {
            studentVerification = checkStudent(username, password);
            if (studentVerification) {
                StudentUI studentUI = new StudentUI();
                studentUI.displayMenu();
            }
        } 
        return false;
    }

    private boolean checkAdmin(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            return true;
        } 
        return false;
    }
    
    private boolean checkStudent(String username, String password) {
        return false;
    }
}
