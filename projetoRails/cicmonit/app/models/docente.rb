class Docente < ActiveRecord::Base
  validates :nome, length: {maximum: 255 }
  validates :nome, presence: true

  belongs_to :professor
  has_and_belongs_to_many :turmas
end
