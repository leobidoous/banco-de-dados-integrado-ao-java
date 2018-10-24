package MODEL;

public class Aluno
{
    private String cod_aluno;
    private String nome;
    private String data_nascimento;
    private String cpf;
    private String sexo;
    private String matricula;
    private String cod_disciplina;
    private String nome_disciplina;

    public String getNome()
    {
        return nome;
    }
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    public String getData_nascimento()
    {
        return data_nascimento;
    }
    public void setData_nascimento(String data_nascimento)
    {
        this.data_nascimento = data_nascimento;
    }
    public String getCpf()
    {
        return cpf;
    }
    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }
    public String getSexo()
    {
        return sexo;
    }
    public void setSexo(String sexo)
    {
        this.sexo = sexo;
    }
    public String getMatricula()
    {
        return matricula;
    }
    public void setMatricula(String matricula)
    {
        this.matricula = matricula;
    }
    public String getCod_disciplina() {
        return cod_disciplina;
    }
    public void setCod_disciplina(String cod_disciplina) {
        this.cod_disciplina = cod_disciplina;
    }
    public String getNome_disciplina() {
        return nome_disciplina;
    }
    public void setNome_disciplina(String nome_disciplina) {
        this.nome_disciplina = nome_disciplina;
    }
    public String getCod_aluno() {
        return cod_aluno;
    }
    public void setCod_aluno(String cod_aluno) {
        this.cod_aluno = cod_aluno;
    }
}