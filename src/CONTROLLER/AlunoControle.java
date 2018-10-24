package CONTROLLER;

import java.util.ArrayList;
import MODEL.Aluno;
import UTILITY.Utilidade;

public class AlunoControle
{
    public ArrayList<Aluno> DadosAlunos()
    {
        ArrayList<Aluno> DadosAlunos = new ArrayList<Aluno>();
        DadosAlunos = Utilidade.DadosAlunos();
        return DadosAlunos;
    }
}
