class CreateDepartamentos < ActiveRecord::Migration
  def change
    create_table :departamentos do |t|
      t.integer :codigo
      t.string :sigla
      t.string :nome
      t.references :campu, index: true, foreign_key: true

      t.timestamps null: false
    end
  end
end
