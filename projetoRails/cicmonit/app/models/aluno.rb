class Aluno < ActiveRecord::Base
  validates_presence_of :nome
  validates_presence_of :matricula
  validates_presence_of :semestre
  validates_presence_of :IRA
end
