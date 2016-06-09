class AddUserIdToAluno < ActiveRecord::Migration
  def change
    add_column :alunos, :user_id, :integer
  end
end
