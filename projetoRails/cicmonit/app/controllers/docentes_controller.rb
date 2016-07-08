class DocentesController < ApplicationController
  before_action :set_docente, only: [:show, :edit, :update, :destroy]
  #before_action :authenticate_user! # nao precisa ir direto para a autenticacao antes de entrar
  # na parte de docentes pq as rotas do routes.rb ja direcionam para a pagina principal (a partir da qual
  # sera feito o login) quando o usuario nao esta autenticado


  # GET /docentes
  # GET /docentes.json
  def index
    # @docentes = Docente.all
    @docentes = current_user.docentes
  end

  # GET /docentes/1
  # GET /docentes/1.json

  def show
  	render
  end

  # GET /docentes/new
  def new
    @docente = Docente.new
  end

  # GET /docentes/1/edit
  def edit
  end

  # POST /docentes
  # POST /docentes.json
  def create
    @docente = Docente.where("nome = ?", docente_params["nome"])
    User.find(current_user.id).docentes << @docente
    respond_to do |format|
        format.html { redirect_to :edit => 'localhost:3000/docentes'}
        format.json { render :show, status: :created, location: @docente }
    end
  end

  # PATCH/PUT /docentes/1
  # PATCH/PUT /docentes/1.json
  def update
    respond_to do |format|
      if @docente.update(docente_params)
        format.html { redirect_to @docente, notice: "Sucesso no atualização do docente #{@docente.nome}."}
        format.json { render :show, status: :ok, location: @docente }
      else
        format.html { render :edit }
        format.json { render json: @docente.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /docentes/1
  # DELETE /docentes/1.json
  def destroy
    @docente.destroy
    respond_to do |format|
      format.html { redirect_to docentes_url, notice: "Sucesso na exclusão do docente #{@docente.nome}." }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_docente
      # @docente = Docente.find(params[:id])
      @docente = current_user.docentes.find(params[:id])

    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def docente_params
      params.require(:docente).permit(:nome)
    end
end
