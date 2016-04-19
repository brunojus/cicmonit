require 'test_helper'

class DisciplinasControllerTest < ActionController::TestCase
  test "should get showCampus" do
    get :showCampus
    assert_response :success
  end

  test "should get showDepartamentos" do
    get :showDepartamentos
    assert_response :success
  end

  test "should get showDisciplinas" do
    get :showDisciplinas
    assert_response :success
  end

  test "should get showTurmas" do
    get :showTurmas
    assert_response :success
  end

  test "should get showHorarios" do
    get :showHorarios
    assert_response :success
  end

  test "should get showProfessores" do
    get :showProfessores
    assert_response :success
  end

end
