package fiap.adj.fase3.tech_challenge_hospital.domain.entities;

public final class Paciente {

    private Long id;

    private String nome;

    public Paciente(String nome) {
        this.nome = nome;
    }

    public Paciente(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
