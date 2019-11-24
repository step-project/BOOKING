package booking.io.menus;

public class UserMenu implements Menu {

  public String show() {
    StringBuilder sb = new StringBuilder();
    sb.append("==================================\n");
    sb.append("|    Booking App                 |\n");
    sb.append("==================================\n");
    sb.append("| search - Search for a Flight   |\n");
    sb.append("| book - Make a booking          |\n");
    sb.append("| show - Show my bookings        |\n");
    sb.append("| cancel - Cancel my booking     |\n");
    sb.append("| logout - Log Out               |\n");
    sb.append("| exit - Exit                    |\n");
    sb.append("==================================\n");
    return sb.toString();
  }
}
