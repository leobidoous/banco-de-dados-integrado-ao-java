package DAO;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import DATABASE.Database;
import MODEL.Disciplina;

public class DisciplinaDAO
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

    public ArrayList<ArrayList> InserirDisciplina(ArrayList<Disciplina> disciplina)
    {
        ArrayList<ArrayList> pk = Database.InserirDisciplina(disciplina);
        return pk;
    }
}
