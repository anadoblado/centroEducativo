package model.controladores;

import java.util.ArrayList;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.Controlador;
import model.Tipologiasexo;



public class TipologiaSexoControlador extends Controlador {

	// instancia del singleton
	private static TipologiaSexoControlador instancia = null;

	/**
	 * 
	 */
	public TipologiaSexoControlador() {
		super(Tipologiasexo.class, "EvaluacionCentroEducativo");
	}

	/**
	 * 
	 * @return
	 */
	public static TipologiaSexoControlador getInstancia() {
		if (instancia == null) {
			instancia = new TipologiaSexoControlador();
		}
		return instancia;
	}

	
	/**
	 * 
	 * @return
	 */
	public List<Tipologiasexo> findAllTipologiasSexo () {
		List<Tipologiasexo> entities = new ArrayList<Tipologiasexo>();
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {			
			Query q = em.createNativeQuery("SELECT * FROM tipologiasexo", Tipologiasexo.class);
			entities = (List<Tipologiasexo>) q.getResultList();
		}
		catch (NoResultException nrEx) {
		}
		em.close();
		return entities;
	}
		
}
