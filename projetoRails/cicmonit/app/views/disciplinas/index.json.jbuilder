json.array!(@disciplinas) do |disciplina|
  json.extract! disciplina, :id, :codigo, :nome, :departamento, :creditos
  json.url disciplina_url(disciplina, format: :json)
end
