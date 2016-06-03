module SessionsHelper
    def sign_in(dicente_id)
        session[:dicente_id] = @dicente.id
    end
    def current_dicente
        @current_dicente ||= Dicente.find_by(id: session[:dicente_id])
    end
    def block_access
        if current_dicente.present?
                   redirect_to dicentes_path
        end
    end
    def logged_in?
        !current_dicente.nil?
    end
    def sign_out
        session.delete(:dicente_id)
        @current_dicente = nil
    end
end
