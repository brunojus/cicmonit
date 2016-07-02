class Aluno < ActiveRecord::Base
  validates :nome, length: {minimum:5, maximum: 255 }
  validates :nome, presence: true
  validates :matricula, presence: true
  validates :IRA, presence: true
  validates :semestre, inclusion: { in: 1..10 }
  validates :semestre, presence: true
  validates :user_id, presence: true


  has_many :candidatos

  belongs_to :user

end
