class AddUserIdToDocente < ActiveRecord::Migration
  def change
    add_column :docentes, :user_id, :integer
  end
end
