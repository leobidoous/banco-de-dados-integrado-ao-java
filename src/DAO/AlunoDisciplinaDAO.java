package DAO;

import javax.swing.JOptionPane;

import DATABASE.Database;
import MODEL.Disciplina;

import java.util.ArrayList;

public class AlunoDisciplinaDAO
{
    public void Conn()
    {
        Database conn = new Database();
        conn.Connect();
        boolean isconn = conn.isConnected();
        if(isconn)
        {
            String msg = "Conexão bem sucedida!";
            JOptionPane.showMessageDialog(null,msg,"Banco de Dados",1);
        }
    }

    public void Close()
    {
        Database conn = new Database();
        String msg = "Encerrando conexão!";
        JOptionPane.showMessageDialog(null,msg,"Banco de Dados",1);
        conn.Close();
    }

    public void InserirAlunoDiciplinas(ArrayList<Integer> pk_aluno, ArrayList<ArrayList> pk_disciplina, ArrayList<Disciplina> disciplina)
    {
        Database.InserirAlunoDiciplinas(pk_aluno, pk_disciplina, disciplina);
    }

    public static void RealizarConsulta(int seq)
    {
        Database.RealizarConsulta(seq);
    }
}
