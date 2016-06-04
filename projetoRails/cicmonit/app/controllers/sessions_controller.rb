class SessionsController < ApplicationController
  before_action :block_access, except: [:destroy]
    def create
    @dicente = Dicente.find_by(email: params[:session][:email].downcase)	
        if @dicente && @dicente.authenticate(params[:session][:password])
                   sign_in(@dicente)
                   redirect_to current_dicente
        else
                   render action: :new
        end
    end

    def destroy
    sign_out
    redirect_to root_url
    end
end
