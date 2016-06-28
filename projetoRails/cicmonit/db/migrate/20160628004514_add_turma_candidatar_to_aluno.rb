class AddTurmaCandidatarToAluno < ActiveRecord::Migration
  def change
    add_column :alunos, :TurmasCandidatar, :string
  end
end
