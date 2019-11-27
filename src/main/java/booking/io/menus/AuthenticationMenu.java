package booking.io.menus;

public class AuthenticationMenu implements Menu {
    @Override
    public String show() {
        StringBuilder sb = new StringBuilder();
        sb.append("==================================\n");
        sb.append("|    Booking App                 |\n");
        sb.append("==================================\n");
        sb.append("| 1. Log in                      |\n");
        sb.append("| 2. Sign Up                     |\n");
        sb.append("| 3. Exit                        |\n");
        sb.append("==================================\n");
        return sb.toString();
    }
}
