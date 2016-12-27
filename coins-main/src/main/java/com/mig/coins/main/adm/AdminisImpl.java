package com.mig.coins.main.adm;

import java.util.List;

import com.mig.coins.db.dao.DAOFactory;
import com.mig.coins.db.dao.IPaisDAO;
import com.mig.coins.db.entity.Pais;
import com.mig.coins.main.BaseBusiness;
import com.mig.coins.main.base.Session;
import com.mig.coins.main.base.SessionManager;

// PDTE Documentar
public class AdminisImpl extends BaseBusiness implements IAdminisBusiness {

	public Pais getPais(Integer idPais) {
		// Obtenemos el DAO para recuperar la lista de paises
		final Session session = SessionManager.getInstance().getSessionInCurrentThread();

		final DAOFactory factory = DAOFactory.getInstance();
		final IPaisDAO paisDAO = factory.getPaisDAO(session.getConnection(), session.getLocale());
		final Pais p = paisDAO.read(idPais);

		return p;
	}
	
	public List<Pais> getListaPaises(List<Integer> divisasIncluidas, 
			List<Integer> divisasExcluidas) {
		// PDTE Aplicar filtros
		
		// Obtenemos el DAO para recuperar la lista de paises
		final Session session = SessionManager.getInstance().getSessionInCurrentThread();

		final DAOFactory factory = DAOFactory.getInstance();
		final IPaisDAO paisDAO = factory.getPaisDAO(session.getConnection(), session.getLocale());
		final List<Pais> lPaises = paisDAO.getPaises();

		return lPaises;
	}

}
