package controller;
import java.util.ArrayList;
import java.util.List;

import factory.model.Carro;
import factory.model.CarroEsportivo;
import factory.model.CarroPopular;
import factory.model.CarroSedan;
import factory.model.FabricaCarros;

public class Executar {

	public static void main(String[] args) {
		CarroPopular cp;
		CarroSedan cs;
		CarroEsportivo ce;
		List<Carro> listaCarros = new ArrayList<>();
		FabricaCarros fc;
		
		try {
			fc = CriarFabricaCarros.criarFabricaCarros();
			
			cp = fc.getCarroPopular(2010, 60);
			cp.andar();
			cp.economizarCombustivel();
			
			cs = fc.getCarroSedan(2015, 80, 25.4);
			cs.andar();
			cs.encherPortaMalas();
			
			ce = fc.getCarroEsportivo(2005, 100, 1.6);
			ce.andar();
			ce.acelerar();
			
			listaCarros.add( cp );
			listaCarros.add( cs );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
