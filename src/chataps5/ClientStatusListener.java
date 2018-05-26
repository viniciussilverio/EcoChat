//Classe abstrata para status dos clientes
package chataps5;

public interface ClientStatusListener
{
    //Instancia as funções obrigatórias.
    void loginStatus(String status);
}
