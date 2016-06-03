class CreateJoinTableProfessorTurma < ActiveRecord::Migration
  def change
    create_join_table :professors, :turmas do |t|
      t.index :professor_id
      t.index :turma_id
    end

    # add_index :professors_turmas, :professor_id
    # add_index :professors_turmas, :turma_id
  end
end
