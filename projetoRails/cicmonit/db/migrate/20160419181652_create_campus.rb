class CreateCampus < ActiveRecord::Migration
  def change
    create_table :campus do |t|
      t.integer :codigo
      t.string :nome
    end
  end
end
