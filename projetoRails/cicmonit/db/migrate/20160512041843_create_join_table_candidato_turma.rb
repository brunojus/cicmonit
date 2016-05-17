class CreateJoinTableCandidatoTurma < ActiveRecord::Migration
  def change
    create_join_table :candidatos, :turmas do |t|
      t.index :candidato_id
      t.index :turma_id
    end
    # add_index :candidatos_turmas, :candidato_id
    # add_index :candidatos_turmas, :turma_id
  end
end
