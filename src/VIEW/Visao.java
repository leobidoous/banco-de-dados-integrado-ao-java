package VIEW;

import MODEL.*;
import CONTROLLER.*;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Visao
{
    public static void Visao()
    {

//        Variáveis referentes ao modelo Aluno
        ArrayList<Aluno> Aluno;
        AlunoControle AlunoControle = new AlunoControle();
        Aluno = AlunoControle.DadosAlunos();

//		 Variáveis referentes ao modelo Disciplinas
        ArrayList<Disciplina> Disciplina;
        DisciplinaControle DisciplinaControle = new DisciplinaControle();
        Disciplina = DisciplinaControle.DisciplinasAlunos();




//        Manipulação DAO Aluno
        ArrayList<Integer> pk_aluno = DAOControle.AlunoDAO(Aluno);

//        Manipulação DAO Disciplina
		ArrayList<ArrayList> pk_disciplina = DAOControle.DisciplinaDAO(Disciplina);

//        Manipulação DAO AlunoDisciplina
        DAOControle.AlunoDisciplinaDAO(pk_aluno, pk_disciplina, Disciplina);
        System.gc();
    }

    public static void ExibeConsulta(int seq)
    {
        DAOControle.RealizarConsulta(seq);
    }
}
