class AlunosController < ApplicationController
  before_action :set_aluno, only: [:show, :edit, :update, :destroy]
  #before_action :authenticate_user! # nao precisa ir direto para a autenticacao antes de entrar
  # na parte de alunos pq as rotas do routes.rb ja direcionam para a pagina principal (a partir da qual
  # sera feito o login) quando o usuario nao esta autenticado


  # GET /alunos
  # GET /alunos.json
  def index
    # @alunos = Aluno.all
    @alunos = current_user.alunos
  end

  def listar
    @aluno = Aluno.where(user_id: current_user.id)
  end

  # GET /alunos/1
  # GET /alunos/1.json

  def show
  	render
  end

  # GET /alunos/new
  def new
    @aluno = Aluno.new
  end

  def insere
    @use = User.find(current_user.id)

    matricula = @use.email[0..8]

    system "python python.py #{matricula} #{$pass} #{@use.id}"

  end

  # GET /alunos/1/edit
  def edit
  end

  # POST /alunos
  # POST /alunos.json
  def create
    @aluno = Aluno.new(aluno_params)
    @aluno.user_id = current_user.id
    respond_to do |format|
      if @aluno.save
        format.html { redirect_to @aluno, notice: "Sucesso no cadastro do dicente #{@aluno.nome}." }
        format.json { render :show, status: :created, location: @aluno }
      else
        format.html { render :new }
        format.json { render json: @aluno.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /alunos/1
  # PATCH/PUT /alunos/1.json
  def update
    respond_to do |format|
      if @aluno.update(aluno_params)
        format.html { redirect_to @aluno, notice: "Sucesso no atualização do dicente #{@aluno.nome}."}
        format.json { render :show, status: :ok, location: @aluno }
      else
        format.html { render :edit }
        format.json { render json: @aluno.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /alunos/1
  # DELETE /alunos/1.json
  def destroy
    @aluno.destroy
    respond_to do |format|
      format.html { redirect_to alunos_url, notice: "Sucesso na exclusão do dicente #{@aluno.nome}." }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_aluno
      # @aluno = Aluno.find(params[:id])
      @aluno = current_user.alunos.find(params[:id])

    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def aluno_params
      params.require(:aluno).permit(:nome, :matricula, :semestre, :IRA)
    end
end
