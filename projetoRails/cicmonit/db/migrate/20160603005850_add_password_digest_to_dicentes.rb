class AddPasswordDigestToDicentes < ActiveRecord::Migration
  def change
    add_column :dicentes, :password_digest, :string
  end
end
