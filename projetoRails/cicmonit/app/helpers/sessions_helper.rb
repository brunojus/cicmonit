module SessionsHelper

   def formata_email(email)
   	tamanho_matricula = 8#indices vao de 0 a size -1
   	if email.length < 13 then # basicamente serve para ver se o campo de email esta vazio. 
   		#Se estiver, vai preencher com @aluno.unb.br
    	#email_formatado = "#{email}" #campo de matricula que o usario esta preechendo

    	email_formatado = "@aluno.unb.br" #endereco de email que vem automaticamente logo depois do campo de matricula
  	elsif email.index("@")== nil # se nao tiver @
  		email_formatado = "@aluno.unb.br" #endereco de email que vem automaticamente logo depois do campo de matricula
  	elsif email[email.index("@")..email.length] != "@aluno.unb.br" # verifica se o dominio eh @aluno.unb.br
  	#codigo acima pega os indices de email(string) desde o indice do '@' ate o tamanho maximo da palavra 
  		if email.index("@")-1 <= tamanho_matricula then # se o campo de matricula digitado tem tamanho menor
  			#ou igual ao tamanho da matricula entao pegamos tudo antes do @. Sena pegamos os 9 primeiros caracteres
	  		email_formatado = email[0..(email.index("@")-1)] + "@aluno.unb.br" # pega tudo que o usuario digitou
	  		# ate o primeiro @ e concatena o resultado com "@aluno.unb.br"
  		else
  			email_formatado = email[0..8] + "@aluno.unb.br"
  		end 
  	else # email  tem o dominio da unb
  		if email.index("@")-1 <= tamanho_matricula then # se o campo de matricula digitado tem tamanho menor
  			#ou igual ao tamanho da matricula entao pegamos tudo antes do @. Sena pegamos os 9 primeiros caracteres
	  		email_formatado = email[0..(email.index("@")-1)] + "@aluno.unb.br" # pega tudo que o usuario digitou
	  		# ate o primeiro @ e concatena o resultado com "@aluno.unb.br"
  		else
  			email_formatado = email[0..8] + "@aluno.unb.br"
  		end 
  	end 
  end
end
