package it.polito.tdp.country.model;

import org.jgrapht.Graph;
import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.EdgeTraversalEvent;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.DefaultEdge;
import java.util.*;

public class CountryTraversalListener implements TraversalListener<Country, DefaultEdge> {
	private Graph<Country,DefaultEdge> graph;
	private Map<Country,Country> map;
	
	public CountryTraversalListener (Graph<Country,DefaultEdge> graph, Map<Country,Country> map){
		this.graph = graph;
		this.map = map;
	}
	@Override
	public void connectedComponentFinished(ConnectedComponentTraversalEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectedComponentStarted(ConnectedComponentTraversalEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edgeTraversed(EdgeTraversalEvent<DefaultEdge> evento) {
		//mappa deve puntare dal nuovo verso la sorgente
		//so verso quale nodo puntare perch� uno dei due vertici c'� gi� nel keyset della mappa
		Country c1 =graph.getEdgeSource(evento.getEdge());
		Country c2 = graph.getEdgeTarget(evento.getEdge());
		if(!map.containsKey(c1)){
			//c1 � quello nuovo
			map.put(c1, c2);
		}else{
			//c2 � quello nuovo
			map.put(c2, c1);
		}
	
	}

	@Override
	public void vertexFinished(VertexTraversalEvent<Country> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vertexTraversed(VertexTraversalEvent<Country> arg0) {
		// TODO Auto-generated method stub
		
	}

}
