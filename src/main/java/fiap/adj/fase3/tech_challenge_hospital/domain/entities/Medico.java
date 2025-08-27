package fiap.adj.fase3.tech_challenge_hospital.domain.entities;

public final class Medico {

    private Long id;

    private String nome;

    public Medico(String nome) {
        this.nome = nome;
    }

    public Medico(Long id, String nome) {
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
