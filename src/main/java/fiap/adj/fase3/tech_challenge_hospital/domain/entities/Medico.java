package fiap.adj.fase3.tech_challenge_hospital.domain.entities;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.MedicoDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.MedicoRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.application.mappers.MedicoMapper;

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

    public static Medico regraAtualizar(MedicoDto dto, MedicoRequestDto request) {

        var entity = MedicoMapper.converterRequestParaEntity(request);
        entity.setId(dto.id());
        return entity;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
