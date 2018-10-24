package CONTROLLER;

import java.util.ArrayList;
import MODEL.Disciplina;
import UTILITY.Utilidade;

public class DisciplinaControle
{
    public ArrayList<Disciplina> DisciplinasAlunos()
    {
        ArrayList<Disciplina> DisciplinasAlunos = new ArrayList<Disciplina>();
        DisciplinasAlunos = Utilidade.DisciplinasAlunos();
        return DisciplinasAlunos;
    }
}