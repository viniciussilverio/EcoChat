package chataps5;         //Pacote onde a classe está inserida.

//Importa as classes.
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

public class Arquivo {   //Classe que manipula arquivos.
    public static void gravarArquivo(String texto, String arq) throws ParseException, IOException {   //Grava dados em arquivo.
        if (!new File(arq).exists()){    //Verifica se o arquivo ja existe.
            new File(arq).createNewFile();     //Cria o arquivo.
        } 
        FileWriter Arquivo = new FileWriter(arq);  //Cria gravador de arquivo.
        try (BufferedWriter gravarArq = new BufferedWriter (Arquivo)) {    //Tenta abrir o arquivo.
            gravarArq.write(texto);    //Grava no arquivo.
            gravarArq.flush();     //Limpa o buffer.
            gravarArq.close();     //Desconecta do arquivo.
        }
    }
}