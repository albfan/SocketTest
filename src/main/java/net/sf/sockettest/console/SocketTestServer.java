package net.sf.sockettest.console;

import net.sf.sockettest.SocketTestServerController;
import net.sf.sockettest.model.SocketTestServerModel;
import net.sf.sockettest.swing.SocketTestServerView;
import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SocketTestServer implements SocketTestServerView {


    private SocketTestServerModel model;

    public SocketTestServer() {
        model = new SocketTestServerModel();
    }

    @Override
    public void focusOnIp() {
        //Do nothing
    }

    @Override
    public void focusOnPort() {
        //Do nothing
    }

    @Override
    public void startWaitInfo() {
        //Do nothing
    }

    @Override
    public void stopWaitInfo() {
        //Do nothing
    }

    @Override
    public void connected() {
        System.out.println("connected");
    }

    @Override
    public void showConnectionInfo(String ip) {
        System.out.println("connected to ip <"+ip+">");
    }

    @Override
    public void socketSet(boolean set) {

    }

    @Override
    public void resetSendField() {

    }

    @Override
    public void clearMessages() {
        model.setMessages(new StringBuffer());
    }

    @Override
    public void appendMessage(String message) {
        model.getMessages().append(message+System.lineSeparator());
        System.out.println(message);
    }

    @Override
    public void stopped() {

    }

    @Override
    public String getMessages() {
        return model.getMessages().toString();
    }

    public static final int NO = 0;
    public static final int YES = 1;

    @Override
    public String chooseFile() {
        System.out.println("choose file:");
        Scanner input = new Scanner(System.in);
        boolean validAnswer;
        String answer = null;
        String tempResponse = input.next();
        File file = new File(tempResponse);
        if (file.exists()) {
            validAnswer = confirm("Warning", "file exists, are you sure?", YES);
        } else {
            validAnswer = confirm("Warning", "write messages to "+file.getAbsolutePath()+", are you sure?", YES);
        }
        if (validAnswer) {
            answer = file.getAbsolutePath();
        }
        return answer;
    }

    @Override
    public boolean confirm(String title, String message, int option) {
        Scanner input = new Scanner(System.in);
        boolean validAnswer =false;
        int answer = NO;
        while(!validAnswer) {
            System.out.println(title);
            System.out.println(message+": [y/n]");
            String a = input.next();
            if(a.equalsIgnoreCase("y")) {
                validAnswer = true;
                answer = YES;
            }
            else if(a.equalsIgnoreCase("n")) {
                validAnswer = true;
                answer = NO;
            }
        }
        return validAnswer && answer == option;
    }

    @Override
    public void error(String error) {
        System.err.println(error);
    }

    @Override
    public void error(String error, String heading) {
        System.err.println(heading);
        System.err.println(error);
    }

    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();

        Options options = new Options();

        Option help = new Option( "help", "print this message" );
        Option hexInput = new Option( "hinput", "input in hexadecimal" );
        Option hexOutput = new Option( "houtput", "output in hexadecimal" );
        Option ip   = Option.builder("h").argName( "host" )
                .hasArg()
                .required()
                .desc(  "ip to listen" )
                .longOpt( "logfile" ).build();
        Option port   = Option.builder("p").argName( "port" )
                .hasArg()
                .type(Number.class)
                .required()
                .desc(  "port to listen" )
                .longOpt( "port" ).build();

        options.addOption( help );
        options.addOption( hexInput );
        options.addOption( hexOutput );
        options.addOption( ip );
        options.addOption( port );

        SocketTestServer view = new SocketTestServer();
        try {
            CommandLine cmd = parser.parse( options, args);

            SocketTestServerModel model = new SocketTestServerModel();
            model.setHexInput(cmd.hasOption("hinput"));
            model.setHexOutput(cmd.hasOption("houtput"));
            model.setIp(cmd.getOptionValue("h"));
            try {
                //TODO: Insane number parsing on commons-cli
                model.setPort(((Long) cmd.getParsedOptionValue("port")).intValue());
            } catch (Exception e) {
                view.error(e.getMessage(), "port option is not an integer");
                System.exit(1);
            }
            SocketTestServerController controller = new SocketTestServerController();
            controller.setModel(model);
            controller.setView(view);
            controller.connect(model.getIp(), String.valueOf(model.getPort()));
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String inputString;
            while((inputString = bufferRead.readLine()) != null) {
                controller.buildMessage(inputString, model.isHexInput(), model.isHexOutput());
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.error(e.getMessage(),"error");
            System.exit(1);
        }
    }
}

