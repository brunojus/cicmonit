# Be sure to restart your server when you modify this file.

# Configure sensitive parameters which will be filtered from the log file.
#Aqui não deveria ser encrypted_password??
Rails.application.config.filter_parameters += [:password]
