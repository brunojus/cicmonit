class Turma < ActiveRecord::Base
  belongs_to :disciplina
  has_many :horarios
  has_many :professores_das_disciplinas
end
