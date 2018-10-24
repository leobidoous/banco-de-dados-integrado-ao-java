package DAO;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import DATABASE.Database;
import MODEL.Aluno;

public class AlunoDAO
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

    public ArrayList<Integer> InserirAluno(ArrayList<Aluno> aluno)
    {
        ArrayList<Integer> pk = Database.InserirAluno(aluno);
        return pk;
    }

    public void Close()
    {
        Database conn = new Database();
        String msg = "Encerrando conexão!";
        JOptionPane.showMessageDialog(null,msg,"Banco de Dados",1);
        conn.Close();
    }
}