class DisciplinasController < ApplicationController
  def showCampus
  end

  def showDepartamentos
	@departamento = Campu.find(params[:id])
  end

  def showDisciplinas
	@disciplina = Departamento.find(params[:id])
  end

  def showTurmas
	@turma = Disciplina.find(params[:id])
  end

  def showTurma
	@turma = Turma.find(params[:id])
  end

  def showHorarios
	@horarios = Turma.find(params[:id])
  end

  def showCandidatos

  end

  def showProfessores
  end
end
