class DicentesController < ApplicationController
    before_action :authorize, except: [:new, :create]
    before_action :correct_dicente?, only: [:edit, :update, :destroy]
    def index
        @dicentes = Dicente.all
    end

    def show
        @dicente = Dicente.find(params[:id])
    end


    def new
        @dicente = Dicente.new
    end
    def create
        @dicente = Dicente.new(dicente_params)
        if @dicente.save
          redirect_to @dicente, notice: "Usuário foi criado com sucesso!"
          #tire o método de comentário quando criar o helper.
          #Usuário depois de cadastrar-se acessa o sistema automaticamente
          #sign_in(@dicente)
        else
          render action: :new
        end
    end


    def dicente_params
              params.require(:dicente).permit(:name, :email, :password, :password_confirmation, :matricula, :semestre, :ira)
    end

    def edit
      @dicente = Dicente.find(params[:id])
    end

    def update
      @dicente = Dicente.find(params[:id])
      if @dicente.update_attributes(dicente_params)
        redirect_to dicentes_path
      else
        render action: :edit
      end
    end

    def destroy
      @dicente = Dicente.find(params[:id])
      @dicente.destroy
      sign_out
      redirect_to root_path
    end
end
