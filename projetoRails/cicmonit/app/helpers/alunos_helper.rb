module AlunosHelper
  def formata_matricula(n_matricula)
    mat_formatada = ""
    mat_formatada << n_matricula[0..1]
    mat_formatada << "/"
    mat_formatada << n_matricula[2..8]
  end

  def formata_semestre(n_semestre)
    sem_formatado = "#{n_semestre}"
    sem_formatado << "º"
  end
end
