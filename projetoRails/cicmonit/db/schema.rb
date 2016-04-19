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

ActiveRecord::Schema.define(version: 20160419182151) do

  create_table "campus", force: :cascade do |t|
    t.integer  "codigo"
    t.string   "nome"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "departamentos", force: :cascade do |t|
    t.integer  "codigo"
    t.string   "sigla"
    t.string   "nome"
    t.integer  "campu_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  add_index "departamentos", ["campu_id"], name: "index_departamentos_on_campu_id"

  create_table "disciplinas", force: :cascade do |t|
    t.integer  "codigo"
    t.string   "nome"
    t.integer  "departamento_id"
    t.datetime "created_at",      null: false
    t.datetime "updated_at",      null: false
  end

  add_index "disciplinas", ["departamento_id"], name: "index_disciplinas_on_departamento_id"

  create_table "horarios", force: :cascade do |t|
    t.string   "nome"
    t.integer  "turma_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  add_index "horarios", ["turma_id"], name: "index_horarios_on_turma_id"

  create_table "professors", force: :cascade do |t|
    t.string   "nome"
    t.integer  "turma_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  add_index "professors", ["turma_id"], name: "index_professors_on_turma_id"

  create_table "turmas", force: :cascade do |t|
    t.string   "nome"
    t.integer  "disciplina_id"
    t.datetime "created_at",    null: false
    t.datetime "updated_at",    null: false
  end

  add_index "turmas", ["disciplina_id"], name: "index_turmas_on_disciplina_id"

end
