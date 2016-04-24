class CreateHorarios < ActiveRecord::Migration
  def change
    create_table :horarios do |t|
      t.string :nome
      t.references :turma, index: true, foreign_key: true

      t.timestamps null: false
    end
  end
end
