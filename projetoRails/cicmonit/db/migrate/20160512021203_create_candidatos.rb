class CreateCandidatos < ActiveRecord::Migration
  def change
    create_table :candidatos do |t|
      t.integer :codigo_candidato
      t.string :mencao

      t.timestamps null: false
    end
  end
end
