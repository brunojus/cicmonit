class CreateCandidatos < ActiveRecord::Migration
  def change
    create_table :candidatos do |t|
      t.string :mencao
      t.references :aluno, index: true, foreign_key: true
    end
  end
end
