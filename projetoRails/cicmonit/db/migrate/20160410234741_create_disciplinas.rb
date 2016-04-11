class CreateDisciplinas < ActiveRecord::Migration
  def change
    create_table :disciplinas do |t|
      t.integer :codigo
      t.string :nome
      t.integer :departamento
      t.integer :creditos

      t.timestamps null: false
    end
  end
end
