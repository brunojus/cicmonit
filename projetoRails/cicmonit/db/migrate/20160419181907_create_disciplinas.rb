class CreateDisciplinas < ActiveRecord::Migration
  def change
    create_table :disciplinas do |t|
      t.integer :codigo
      t.string :nome
      t.references :departamento, index: true, foreign_key: true

      t.timestamps null: false
    end
  end
end
