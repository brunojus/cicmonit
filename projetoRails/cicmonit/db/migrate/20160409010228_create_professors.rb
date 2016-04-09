class CreateProfessors < ActiveRecord::Migration
  def change
    create_table :professors do |t|
      t.string :nome
      t.integer :matricula

      t.timestamps null: false
    end
  end
end
