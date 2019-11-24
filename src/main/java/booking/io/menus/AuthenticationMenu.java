package booking.io.menus;

public class AuthenticationMenu implements Menu {
    @Override
    public String show() {
        StringBuilder sb = new StringBuilder();
        sb.append("==================================\n");
        sb.append("|    Booking App                 |\n");
        sb.append("==================================\n");
        sb.append("| login - Log in                 |\n");
        sb.append("| signup - Sign Up               |\n");
        sb.append("| exit - Exit                    |\n");
        sb.append("==================================\n");
        return sb.toString();
    }
}
