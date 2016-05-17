class Turma < ActiveRecord::Base
  belongs_to :disciplina
  has_many :horarios
  # has_many :professors, :through=>:professor_das_disciplinas
  has_many :professores_das_disciplinas
  #has_and_belongs_to_many :professors
  # has_many :cadidatos, :through=>:turma_has_candidatos
  # has_many :turma_has_candidatos
  has_and_belongs_to_many :cadidatos
end
