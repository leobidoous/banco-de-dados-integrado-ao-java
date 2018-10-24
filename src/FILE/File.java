package FILE;

import java.io.*;
import java.util.ArrayList;

public class File
{
    public static ArrayList<String> LerArquivo()
    {
        try
        {
            String nome;
            ArrayList<String> Linha = new ArrayList();
//			nome = getClass().getResource("/src/arquivo/alunos.tx").toString();
            BufferedReader file = new BufferedReader(new FileReader("/home/leobidoous/Documentos/INTELLIJ/CATALOGO/src/FILE/alunos.txt"));
            while(file.ready())
            {
                String linha = file.readLine();
                Linha.add(linha);
            }
            file.close();
            return Linha;
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        return null;
    }
}