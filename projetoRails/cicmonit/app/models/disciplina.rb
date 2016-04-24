class Disciplina < ActiveRecord::Base
  belongs_to :departamento
  has_many :turmas
end
