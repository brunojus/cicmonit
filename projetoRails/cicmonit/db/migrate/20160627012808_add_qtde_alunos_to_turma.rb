class AddQtdeAlunosToTurma < ActiveRecord::Migration
  def change
    add_column :turmas, :QtdeAlunos, :integer
  end
end
