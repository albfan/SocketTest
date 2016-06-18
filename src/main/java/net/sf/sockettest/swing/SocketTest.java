package net.sf.sockettest.swing;

import java.awt.*;
import javax.swing.*;

import net.sf.sockettest.SocketTestServerController;
import net.sf.sockettest.Util;
import net.sf.sockettest.controller.SocketTestClientController;
import net.sf.sockettest.model.SocketTestClientModel;
import net.sf.sockettest.model.SocketTestServerModel;

/**
 *
 * @author Akshathkumar Shetty
 */
public class SocketTest extends JFrame {
    private ClassLoader cl = getClass().getClassLoader();
    public ImageIcon logo = new ImageIcon(
            cl.getResource("icons/logo.gif"));
    public ImageIcon ball = new ImageIcon(
            cl.getResource("icons/ball.gif"));
    private JTabbedPane tabbedPane;
    
    /** Creates a new instance of SocketTest */
    public SocketTest() {
        Container cp = getContentPane();

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        SocketTestClient client = new SocketTestClient().setParent(this);
        SocketTestClientController controller = new SocketTestClientController();
        SocketTestClientModel model = new SocketTestClientModel();
        controller.setModel(model);
        client.setController(controller);
        SocketTestServer server = new SocketTestServer().setParent(this);
        SocketTestServerController controllerServer = new SocketTestServerController();
        SocketTestServerModel modelServer = new SocketTestServerModel();
        controllerServer.setModel(modelServer);
        server.setController(controllerServer);
        SocketTestUdp udp = new SocketTestUdp().setParent(this);
        About about = new About();
        
        tabbedPane.addTab("Client", ball, client, "Test any server");
        tabbedPane.addTab("Server", ball, server, "Test any client");
        tabbedPane.addTab("Udp", ball, udp, "Test any UDP Client or Server");
        tabbedPane.addTab("About", ball, about, "About SocketTest");
        
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        cp.add(tabbedPane);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("net.sourceforge.mlf.metouia.MetouiaLookAndFeel");
        } catch(Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch(Exception ee) {
                System.out.println("Error setting native LAF: " + ee);
            }
        }
		
		boolean toSplash = true;
		if(args.length>0) {
			if("nosplash".equals(args[0])) toSplash = false;
		}
        
		SplashScreen splash = null;
		if(toSplash) splash = new SplashScreen();
        
        SocketTest st = new SocketTest();
        st.setTitle("SocketTest v 3.0.0");
        st.setSize(600,500);
        Util.centerWindow(st);
        st.setDefaultCloseOperation(EXIT_ON_CLOSE);
        st.setIconImage(st.logo.getImage());
        if(toSplash) splash.kill();
        st.setVisible(true);
    }
    
}