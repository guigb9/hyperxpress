package com.bandtec.hyperxpress.hyperxpressproject.control.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class AdaptarLista<T, R> {
	private final List<T> lista;
	private final List<R> listaAdaptada;
	private final Function<T, R> funcao;

	public AdaptarLista(Function<T, R> function, List<T> listaParametro) {
		this.lista = listaParametro;
		this.listaAdaptada = new ArrayList<>();
		this.funcao = function;
	}

	public List<R> iterarLista() {
		lista.forEach(t -> listaAdaptada.add(funcao.apply(t)));
		return listaAdaptada;
	}
}
