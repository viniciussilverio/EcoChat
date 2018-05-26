//Pacote da aplicação
package chataps5;

public class ChatAPS5 {
    //Classe Principal
    public static void main(String[] args) 
    {
        ger_Clientes Clientes=new ger_Clientes(); //Instancia o gestor e clientes
        Tela_Login tela_Login=new Tela_Login(Clientes); //Instancia a janela de login
        tela_Login.setLocation(400,100); //Define o tamanho da tela de login
        tela_Login.setVisible(true); //Exibe a tela de login
        TocaSom.Tocar();
    }

}
