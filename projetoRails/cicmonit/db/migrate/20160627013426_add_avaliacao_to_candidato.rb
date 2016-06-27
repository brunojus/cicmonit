class AddAvaliacaoToCandidato < ActiveRecord::Migration
  def change
    add_column :candidatos, :avaliacao, :integer
  end
end
