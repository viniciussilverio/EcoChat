//Pacote da aplicação
package chataps5;

//Importação das classes
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageRecever implements Runnable
{
    public static final String DISCONNECT_STRING="DISCONNECT"; //Variável de desconexão
    ObjectInputStream input; //Objeto que armazena valores de entrada
    boolean keepListening=true; //Variavel de leitura de mensagens
    ClientListListener clientListListener; //Variavel listener
    ClientWindowListener clientWindowListener;
    ger_Clientes clientManager;
    Socket clientSocket;
    ExecutorService clientExecutor;
   


    MessageRecever(Socket getClientSocket,ClientListListener getClientListListener ,ClientWindowListener getClientWindowListener,ger_Clientes getClientManager)
    {
        clientExecutor=Executors.newCachedThreadPool();
        clientManager=getClientManager;
        clientSocket=getClientSocket;
        try
        {
            input = new ObjectInputStream(getClientSocket.getInputStream());
        }
        catch (IOException ex)
        {}
        clientListListener=getClientListListener;
        clientWindowListener=getClientWindowListener;
    }
    public void run()
    {
        String message,name="",ips = "";
        while(keepListening)
        {
            try
            {
                message = (String) input.readObject();
                System.out.println("Usuário está recebendo "+ message);
                StringTokenizer tokens=new StringTokenizer(message);

                String header=tokens.nextToken();
                if(tokens.hasMoreTokens())
                    name=tokens.nextToken();
     
                	
                if(header.equalsIgnoreCase("login"))
                {
                    clientListListener.addToList(name);
 
                }
                else if(header.equalsIgnoreCase(DISCONNECT_STRING))
                {
                    clientListListener.removeFromList(name);
                }
                else if(header.equalsIgnoreCase("server"))
                {
                    clientWindowListener.closeWindow(message);
                }else
                {
                    clientWindowListener.openWindow(message);
                }
            }
            catch (IOException ex)
            {
                clientListListener.removeFromList(name);
            }
            catch (ClassNotFoundException ex)
            {

            }
        }
    }

    void stopListening()
    {
        keepListening=false;
    }
}
