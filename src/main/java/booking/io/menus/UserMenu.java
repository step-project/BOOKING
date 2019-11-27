package booking.io.menus;

public class UserMenu implements Menu {

  public String show() {
    StringBuilder sb = new StringBuilder();
    sb.append("==================================\n");
    sb.append("|    Booking App                 |\n");
    sb.append("==================================\n");
    sb.append("| 1. Show TimeTable              |\n");
    sb.append("| 2. Search for a Flight         |\n");
    sb.append("| 3. Make a booking              |\n");
    sb.append("| 4. Show my bookings            |\n");
    sb.append("| 5. Cancel my booking           |\n");
    sb.append("| 6. Log Out                     |\n");
    sb.append("| 7. Exit                        |\n");
    sb.append("==================================\n");
    return sb.toString();
  }
}
