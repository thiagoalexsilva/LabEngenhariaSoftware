function teclaEspecial(e){
    //var especiais = [8, 9, 13, 16, 17, 18, 20, 27, 35, 36, 37, 38, 39, 40, 45, 46];

    return e.keyCode != 0;
    /*if(especiais.indexOf(e.key) == -1){
        return false;
    }
    
    return true;*/
}

function mascaraNumero(e, max){
			
	var num = e.target.value;
	
	if(num.length <= max-1){
		if('0123456789'.indexOf(e.key) == -1){
                    if(!teclaEspecial(e))
                        e.preventDefault();
		}
	}
	else{
            if(!teclaEspecial(e))
                e.preventDefault();
	}
}

function mascaraFlutuante(e, max){
			
	var num = e.target.value;
	
	if(num.length <= max-1){
		if('-0123456789.'.indexOf(e.key) == -1){
                    if(!teclaEspecial(e))
                        e.preventDefault();
		}
	}
	else{
            if(!teclaEspecial(e))
                e.preventDefault();
	}
}

function mascaraData(e){
			
	var num = e.target.value;
	
	if(num.length <= max-1){
		if('0123456789/'.indexOf(e.key) == -1){
                    if(!teclaEspecial(e))
                        e.preventDefault();
		}
	}
	else{
            if(!teclaEspecial(e))
                e.preventDefault();
	}
        
}

function mascaraAlfa(e, max){
	
	var cpf = e.target.value;
	
	if(cpf.length <= max-1){
		if('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'.indexOf(e.key) == -1){
		    if(!teclaEspecial(e))
                        e.preventDefault();
		}
	}
	else{
            if(!teclaEspecial(e))
                e.preventDefault();
	}
}

function mascaraAlfaNumero(e, max){
	
	var cpf = e.target.value;
	
	if(cpf.length <= max-1){
		if('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789'.indexOf(e.key) == -1){
		    if(!teclaEspecial(e))
                        e.preventDefault();
		}
	}
	else{
            if(!teclaEspecial(e))
                e.preventDefault();
	}
}

function mascaraEmail(e){
	
	var cpf = e.target.value;
	
	if('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@._'.indexOf(e.key) == -1){
	    if(!teclaEspecial(e))
                e.preventDefault();
	}

}

function cpfValido(cpf){
	
	var i=0;
	
	if(cpf.length != 11)
		return false;
	
	for(i=0; i<cpf.length; i++){
		if("0123456789".indexOf(cpf[i]) == -1)
			return false;
	}
	
	return true;
}

function cnpjValido(cnpj){
	
	var i=0;
	
	if(cnpj.length != 14)
		return false;
	
	for(i=0; i<cnpj.length; i++){
		if("0123456789".indexOf(cnpj[i]) == -1)
			return false;
	}
	
	return true;
}

function telefoneValido(telefone){
	
	var i=0;
	
	if(telefone.length != 11)
		return false;
	
	for(i=0; i<telefone.length; i++){
		if("0123456789".indexOf(telefone[i]) == -1)
			return false;
	}
	
	return true;
}

function celularValido(celular){
	
	var i=0;
	
	if(celular.length != 12)
		return false;
	
	for(i=0; i<celular.length; i++){
		if("0123456789".indexOf(celular[i]) == -1)
			return false;
	}
	
	return true;
}

function dataValido(data){
	
	var i=0;
	var ano;
	var mes;
	var dia;
	
	if(data.length != 10)
		return false;
	
	for(i=0; i<data.length; i++){
		if(i == 2 || i == 5){
			if(data[i] != '/')
				return false;
		}
		else{
			if("0123456789".indexOf(data[i]) == -1)
				return false;
		}
		
		ano = parseInt(data.substr(6, 4));
		if(ano < 1900)
			return false;
		
		mes = parseInt(data.substr(3, 2));
		if(!(mes >= 1 && mes <= 12))
			return false;
		
		dia = parseInt(data.substr(0, 2));
		if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12){
			if(!(dia >= 1 && dia <= 31))
				return false;
		}
		else if(mes != 2){
			if(!(dia >= 1 && dia <= 30))
				return false;
		}
		else{
			if(ano % 4 == 0){
				if(!(dia >= 1 && dia <= 29))
					return false;
			}
			else{
				if(!(dia >= 1 && dia <= 28))
					return false;
			}
		}
	}
	
	return true;
}

function cepValido(cep){
	
	var i=0;
	
	if(cep.length != 8)
		return false;
	
	for(i=0; i<cep.length; i++){
		if("0123456789".indexOf(cep[i]) == -1)
			return false;
	}
	
	return true;
}

function senhaValido(senha){
	return senha.length >= 8;
}

function emailValido(email){

	var i=0;
	var j=0;
	
	var at_split = email.split('@');
	var dot_split;
	
	if(at_split.length != 2)
		return false;
	
	dot_split = at_split[1].split('.');
				
	if(dot_split.length < 2)
		return false;
		
	for(i=0; i<dot_split.length; i++){
		if(dot_split[i] == '')
			return false;
		else{
			for(j=0; j<dot_split[i].length; j++){
				if("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_"
					.indexOf(dot_split[i][j]) == -1)
					return false;
			}
		}
	}
				
	return true;
}

function crmvValido(crmv){
	
	var i=0;
	
	if(crmv.length != 5)
		return false;
	
	for(i=0; i<crmv.length; i++){
		if("0123456789".indexOf(cnpj[i]) == -1)
			return false;
	}
	
	return true;
}

var i=0;

// Arrays com todos os campos dos tipos especificados
var cpfs = document.getElementsByClassName("cpf");
var cnpjs = document.getElementsByClassName("cnpj");
var telefones = document.getElementsByClassName("telefone");
var celulares = document.getElementsByClassName("celular");
var datas = document.getElementsByClassName("data");
var ceps = document.getElementsByClassName("cep");
var senhas = document.getElementsByClassName("senha");
var emails = document.getElementsByClassName("email");
var crmvs = document.getElementsByClassName("crmv");
var numeros = document.getElementsByClassName("numero");
var alfanumeros = document.getElementsByClassName("alfanumero");
var alfas = document.getElementsByClassName("alfa");
var flutuantes = document.getElementsByClassName("flutuante");

var submit = document.getElementsByName("submit")[0];

for(i=0; i<flutuantes.length; i++){
	flutuantes[i].addEventListener('keypress', function(e){
            mascaraFlutuante(e, 100);
	});
}

for(i=0; i<numeros.length; i++){
	numeros[i].addEventListener('keypress', function(e){
		mascaraNumero(e, 100);
	});
}

for(i=0; i<alfas.length; i++){
	alfas[i].addEventListener('keypress', function(e){
            mascaraAlfa(e, 100);
	});
}

for(i=0; i<alfanumeros.length; i++){
	alfanumeros[i].addEventListener('keypress', function(e){
        mascaraAlfaNumero(e, 100);
	});
}

for(i=0; i<cpfs.length; i++){
	cpfs[i].addEventListener('keypress', function(e){
		mascaraNumero(e, 11);
	});
}

for(i=0; i<cnpjs.length; i++){
	cnpjs[i].addEventListener('keypress', function(e){
		mascaraNumero(e, 14);                
	});
}

for(i=0; i<telefones.length; i++){
	telefones[i].addEventListener('keypress', function(e){
		mascaraNumero(e, 10);
	});
        
        telefones[i].addEventListener('focus', function(e){
		var telefone = e.target.value;
                e.target.value = telefone.split('(').join('').split(')').join('').split(' ').join('').split('-').join('');
	});
        
        telefones[i].addEventListener('blur', function(e){
                var telefone = e.target.value;
                if(telefone.length >= 1 && telefone.length < 2){
                    e.target.value = '(' + telefone;
                }
                else if(telefone.length >= 2 && telefone.length < 8){
                    e.target.value = '(' + telefone.substr(0, 2) + ') ' + telefone.substr(2);
                }
                else if(telefone.length >= 8){
                    e.target.value = '(' + telefone.substr(0, 2) + ') ' + telefone.substr(2, 4) + '-' +  telefone.substr(6);
                }
                
	});
}

for(i=0; i<celulares.length; i++){
	celulares[i].addEventListener('keypress', function(e){
		mascaraNumero(e, 11);
	});
        
        celulares[i].addEventListener('focus', function(e){
		var celular = e.target.value;
                e.target.value = celular.split('(').join('').split(')').join('').split(' ').join('').split('-').join('');
	});
        
        celulares[i].addEventListener('blur', function(e){
                var celular = e.target.value;
                if(celular.length >= 1 && celular.length < 2){
                    e.target.value = '(' + celular;
                }
                else if(celular.length >= 2 && celular.length < 8){
                    e.target.value = '(' + celular.substr(0, 2) + ') ' + celular.substr(2);
                }
                else if(celular.length >= 8){
                    e.target.value = '(' + celular.substr(0, 2) + ') ' + celular.substr(2, 5) + '-' +  celular.substr(7);
                }
                
	});
}

for(i=0; i<datas.length; i++){
	datas[i].addEventListener('keypress', function(e){
		mascaraNumero(e, 8);
	});
        
        datas[i].addEventListener('focus', function(e){
		var data = e.target.value;
                e.target.value = data.split('/').join('');
	});
        
        datas[i].addEventListener('blur', function(e){
                var data = e.target.value;
                if(data.length >= 2 && data.length < 4){
                    e.target.value = data.substr(0, 2) + '/';
                }
                else if(data.length >= 4){
                    e.target.value = data.substr(0, 2) + '/' + data.substr(2, 2) + '/' +  data.substr(4);
                }
	});
}

for(i=0; i<emails.length; i++){
	emails[i].addEventListener('keypress', function(e){
		mascaraEmail(e);
	});
}

for(i=0; i<ceps.length; i++){
	ceps[i].addEventListener('keypress', function(e){
		mascaraNumero(e, 8);                
	});
}

for(i=0; i<senhas.length; i++){
	senhas[i].addEventListener('keypress', function(e){
		//mascaraAlfaNumero(e, 14);                
	});
}

for(i=0; i<crmvs.length; i++){
	crmvs[i].addEventListener('keypress', function(e){
		mascaraNumero(e, 5);
	});
}