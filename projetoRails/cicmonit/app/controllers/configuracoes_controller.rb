class ConfiguracoesController < ApplicationController
  def index
  end

  def rungetdb


	system "java -jar GetDB.jar"
	system "sqlite3 db/development.sqlite3 < db/db.sql"
  flash[:notice] = 'Banco de dados obtido com sucesso'

  end

  def runalocabolsas

    @total_de_bolsas = Configuracoe.all.first

    @total_de_bolsas.QTE = params[:total]

	system "sqlite3 db/development.sqlite3 '.dump \"turmas\" '> db/AlocaBolsas/turmas.sql"
	system "sqlite3 db/development.sqlite3 '.dump \"candidatos\" '> db/AlocaBolsas/candidatos.sql"
	system "sqlite3 db/development.sqlite3 '.dump \"candidatos_turmas\" '> db/AlocaBolsas/candidatos_turmas.sql"
	system "sqlite3 db/development.sqlite3 '.dump \"total_de_bolsas\" '> db/AlocaBolsas/total_de_bolsas.sql"
	system "cat db/AlocaBolsas/turmas.sql db/AlocaBolsas/candidatos.sql db/AlocaBolsas/candidatos_turmas.sql db/AlocaBolsas/total_de_bolsas.sql > db/AlocaBolsas/temp.sql"
	system "java -jar AlocaBolsas.jar"
	system "sqlite3 db/development.sqlite3 < db/AlocaBolsas/result.sql"
  flash[:notice] = "Total = #{@total_de_bolsas.QTE}."

  end
end
