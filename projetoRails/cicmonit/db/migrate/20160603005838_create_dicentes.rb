class CreateDicentes < ActiveRecord::Migration
  def change
    create_table :dicentes do |t|
      t.string :name
      t.string :email
      t.string :matricula,  limit:9
      t.float :ira
      t.integer :semestre

      # t.timestamps null: false
    end
  end
end
