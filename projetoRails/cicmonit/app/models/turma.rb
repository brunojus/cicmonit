class Turma < ActiveRecord::Base
  belongs_to :disciplina
  has_many :horarios
  
  has_and_belongs_to_many :docentes

  has_and_belongs_to_many :cadidatos
end
