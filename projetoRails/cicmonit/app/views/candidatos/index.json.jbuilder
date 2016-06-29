json.array!(@candidatos) do |candidato|
  json.extract! candidato, :id
  json.url candidato_url(candidato, format: :json)
end
