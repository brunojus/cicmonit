class AddCandidaturaToAluno < ActiveRecord::Migration
  def change
    add_column :alunos, :Candidatura, :integer
  end
end
