class Professor < ActiveRecord::Base
  # Include default devise modules. Others available are:
  # :confirmable, :lockable, :timeoutable and :omniauthable
  ##devise :database_authenticatable, :registerable,
  ##       :recoverable, :rememberable, :trackable, :validatable
    validates :matricula, uniqueness: true


  ##has_one :docente

end
