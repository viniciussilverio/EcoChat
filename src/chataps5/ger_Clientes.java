//Pacote da aplicação
package chataps5;

//Importação das classes
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ger_Clientes
{
    //Parametros de conexão
    ExecutorService clientExecutor;
    Socket clientSocket ;
    boolean isConnected=false;

    ObjectInputStream input;
    ObjectOutputStream output;
    MessageRecever messageRecever;

    public ger_Clientes()
    {
        clientExecutor=Executors.newCachedThreadPool();
    }

    public void conectar(ClientStatusListener clientStatus, String sServerAddress, String sPort)
    {
        try
        {
            if(isConnected)
                return;
            else
            {
                clientSocket=new Socket(sServerAddress,Integer.parseInt(sPort));
                clientStatus.loginStatus("Você está conectado em :"+sServerAddress);
                isConnected=true;
            }
        }
        catch (UnknownHostException ex)
        {
            clientStatus.loginStatus("Servidor não encontrado!!!");
        }
        catch (IOException ex)
        {
            clientStatus.loginStatus("Servidor não encontrado!!!");
        }
    }

    public void desconectar(ClientStatusListener clientStatus)
    {
        messageRecever.stopListening();
        try
        {
            clientStatus.loginStatus("Você não está mais conectado no servidor!!!");
            clientSocket.close();
        }
        catch (IOException ex)
        {
        }
    }

    public void sendMessage(String message)
    {
        clientExecutor.execute(new MessageSender(message));
    }

    boolean flageoutput=true;
    class MessageSender implements Runnable
    {
        String message;
        public MessageSender(String getMessage)
        {
            if(flageoutput)
            {
                try
                {
                    output = new ObjectOutputStream(clientSocket.getOutputStream());
                    output.flush();
                    flageoutput=false;
                }
                catch (IOException ex)
                {
                }
            }
            message=getMessage;
            System.out.println("O usuário está enviando   "+ message);
        }
        public void run()
        {
            try
            {
                output.writeObject(message);
                output.flush();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public void receiveMessage(ClientListListener getClientListListener ,ClientWindowListener getClientWindowListener)
    {
        messageRecever=new MessageRecever(clientSocket,getClientListListener, getClientWindowListener, this);
        clientExecutor.execute(messageRecever);
    }
}
