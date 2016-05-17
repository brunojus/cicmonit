class CreateAlunos < ActiveRecord::Migration
  def change
    create_table :alunos do |t|
      t.string :nome, limit: 225, null: false
      t.string :matricula, limit:9, null: false
      t.integer :semestre, null: false
      t.float :IRA, null: false, default: 5.0

      t.timestamps null: false
    end
  end
end
