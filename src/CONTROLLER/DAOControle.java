package CONTROLLER;

import java.util.ArrayList;

import DAO.AlunoDAO;
import DAO.AlunoDisciplinaDAO;
import DAO.DisciplinaDAO;
import MODEL.Aluno;
import MODEL.Disciplina;

public class DAOControle
{
    public static ArrayList<Integer> AlunoDAO(ArrayList<Aluno> aluno)
    {
        AlunoDAO AlunoDAO = new AlunoDAO();
        AlunoDAO.Conn();
        ArrayList<Integer> pk = AlunoDAO.InserirAluno(aluno);
        AlunoDAO.Close();
        return pk;
    }

    public static ArrayList<ArrayList> DisciplinaDAO(ArrayList<Disciplina> disciplina)
    {
        DisciplinaDAO DisciplinaDAO = new DisciplinaDAO();
        DisciplinaDAO.Conn();
        ArrayList<ArrayList> pk = DisciplinaDAO.InserirDisciplina(disciplina);
        DisciplinaDAO.Close();
        return pk;
    }

    public static void AlunoDisciplinaDAO(ArrayList<Integer> pk_aluno, ArrayList<ArrayList> pk_disciplina, ArrayList<Disciplina> disciplina)
    {
        AlunoDisciplinaDAO AlunoDisciplinaDAO = new AlunoDisciplinaDAO();
        AlunoDisciplinaDAO.Conn();
        AlunoDisciplinaDAO.InserirAlunoDiciplinas(pk_aluno, pk_disciplina, disciplina);
        AlunoDisciplinaDAO.Close();
    }

    public static void RealizarConsulta(int seq)
    {
        AlunoDisciplinaDAO AlunoDisciplinaDAO = new AlunoDisciplinaDAO();
        AlunoDisciplinaDAO.Conn();
        DAO.AlunoDisciplinaDAO.RealizarConsulta(seq);
        AlunoDisciplinaDAO.Close();
    }
}