//Prueba de PR

//Como usuarie de QuéMePongo, quiero poder manejar varios guardarropas para separar mis prendas según diversos criterios 
//(ropa de viaje, ropa de entrecasa, etc). 
class Usuario>> void ordenarEnGuardarropasSegunCriterio(){
			guardarropas.forEach(guardarropa -> guardarropa.guardarSegunCriterio(guardarropaPrincipal))
		}

class Guardarropa>> void guardarSegunCriterio(List<Prenda> unasPrendas){
			prendas.addAll(this.prendasQueCumplenCriterio(unasPrendas))
		    }

		     List<Prenda> prendasQueCumplenCriterio(List<Prenda> unasPrendas){
			return unasPrendas.filter(prenda -> prenda.cumpleCriterio(this.criterio))
		     }

class Prenda>> boolean cumpleCriterio(Criterio criterio){
			return criterio.equals(this.criterio);
		}

//Como usuarie de QuéMePongo, quiero poder crear guardarropas compartidos con otros usuaries (ej, ropa que comparto con mi hermane).
class Usuario>> void compartirGuardarropa(Guardarropa unGuardarropa, Usuario otroUsuario){
			otroUsuario.agregarGuardarropa(unGuardarropa) //No sé si sería mejor guardarlo en una lista independiente llamada guardarropas compartidos
		}

//Como usuarie de QuéMePongo, quiero que otro usuario me proponga tentativamente agregar una prenda al guardarropas.
//Como usuarie de QuéMePongo, quiero que otro usuario me proponga tentativamente quitar una prenda del guardarropas.
//Como usuarie de QuéMePongo, necesito ver todas las propuestas de modificación (agregar o quitar prendas) del guardarropas y poder 
//aceptarlas o rechazarlas.
class Usuario>> void proponer(Propuesta unaPropuesta, Usuario otroUsuario){
			otroUsuario.agregarAPropuestas(unaPropuesta)
		}

		void agregarAPropuestas(Propuesta unaPropuesta){
			propuestas.add(unaPropuesta)
		}

		void aceptarPropuesta(Propuesta unaPropuesta){
			quitarPropuesta(unaPropuesta)
			agregarAPropuestasAceptadas(unaPropuesta)
			unaPropuesta.aplicarseA(this)
		}

		void rechazarPropuesta(Propuesta unaPropuesta){
			quitarPropuesta(unaPropuesta)
		}

class AdicionPrenda>> void aplicarseA(Usuario unUsuario){
			unUsuario.agregarPrenda(this.prenda)
		     }


class SustraccionPrenda>> void aplicarseA(Usuario unUsuario){
				unUsuario.quitarPrenda(this.prenda)
			  } 

//Como usuarie de QuéMePongo, quiero poder deshacer las propuestas de modificación que haya aceptado.
class Usuario>> void deshacerPropuesta(Propuesta unaPropuesta){
			unaPropuesta.deshacerse(this)
		}

class AdicionPrenda>> void deshacerse(Usuario unUsuario){
			unUsuario.quitarPrenda(this.prenda)
		     }

class SustraccionPrenda>> void deshacerse(Usuario unUsuario){
				unUsuario.AgregarPrenda(this.prenda)
			  } 
