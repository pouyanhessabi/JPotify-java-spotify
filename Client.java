import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
    private String info;
    private String clientName;
    private String ipHost;
    private int port;

    public Client(String clientName, String info, String ipHost, int port) throws IOException {
        this.info = info;
        this.clientName = clientName;
        this.ipHost = ipHost;
        this.port = port;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String currentTime = simpleDateFormat.format(date);
        Socket client = new Socket(ipHost, port);
        DataOutputStream out = new DataOutputStream(client.getOutputStream());
        String pass = this.clientName + "    " + currentTime + "\n" + this.info;
        out.writeUTF(pass);

    }

}