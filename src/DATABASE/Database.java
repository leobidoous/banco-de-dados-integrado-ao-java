package DATABASE;

import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import MODEL.*;

public class Database
{
    private static Connection connection = null;
    private static ResultSet resultset = null;

    public void Connect()
    {
        String user = "root";
        String server = "jdbc:mysql://127.0.0.1:3307/Universidade";
        String  password = "";
        String driver = "com.mysql.jdbc.Driver";

        try
        {
            Class.forName(driver);
            Database.connection = DriverManager.getConnection(server, user, password);
            Database.connection.createStatement();
        }
        catch(Exception e)
        {
            System.out.println("ERROR: "+e.getMessage());
        }
    }

    public boolean isConnected()
    {
        if(connection != null) return true;
        else return false;
    }

    public void Close()
    {
        try
        {
            connection.close();
        }
        catch(Exception e)
        {
            System.out.println("ERROR: "+e.getMessage());
        }
    }

    public static ArrayList<Integer> InserirAluno(ArrayList<Aluno> aluno)
    {
        ArrayList<Integer> pk = new ArrayList<>();
        try
        {
            for (int i = 0; i < aluno.size(); i++) {
                String sql = null;
                sql = "insert into Alunos (nome_aluno, cpf, sexo, data_nascimento, matricula) values (?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setString(1, aluno.get(i).getNome());
                    preparedStatement.setString(2, aluno.get(i).getCpf());
                    preparedStatement.setString(3, aluno.get(i).getSexo());
                    preparedStatement.setString(4, aluno.get(i).getData_nascimento());
                    preparedStatement.setString(5, aluno.get(i).getMatricula());
                    preparedStatement.executeUpdate();
                    resultset = preparedStatement.getGeneratedKeys();
                    if (resultset.next()) {
                        int id = resultset.getInt(1);
                        pk.add(id);
                    }
                }
                catch (Exception e)
                {
                    System.out.println(e);
                    return null;
                }
            }
            String msg = "Alunos inseridos com sucesso!";
            JOptionPane.showMessageDialog(null,msg,"Banco de Dados",1);
            return pk;
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,e,"Inserir Alunos",1);
            return null;
        }
    }


    public static ArrayList<ArrayList> InserirDisciplina(ArrayList<Disciplina> disciplina)
    {
        ArrayList<Integer> pk = new ArrayList<>();
        ArrayList<ArrayList> DisciplinaCod = new ArrayList<>();

        try
        {
//            Verificar se a disciplina já foi inserida no banco
            ArrayList<String> Disciplinas = new ArrayList<>();

            Disciplinas.add((String) disciplina.get(0).getNome().get(0));

            for (int i = 0; i < disciplina.size(); i++){
                for (int j = 0; j < disciplina.get(i).getNome().size(); j++){
                    int cont = 0;
                    for (int k = 0; k < Disciplinas.size(); k++){
                        if (Disciplinas.get(k).equals(disciplina.get(i).getNome().get(j))){
                            cont += 1;
                        }
                        else if(k+1 == Disciplinas.size() && cont == 0){
                            Disciplinas.add((String) disciplina.get(i).getNome().get(j));
                        }
                    }
                }
            }

////            Inserir disciplina no banco
            for (int i = 0; i < Disciplinas.size(); i++) {
                String sql = null;
                sql = "INSERT INTO Disciplinas (nome_disciplina) VALUES (?);";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setString(1, Disciplinas.get(i));
                    preparedStatement.executeUpdate();
                    resultset = preparedStatement.getGeneratedKeys();
                    if (resultset.next()) {
                        int id = resultset.getInt(1);
                        pk.add(id);
                    }
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }

            DisciplinaCod.add(pk);
            DisciplinaCod.add(Disciplinas);

            String msg = "Disciplinas inseridas com sucesso!";
            JOptionPane.showMessageDialog(null,msg,"Banco de Dados",1);
            return DisciplinaCod;
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e,"Inserir Disciplinas",0);
            return null;
        }
    }

    public static void InserirAlunoDiciplinas(ArrayList<Integer> pk_aluno, ArrayList<ArrayList> pk_disciplina, ArrayList<Disciplina> disciplina)
    {
        try
        {
            for (int i = 0; i < pk_aluno.size(); i++)
            {
                for (int j = 0; j < disciplina.get(i).getNome().size(); j++)
                {
                    for (int k = 0; k < pk_disciplina.get(0).size(); k++)
                    {
                        if (disciplina.get(i).getNome().get(j).equals(pk_disciplina.get(1).get(k)))
                        {
                            String sql = null;
                            sql = "INSERT INTO Aluno_Disciplinas  (cod_aluno, cod_disciplina) values (?, ?);";
                            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
                            {
                                preparedStatement.setInt(1, pk_aluno.get(i));
                                preparedStatement.setInt(2, (Integer) pk_disciplina.get(0).get(k));
                                preparedStatement.executeUpdate();
                            }
                            catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                }
            }
            String msg = "Disciplinas vinculadas aos alunos com sucesso!";
            JOptionPane.showMessageDialog(null,msg,"Banco de Dados",1);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static String seq(int seq, int cod_aluno)
    {
        String zeros = "";
        if(cod_aluno<10)
        {
            for (int j = 0; j < seq-1; j++)
            {
                zeros += "0";
            }
        }
        else if(cod_aluno<100)
        {
            for (int j = 0; j < seq-2; j++)
            {
                zeros += "0";
            }
        }
        return zeros+cod_aluno+"\t\t";
    }

    public static void RealizarConsulta(int seq)
    {
        // TODO Auto-generated method stub
        ArrayList<Aluno> tab = new ArrayList<>();
        try
        {
            String sql = null;
            sql = "select Alunos.*, Disciplinas.* from Aluno_Disciplinas " +
                    "inner join Alunos on Aluno_Disciplinas.cod_aluno = Alunos.cod_aluno " +
                    "inner join Disciplinas on Aluno_Disciplinas.cod_disciplina = Disciplinas.cod_disciplina " +
                    "order by cod_aluno;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql))
            {
                resultset = preparedStatement.executeQuery();
                while (resultset.next()) {
                    Aluno Aluno = new Aluno();
                    Aluno.setCod_aluno(resultset.getString(1));
                    Aluno.setNome(resultset.getString(2));
                    Aluno.setCpf(resultset.getString(3));
                    Aluno.setSexo(resultset.getString(4));
                    Aluno.setData_nascimento(resultset.getString(5));
                    Aluno.setMatricula(resultset.getString(6));
                    Aluno.setCod_disciplina(resultset.getString(7));
                    Aluno.setNome_disciplina(resultset.getString(8));

                    tab.add(Aluno);
                }
                //realiza a exibição dos alunos
                String title = "\nSeq\t\tMatr.\tNome\n";
                String disc = "";

                for(int i = 0; i < tab.size(); i++)
                {
                    if (i+1 == tab.size())
                    {
                        if(tab.get(i-1).getCod_aluno().equals(tab.get(i).getCod_aluno()))
                        {
                            disc += tab.get(i).getNome_disciplina()+" ";
                        }
                        else
                        {
                            String date = "";
                            date += title+seq(seq, Integer.parseInt(tab.get(i).getCod_aluno()))+tab.get(i).getMatricula()+"\t"
                                    +tab.get(i).getNome()+tab.get(i).getCpf()+" "+tab.get(i).getSexo()
                                    +tab.get(i).getData_nascimento()+" " + disc + tab.get(i).getNome_disciplina();
                            disc = "";
                            System.out.println(date);
                        }
                    }
                    else if(tab.get(i+1).getCod_aluno().equals(tab.get(i).getCod_aluno()))
                    {
                        disc += tab.get(i).getNome_disciplina()+" ";
                    }
                    else
                    {

                        String date = "";
                        date += title+seq(seq, Integer.parseInt(tab.get(i).getCod_aluno()))+tab.get(i).getMatricula()+"\t"
                                +tab.get(i).getNome()+tab.get(i).getCpf()+" "+tab.get(i).getSexo()
                                +tab.get(i).getData_nascimento()+" " + disc + tab.get(i).getNome_disciplina();
                        disc = "";
                        System.out.println(date);
                    }

                }
                System.out.println(Integer.parseInt(tab.get(0).getCod_aluno()));
                //fim das exibições dos alunos
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
