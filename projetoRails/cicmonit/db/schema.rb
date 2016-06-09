# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20160609035234) do

  create_table "alunos", force: :cascade do |t|
    t.string  "nome",      limit: 225,               null: false
    t.string  "matricula", limit: 9,                 null: false
    t.integer "semestre",                            null: false
    t.float   "IRA",                   default: 5.0, null: false
    t.integer "user_id"
  end

  create_table "campus", force: :cascade do |t|
    t.integer "codigo"
    t.string  "nome"
  end

  create_table "candidatos", force: :cascade do |t|
    t.string  "mencao"
    t.integer "aluno_id"
  end

  add_index "candidatos", ["aluno_id"], name: "index_candidatos_on_aluno_id"

  create_table "candidatos_turmas", id: false, force: :cascade do |t|
    t.integer "candidato_id", null: false
    t.integer "turma_id",     null: false
  end

  add_index "candidatos_turmas", ["candidato_id"], name: "index_candidatos_turmas_on_candidato_id"
  add_index "candidatos_turmas", ["turma_id"], name: "index_candidatos_turmas_on_turma_id"

  create_table "departamentos", force: :cascade do |t|
    t.integer "codigo"
    t.string  "sigla"
    t.string  "nome"
    t.integer "campu_id"
  end

  add_index "departamentos", ["campu_id"], name: "index_departamentos_on_campu_id"

  create_table "dicentes", force: :cascade do |t|
    t.string  "name"
    t.string  "email"
    t.string  "matricula",       limit: 9
    t.float   "ira"
    t.integer "semestre"
    t.string  "password_digest"
  end

  create_table "disciplinas", force: :cascade do |t|
    t.integer "codigo"
    t.string  "nome"
    t.integer "departamento_id"
  end

  add_index "disciplinas", ["departamento_id"], name: "index_disciplinas_on_departamento_id"

  create_table "docentes", force: :cascade do |t|
    t.string  "nome"
    t.integer "professor_id"
  end

  add_index "docentes", ["professor_id"], name: "index_docentes_on_professor_id"

  create_table "docentes_turmas", id: false, force: :cascade do |t|
    t.integer "docente_id", null: false
    t.integer "turma_id",   null: false
  end

  add_index "docentes_turmas", ["docente_id"], name: "index_docentes_turmas_on_docente_id"
  add_index "docentes_turmas", ["turma_id"], name: "index_docentes_turmas_on_turma_id"

  create_table "horarios", force: :cascade do |t|
    t.string  "nome"
    t.integer "turma_id"
  end

  add_index "horarios", ["turma_id"], name: "index_horarios_on_turma_id"

  create_table "professors", force: :cascade do |t|
    t.string   "nome"
    t.integer  "matricula"
    t.string   "email",                  default: "", null: false
    t.string   "encrypted_password",     default: "", null: false
    t.string   "reset_password_token"
    t.datetime "reset_password_sent_at"
    t.datetime "remember_created_at"
    t.integer  "sign_in_count",          default: 0,  null: false
    t.datetime "current_sign_in_at"
    t.datetime "last_sign_in_at"
    t.string   "current_sign_in_ip"
    t.string   "last_sign_in_ip"
  end

  add_index "professors", ["email"], name: "index_professors_on_email", unique: true
  add_index "professors", ["reset_password_token"], name: "index_professors_on_reset_password_token", unique: true

  create_table "turmas", force: :cascade do |t|
    t.string  "nome"
    t.integer "disciplina_id"
  end

  add_index "turmas", ["disciplina_id"], name: "index_turmas_on_disciplina_id"

  create_table "users", force: :cascade do |t|
    t.string   "email",                  default: "", null: false
    t.string   "encrypted_password",     default: "", null: false
    t.string   "reset_password_token"
    t.datetime "reset_password_sent_at"
    t.datetime "remember_created_at"
    t.integer  "sign_in_count",          default: 0,  null: false
    t.datetime "current_sign_in_at"
    t.datetime "last_sign_in_at"
    t.string   "current_sign_in_ip"
    t.string   "last_sign_in_ip"
    t.datetime "created_at",                          null: false
    t.datetime "updated_at",                          null: false
  end

  add_index "users", ["email"], name: "index_users_on_email", unique: true
  add_index "users", ["reset_password_token"], name: "index_users_on_reset_password_token", unique: true

end
