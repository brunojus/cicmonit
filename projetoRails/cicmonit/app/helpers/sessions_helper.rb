module SessionsHelper
   def formata_email(email)
    email_formatado = "#{email}" #campo de matricula que o usario esta preechendo
    email_formatado << "@aluno.unb.br" #endereco de email que vem automaticamente logo depois do campo de matricula
  end
end
