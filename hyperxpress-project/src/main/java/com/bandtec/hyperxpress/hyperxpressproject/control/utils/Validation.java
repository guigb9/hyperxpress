package com.bandtec.hyperxpress.hyperxpressproject.control.utils;

import br.com.safeguard.check.SafeguardCheck;
import br.com.safeguard.interfaces.Check;
import br.com.safeguard.types.ParametroTipo;

public class Validation {

	public static String validacaoCampos(String cpf, String email, String nome) {

		String mensagem = "";

		Check checkCPF = new SafeguardCheck();
		Check checkEmail = new SafeguardCheck();
		Check checkNome = new SafeguardCheck();

		boolean errorCPF = (checkCPF
				.elementOf(cpf, ParametroTipo.CPF).validate().hasError());

		boolean errorEmail = (checkEmail
				.elementOf(email, ParametroTipo.EMAIL).validate().hasError());

		boolean errorNome = (checkNome
				.elementOf(nome, ParametroTipo.DEFAULT_SEM_NUMEROS).validate().hasError());

		System.out.println(errorNome);

		if (errorCPF) {
			mensagem += "CPF ";
		}
		if (errorEmail) {
			mensagem += "EMAIL ";
		}
		if (errorNome) {
			mensagem += "NOME ";
		}

		if (mensagem.isEmpty()) {
			return "OK";
		}

		return "Foi encontrado Erros no(s) campo(s): " + mensagem;

	}


}
