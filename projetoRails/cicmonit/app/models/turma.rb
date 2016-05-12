class Turma < ActiveRecord::Base
  belongs_to :disciplina
  has_many :horarios
  has_many :professors, :through=>:professor_das_disciplinas
  has_many :professores_das_disciplinas
  has_many :cadidatos, :through=>:turma_has_candidatos
  has_many :turma_has_candidatos
end
