import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Friend {

volatile private    String myIpHost;
volatile private int myPort;
volatile private String clientIpHost;
volatile private int clientPort;
volatile private String inputData;



volatile private String username;
volatile private static ArrayList<String> allData=new ArrayList<>(50);

  public   static synchronized ArrayList<String> getAllData() {  return allData;  }
  public synchronized String getInputData() {  return inputData;  }
  public synchronized void setInputData(String inputData) {  this.inputData = inputData;  }
  public Friend(String username, String myIpHost, int myPort, String clientIpHost, int clientPort) {  this.username=username;
        this.myIpHost = myIpHost;
        this.myPort = myPort;
        this.clientIpHost = clientIpHost;
        this.clientPort = clientPort;
        new Thread(new ServerThread()).start();
    }

    public synchronized void sendData(String outputData) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String currentTime = simpleDateFormat.format(date);
        Socket client =new Socket(clientIpHost,clientPort);
        DataOutputStream out = new DataOutputStream(client.getOutputStream());
        String pass="\t"+currentTime+"\n"+outputData;
        out.writeUTF(username+pass);
        //System.out.println(username+pass);
//        getAllData().add(username+pass);
//        mainFrame.getCopyOfAllData().add(username+pass);

        client.close();
    }



    private class ServerThread implements Runnable {

        @Override
        public synchronized   void run() {
            Socket socket = null;
            ServerSocket server = null;
            int i=0;
            try {
                server = new ServerSocket(myPort);
                while (true) {
                    socket = server.accept();
                    DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    setInputData(in.readUTF());
                    System.out.println(getInputData());
                    getAllData().add(getInputData());
                    //getAllData().add(getInputData());
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
