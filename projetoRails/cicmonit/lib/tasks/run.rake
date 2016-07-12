namespace :run do
  desc "roda o getdb"
  task getdb: :environment do
	system "java -jar GetDB.jar"
  end

  desc "roda o alocabolsas"
  task aloca_bolsas: :environment do
	system "java -jar AlocaBolsas.jar"
  end

end
