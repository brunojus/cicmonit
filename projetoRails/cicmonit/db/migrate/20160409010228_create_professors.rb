class CreateProfessors < ActiveRecord::Migration
  def change
    create_table :professors do |t|
      t.string :nome
      t.integer :matricula
      # t.references :turma, index: true, foreign_key: true
    end
  end
end
