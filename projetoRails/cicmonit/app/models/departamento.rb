class Departamento < ActiveRecord::Base
  belongs_to :campu
  has_many :disciplinas
end
