import app.Main;
import view.CreateEventView;
import view.MainMenuView;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.assertNotNull;

public class ViewNavigation {


    public JButton getButton(int n, int m){
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        MainMenuView sv = (MainMenuView) jp2.getComponent(0);

        JPanel buttons = (JPanel) sv.getComponent(n);
        buttons.getComponent(m);

        return (JButton) buttons.getComponent(0);
    }

    @org.junit.Test
    public void navigateToTaskView(){
        Main.main(null);
        JButton button = getButton(1,0);
        button.doClick();
    }


}
