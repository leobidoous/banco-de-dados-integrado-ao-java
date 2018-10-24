package UTILITY;

import java.util.ArrayList;
import MODEL.Aluno;
import MODEL.Disciplina;
import FILE.File;
import CONTROLLER.ValidadorCPF;
import org.jetbrains.annotations.NotNull;

public class Utilidade
{
    public static String ValidaSexo(@NotNull String sexo)
    {
        if (sexo.equals("1"))sexo = "Masc";
        else if (sexo.equals("2")) sexo = "Fem ";
        else sexo = "Inválido*";
        return sexo;
    }

    public static String ValidaDataNascimento1(String data_nascimento)
    {
        data_nascimento = data_nascimento.substring(0, 2)+
                "/"+data_nascimento.substring(2, 4)+
                "/"+data_nascimento.substring(4, 8);
        return data_nascimento;
    }

    public static String ValidaDataNascimento2(String data_nascimento)
    {
        data_nascimento = data_nascimento.substring(4, 8)+
                "-"+data_nascimento.substring(2, 4)+
                "-"+data_nascimento.substring(0, 2);
        return data_nascimento;
    }

    public static String FormataNome(String nome)
    {
        nome = nome.toLowerCase();
        for (int i = 0; i < nome.length(); i++)
        {
            if(i == 0)
            {
                String aux = nome.substring(i, i+1).toUpperCase();
                nome = aux.concat(nome.substring(i+1));
            }
            if(nome.charAt(i) == ' ' && nome.charAt(i-1) != ' ')
            {
                String aux1 = nome.substring(0, i+1);
                String aux2 = nome.substring(i+1, i+2).toUpperCase();
                String aux3 = nome.substring(i+2);
                nome = aux1.concat(aux2.concat(aux3));
            }
        }
        return nome;
    }

    public static ArrayList FormataDisciplinas(@NotNull String disc)
    {
        ArrayList<String> DisciplinasAluno = new ArrayList<>();
        if(disc.length() != 0)
        {
            for (int j = 0; j < disc.length(); j+=7)
            {
                String disciplinas = null;
                disciplinas = disc.substring(j, j+7);
                DisciplinasAluno.add(disciplinas);
            }
        }
        else
        {
            DisciplinasAluno.add("SEM DISCIPLINAS");
        }
        return DisciplinasAluno;
    }

    public static ArrayList<Aluno> DadosAlunos()
    {
        ArrayList<Aluno> DadosAluno = new ArrayList<Aluno>();
        ArrayList<String> data = File.LerArquivo();
        for (int i = 0; i < data.size(); i++)
        {
            Aluno aluno = new Aluno();
            aluno.setMatricula(data.get(i).substring(0, 4));
            aluno.setNome(FormataNome(data.get(i).substring(4, 29)));
            if(ValidadorCPF.isValidCPF(data.get(i).substring(29, 40)))
            {
                aluno.setCpf(data.get(i).substring(29, 40)+' ');
            }
            else
            {
                aluno.setCpf(data.get(i).substring(29, 40)+'*');
            }
            aluno.setSexo(ValidaSexo(data.get(i).substring(40, 41)));
            aluno.setData_nascimento(ValidaDataNascimento2(data.get(i).substring(41, 49)));
            DadosAluno.add(aluno);
        }
        return DadosAluno;
    }

    public static ArrayList<Disciplina> DisciplinasAlunos()
    {
        ArrayList<Disciplina> DisciplinasAlunos = new ArrayList<>();
        ArrayList<String> arq = File.LerArquivo();

        for (int i = 0; i < arq.size(); i++)
        {
            Disciplina Disciplinas = new Disciplina();
            Disciplinas.setNome(FormataDisciplinas(arq.get(i).substring(49)));
            DisciplinasAlunos.add(Disciplinas);
        }
        return DisciplinasAlunos;
    }

}