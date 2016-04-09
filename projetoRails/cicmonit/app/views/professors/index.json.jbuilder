json.array!(@professors) do |professor|
  json.extract! professor, :id, :nome, :matricula
  json.url professor_url(professor, format: :json)
end
