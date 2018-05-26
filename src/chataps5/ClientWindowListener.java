//Classe abstrata para status dos clientes
package chataps5;

public interface ClientWindowListener
{
    //Instancia as funções obrigatórias.
    public void openWindow(String message);
    public void closeWindow(String message);
    public void fileStatus(String filesStatus);
}
