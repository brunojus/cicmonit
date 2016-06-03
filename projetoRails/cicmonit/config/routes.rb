Rails.application.routes.draw do

  get 'sessions/new'

  get 'dicentes/new'

  get 'docentes/index'

  get 'docentes/show'

  resources :alunos
  # root :to =>"alunos#FrontPage"

  devise_for :professors
  resources :professors
  #root :to =>"professors#index"


  # unauthenticated do
	# #alunos#index
  #     root :to =>"alunos#FrontPage", as: :unauthenticated_root
  #
  # end
  #
  # authenticated :professors do
  #   root  :to=>  'disciplinas#showCampus',  as: :authenticated_root
  # end


  get 'disciplinas/showCampus'

  get 'disciplinas/showDepartamentos'

  get 'disciplinas/showDisciplinas'

  get 'disciplinas/showTurmas'

  get 'disciplinas/showHorarios'

  get 'disciplinas/showProfessores'

	resources:disciplina
	# root to:"disciplinas#showCampus"

  resources :dicentes
  get    'sign_in'   => 'sessions#new'
  post   'sign_in'   => 'sessions#create'
  delete 'sign_out'  => 'sessions#destroy'

  root 'alunos#FrontPage'

  # The priority is based upon order of creation: first created -> highest priority.
  # See how all your routes lay out with "rake routes".

  # You can have the root of your site routed with "root"
  # root 'welcome#index'

  # Example of regular route:
  #   get 'products/:id' => 'catalog#view'

  # Example of named route that can be invoked with purchase_url(id: product.id)
  #   get 'products/:id/purchase' => 'catalog#purchase', as: :purchase

  # Example resource route (maps HTTP verbs to controller actions automatically):
  #   resources :products

  # Example resource route with options:
  #   resources :products do
  #     member do
  #       get 'short'
  #       post 'toggle'
  #     end
  #
  #     collection do
  #       get 'sold'
  #     end
  #   end

  # Example resource route with sub-resources:
  #   resources :products do
  #     resources :comments, :sales
  #     resource :seller
  #   end

  # Example resource route with more complex sub-resources:
  #   resources :products do
  #     resources :comments
  #     resources :sales do
  #       get 'recent', on: :collection
  #     end
  #   end

  # Example resource route with concerns:
  #   concern :toggleable do
  #     post 'toggle'
  #   end
  #   resources :posts, concerns: :toggleable
  #   resources :photos, concerns: :toggleable

  # Example resource route within a namespace:
  #   namespace :admin do
  #     # Directs /admin/products/* to Admin::ProductsController
  #     # (app/controllers/admin/products_controller.rb)
  #     resources :products
  #   end
end
