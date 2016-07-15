from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time
import sys
import os
user = sys.argv[1]
passw = sys.argv[2]
uid = sys.argv[3]

browser = webdriver.Firefox()
browser.get("https://wwwsec.serverweb.unb.br/matriculaweb/graduacao/sec/login.aspx")
#time.sleep(1)
username = browser.find_element_by_name('inputMatricula')
password = browser.find_element_by_name('inputSenha')
username.send_keys("100111483")
password.send_keys("aruu1397")
password.send_keys("\n")
time.sleep(1)

browser.get("https://wwwsec.serverweb.unb.br/matriculaweb/graduacao/sec/qr.aspx")

nome = browser.find_element_by_id('lblAluNome')
matricula = browser.find_element_by_id('lblAlumatricula')
ira = browser.find_element_by_id('lblIRA')
semestre = browser.find_element_by_id('lblPosFluxo')

print u"Nome: {nome}, \tMatricula: {matricula}, \tIRA: {ira}, \tSemetre: {semestre}".format(
  nome=nome.text,
  matricula=matricula.text,
  ira=ira.text,
  semestre=semestre.text
)


os.system(u"sqlite3 db/development.sqlite3 \"insert into \"alunos\"(nome,matricula,semestre,ira,user_id) VALUES('{nome}','{mat}',{semestre},{ira},{userid});\"".format(
  nome=nome.text,
  mat=user,
  ira=ira.text,
  semestre=semestre.text,
  userid=uid
))

os.system("clear")


browser.quit()
