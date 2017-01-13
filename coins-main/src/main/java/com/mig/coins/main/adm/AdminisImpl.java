package com.mig.coins.main.adm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.mig.coins.db.base.CheckDataBase;
import com.mig.coins.db.dao.DAOFactory;
import com.mig.coins.db.dao.IPaisDAO;
import com.mig.coins.db.entity.Pais;
import com.mig.coins.main.BaseBusiness;
import com.mig.coins.main.CodeValue;
import com.mig.coins.main.MainConfig;
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
		
		final MainConfig config = MainConfig.getInstance();
		
		// PDTE Llevar estas variables al fichero de configuración
		status.setAppName(System.getenv("OPENSHIFT_APP_NAME"));
		status.setHomeDir(System.getenv("OPENSHIFT_HOMEDIR"));
		status.setDataDir(config.getRootDataPath());
		status.setLogDir(config.getLogPath());

		status.setDbInfo(CheckDataBase.getStatus());
		
		Map<String, String> mEnvVar = System.getenv();
		if ((null != mEnvVar) && (!mEnvVar.isEmpty())) {
			final Set<Entry<String, String>> s = mEnvVar.entrySet();
			final List<CodeValue> lVar = new ArrayList<CodeValue>(s.size());
			status.setlVar(lVar);
			for (Entry<String, String> entry : s) {
				lVar.add(new CodeValue(entry.getKey(), entry.getValue()));
			}
		}
		
		return status;
	}

}
