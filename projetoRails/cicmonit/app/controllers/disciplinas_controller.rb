class DisciplinasController < ApplicationController
  def index
  end

  def show
	@disciplina = Disciplina.find(params[:id])
  end
end
