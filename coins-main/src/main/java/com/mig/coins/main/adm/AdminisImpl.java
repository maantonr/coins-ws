package com.mig.coins.main.adm;

import java.sql.SQLException;
import java.util.List;

import com.mig.coins.db.base.CheckDataBase;
import com.mig.coins.db.dao.DAOFactory;
import com.mig.coins.db.dao.IPaisDAO;
import com.mig.coins.db.entity.Pais;
import com.mig.coins.main.BaseBusiness;
import com.mig.coins.util.session.Session;
import com.mig.coins.util.session.SessionException;
import com.mig.coins.util.session.SessionManager;

// PDTE Documentar
public class AdminisImpl extends BaseBusiness implements IAdminisBusiness {

	public AdminisImpl() throws SessionException {
		super();
	}

	public Pais getPais(Integer idPais) throws SessionException, SQLException {
		// Obtenemos el DAO para recuperar la lista de paises
		final Session session = SessionManager.getInstance().getSessionInCurrentThread();

		final DAOFactory factory = DAOFactory.getInstance();
		final IPaisDAO paisDAO = factory.getPaisDAO(session.getConnection(), session.getLocale());
		final Pais p = paisDAO.read(idPais);

		return p;
	}
	
	public List<Pais> getListaPaises(List<Integer> divisasIncluidas, 
			List<Integer> divisasExcluidas) throws SessionException {
		// PDTE Aplicar filtros
		
		// Obtenemos el DAO para recuperar la lista de paises
		final Session session = SessionManager.getInstance().getSessionInCurrentThread();

		final DAOFactory factory = DAOFactory.getInstance();
		final IPaisDAO paisDAO = factory.getPaisDAO(session.getConnection(), session.getLocale());
		final List<Pais> lPaises = paisDAO.getPaises();

		return lPaises;
	}

	@Override
	public Long getNumPaises() throws SessionException, SQLException {
		// Obtenemos el DAO para recuperar la lista de paises
		final Session session = SessionManager.getInstance().getSessionInCurrentThread();

		final DAOFactory factory = DAOFactory.getInstance();
		final IPaisDAO paisDAO = factory.getPaisDAO(session.getConnection(), session.getLocale());

		return paisDAO.count();
	}

	@Override
	public SystemStatus status() {
		final SystemStatus status = new SystemStatus();
		
		// PDTE Llevar estas variables al fichero de configuración
		status.setAppName(System.getenv("OPENSHIFT_APP_NAME"));
		status.setHomeDir(System.getenv("OPENSHIFT_HOMEDIR"));
		status.setDataDir(System.getenv("OPENSHIFT_DATA_DIR"));
		status.setLogDir(System.getenv("OPENSHIFT_LOG_DIR"));

		status.setDbInfo(CheckDataBase.getStatus());
		
		return status;
	}

}
