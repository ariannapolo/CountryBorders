package it.polito.tdp.country.model;

import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.db.CountryDao;

public class Model {
	
	private UndirectedGraph<Country, DefaultEdge> graph  ;
	private List<Country> countries;
	
	public Model() {
		this.graph = new SimpleGraph<>(DefaultEdge.class) ;
		
	}
	
	public List<Country> getCountries(){
		if(this.countries ==null){
			CountryDao dao = new CountryDao();
			this.countries = dao.listCountry();
		}
		return countries;
	}
	
	/**
	 * Creazione del grafo CountryBorders.
	 * Prima versione: per ogni coppia di vertici, chiedo al database se esiste un arco.
	 */
	public void creaGrafo1() {
		
		CountryDao dao = new CountryDao() ;
		
		// crea i vertici del grafo
		Graphs.addAllVertices(graph, this.getCountries()) ; //non scrivo this.countries perchè non siamo sicuri se sia già stata inizializzata!!!
	
		// crea gli archi del grafo -- versione 1
		for(Country c1: graph.vertexSet()) {
			for(Country c2: graph.vertexSet()) {
				if(!c1.equals(c2)) {
					if( dao.confinanti(c1, c2) ) {
						graph.addEdge(c1, c2) ;
					}
				}
			}
		}
	}
	
	/**
	 * Creazione del grafo CountryBorders.
	 * Seconda versione: per ogni vertice, chiedo al database la lista dei vertici ad esso confinanti.
	 */
	public void creaGrafo2() {
		
		CountryDao dao = new CountryDao() ;
		
		// crea i vertici del grafo
		Graphs.addAllVertices(graph, this.getCountries()) ;
	
		// crea gli archi del grafo -- versione 2
		for(Country c: graph.vertexSet()) {
			List<Country> adiacenti = dao.listAdiacenti(c) ;
			for(Country c2: adiacenti)
				graph.addEdge(c, c2) ;
		}
	}
	
	/**
	 * Creazione del grafo CountryBorders.
	 * Terza versione: una sola volta, chiedo al database l'elenco delle coppie di vertici confinanti.
	 */
	public void creaGrafo3() {
		
		CountryDao dao = new CountryDao() ;
		
		// crea i vertici del grafo
		Graphs.addAllVertices(graph, dao.listCountry()) ;
	
		// crea gli archi del grafo -- versione 3
		for(CountryPair cp : dao.listCoppieCountryAdiacenti()) {
			graph.addEdge(cp.getC1(), cp.getC2()) ;
		}
	}

	public void printStats() {
		System.out.format("Grafo: Vertici %d, Archi %d\n", graph.vertexSet().size(), graph.edgeSet().size());
	}

}
