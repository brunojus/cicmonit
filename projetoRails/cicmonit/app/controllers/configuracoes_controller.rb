class ConfiguracoesController < ApplicationController
  def index
  end

  def rungetdb
	system "java -jar GetDB.jar"	
	system "sqlite3 db/development.sqlite3 < db/db.sql"
  end

  def runalocabolsas
	system "sqlite3 db/development.sqlite3 .dump > db/AlocaBolsas/temp.sql"
	system "java -jar AlocaBolsas.jar"
	system "sqlite3 db/development.sqlite3 < db/AlocaBolsas/result.sql"
  end
end
