class User < ActiveRecord::Base
  # Include default devise modules. Others available are:
  # :confirmable, :lockable, :timeoutable and :omniauthable
  devise :database_authenticatable, :registerable,
         :recoverable, :rememberable, :trackable, :validatable, :confirmable

         has_many :alunos

         has_many :docentes

         VALID_EMAIL_FORMAT= /\A(?:[-0-9]+)@((?:[-a-u]+\.)+[a-z]{2,})\Z/i
        validates :email,  length: {maximum: 260}, uniqueness: {case_sensitive: false}

end
