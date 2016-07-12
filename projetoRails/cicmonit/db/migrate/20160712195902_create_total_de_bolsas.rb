class CreateTotalDeBolsas < ActiveRecord::Migration
  def change
    create_table :total_de_bolsas do |t|
      t.integer :QTE
      t.integer :QTE_alocadas
    end
  end
end
