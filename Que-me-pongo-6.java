//Como usuarie de QueMePongo quiero tener una sugerencia diaria de qué ponerme y
//que  todas las mañanas, diariamente, esta sea actualizada 
Usuario>> 
	void actualizarPropuestaDiaria(Propuesta unaPropuesta){
		propuestaDiaria = unaPropuesta;
	}

//Como empleade de QueMePongo quiero poder disparar el cálculo de sugerencias diarias
//para todos los usuarios para poder ejecutar esta acción a principio de cada día.
Propuesta unaPropuesta = new Propuesta(unaPrenda);
usuario.actualizarPropuestaDiaria(unaPropuesta);

//Como usuarie de QueMePongo, quiero poder conocer cuáles son las últimas alertas
//meteorológicas publicadas en el sistema para estar informado (pudiendo verlas, por ejemplo, al entrar en quemepongo.com)
ProveedorDeClima >>
	void actualizarAlertas(String ciudad){
		alertas = apiClima.getAlertas(ciudad);
	}

	void mostrarAlertaMeteorologica(String ciudad){
		this.actualizarAlertas(ciudad);
		NotificationService.notify(alertas.get("CurrentAlerts"));
	}

//Como empleade de QueMePongo, necesito poder disparar un proceso que consulte y
//actualice la lista de alertas publicadas en el sistema para tener control sobre cuándo se publican las mismas 
ProveedorDeClima>> 
	List<String> obtenerAlertasDe(String ciudad){
		this.actualizarAlertas(ciudad);
		return alertas.get("CurrentAlerts");
	}

//Como usuarie de QuéMePongo quiero que se actualice mi sugerencia diaria con las
//condiciones climáticas actualizadas cuando se genere algún alerta durante el día 
ProveedorDeClima>> 
	void recibirAlertaMetereologica(String alerta){
		alertas.add(alerta);
		observers.notificar(alerta);
	}

Observer>> 
	void notificar(String alerta);

PropuestaDiariaObserver>>
	void notificar(String alerta){
		Propuesta propuestaDiaria = new Propuesta(prendaAptaPara(alerta))
		usuarios.forEach(usuario -> usuario.actualizarPropuestaDiaria(propuestaDiaria));
	}

	Prenda prendaAptaPara(String alerta){
		return prendas.stream().findAny(prenda -> prenda.esAptaPara(alerta));
	}

//Como usuarie de QueMePongo quiero tener la posibilidad de que ante una alerta de
//tormenta la app me notifique que debo llevarme también un paraguas 
TormentaObserver>> 
	void notificar(String alerta){
		if(alerta.equals("Tormenta")){
			NotificationService.notify("Deberías llevar paraguas");
		}
	}

//Como usuarie de QueMePongo quiero que ante una alerta meteorológica de granizo la
//app  me notifique que evite salir en auto
GranizoObserver>> 
	void notificar(String alerta){
		if(alerta.equals("Granizo")){
			NotificationService.notify("Evite salir en auto");
		}
	}

//Como usuarie de QueMePongo quiero poder recibir un mail avisándome si se generó algún
//alerta meteorológico y cuál
MailObserver>> 
	void notificar(String alerta){
		MailSender.send(adress, alerta);
	}

//Como usuarie de QuéMePongo quiero poder configurar cuáles de estas acciones (notificaciones,
//mail, recálculo)  quiero que se ejecuten y cuáles no, además de soportar nuevas acciones a futuro.
//(No nos interesará, sin embargo, soportar nuevas alertas)
ProveedorDeClima>>
	void agregarObserver(Observer unObserver){
		observers.add(unObserver);
	}

	void quitarObserver(Observer unObserver){
		observers.remove(unObserver);
	}
