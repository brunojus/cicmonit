class AddObrigatoriaToTurmas < ActiveRecord::Migration
  def change
    add_column :turmas, :obrigatoria, :integer
  end
end
