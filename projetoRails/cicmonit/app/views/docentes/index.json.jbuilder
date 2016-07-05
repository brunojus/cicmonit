json.array!(@docentes) do |docente|
  json.extract! docente, :id, :nome
  json.url docente_url(docente, format: :json)
end
