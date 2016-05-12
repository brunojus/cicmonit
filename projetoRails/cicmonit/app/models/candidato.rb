class Candidato < ActiveRecord::Base
  belongs_to :aluno
  # has_many :turmas, :through=>:turma_has_candidatos
  # has_many :turma_has_candidatos
  has_and_belongs_to_many :turmas
end
