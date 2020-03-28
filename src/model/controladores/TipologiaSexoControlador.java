package model.controladores;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.Controlador;



public class TipologiaSexoControlador extends Controlador {

	// instancia del singleton
	private static TipologiaSexoControlador instancia = null;

	/**
	 * 
	 */
	public TipologiaSexoControlador() {
		super(TipologiaSexo.class, "EvaluacionCentroEducativo");
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
	public List<TipologiaSexo> findAllTipologiasSexo () {
		List<TipologiaSexo> entities = new ArrayList<TipologiaSexo>();
		EntityManager em = getEntityManagerFactory().createEntityManager();
		try {			
			Query q = em.createNativeQuery("SELECT * FROM tipologiasexo", TipologiaSexo.class);
			entities = (List<TipologiaSexo>) q.getResultList();
		}
		catch (NoResultException nrEx) {
		}
		em.close();
		return entities;
	}
		
}
