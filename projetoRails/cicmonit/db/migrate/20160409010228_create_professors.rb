class CreateProfessors < ActiveRecord::Migration
  def change
    create_table :professors do |t|
      t.string :nome
      t.integer :matricula
      # t.references :turma, index: true, foreign_key: true
      t.timestamps null: false
    end
  end
end
