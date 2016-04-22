class Aluno < ActiveRecord::Base
  validates :nome, length: {minimum:5, maximum: 50 }
  validates :nome, presence: true
  validates :matricula, length: { minimum: 9 }
  validates :matricula, presence: true
  validates :IRA, inclusion: { in: 0..5 }
  validates :IRA, presence: true
  validates :semestre, inclusion: { in: 1..10 }
  validates :semestre, presence: true
end
