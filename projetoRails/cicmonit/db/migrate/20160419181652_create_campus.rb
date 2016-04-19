class CreateCampus < ActiveRecord::Migration
  def change
    create_table :campus do |t|
      t.integer :codigo
      t.string :nome

      t.timestamps null: false
    end
  end
end
