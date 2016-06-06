class CreateDocentes < ActiveRecord::Migration
  def change
    create_table :docentes do |t|
      t.string :nome
      t.references :professor, index: true, foreign_key: true
    end
  end
end
