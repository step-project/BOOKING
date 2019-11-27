package booking.service;

import booking.io.menus.AuthenticationMenu;
import booking.io.menus.Menu;
import booking.io.menus.UserMenu;

public class MainService {
    Menu menu;

    public MainService(Menu menu) {
        this.menu = menu;
    }

    public void switchMenu() {
        if (this.menu instanceof UserMenu) {
            this.menu = new AuthenticationMenu();
        } else {
            this.menu = new UserMenu();
        }
    }

    public String menuContent() {
        return menu.show();
    }
}
