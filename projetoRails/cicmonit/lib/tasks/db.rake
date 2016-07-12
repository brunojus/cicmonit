namespace :db do
  desc "Cria o temp.sql"
  task backup_to_temp: :environment do
	system "sqlite3 db/development.sqlite3 .dump > db/AlocaBolsas/temp.sql"
  end

  desc "Inicializa o banco com dados do mw"
  task restore_from_getdb: :environment do
	system "sqlite3 db/development.sqlite3 < db/db.sql"
  end

  desc "finaliza o processamento das bolsas"
  task restore_from_aloca_bolsas: :environment do
	system "sqlite3 db/development.sqlite3 < db/AlocaBolsas/result.sql"
  end

end
