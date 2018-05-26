//Classe abstrata para lista de clientes
package chataps5;

public interface ClientListListener
{
    //Instancia as funções obrigatórias.
    void addToList(String userName);
    void removeFromList(String userName);
}
