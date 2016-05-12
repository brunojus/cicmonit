class CreateTurmaHasCandidatos < ActiveRecord::Migration
  def change
    create_table :turma_has_candidatos do |t|

      t.timestamps null: false
    end
  end
end
