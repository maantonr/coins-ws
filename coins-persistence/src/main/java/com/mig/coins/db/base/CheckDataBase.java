package com.mig.coins.db.base;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

import com.mig.coins.db.dao.DAOFactory;
import com.mig.coins.db.dao.IPaisDAO;
import com.mig.coins.util.session.Session;
import com.mig.coins.util.session.SessionException;
import com.mig.coins.util.session.SessionManager;

// PDTE Documentar
public class CheckDataBase {

	public static DbStatus getStatus() {
		final DbStatus status = new DbStatus();

		try {
			final Session session = SessionManager.getInstance().getSessionInCurrentThread();
			final Connection conn = session.getConnection();
			final Locale loc = session.getLocale();

			final PersistenceConfig config = PersistenceConfig.getInstance();
			if (config.isDummyMode()) {
				status.setStatus("DUMMY");
			} else {
				final IPaisDAO dao = DAOFactory.getInstance().getPaisDAO(conn, loc);
				status.setStatus("OK - NumPaises=" + Long.toString(dao.count()));
			}
			
			status.setDriver(config.getDriver());
			status.setHost(config.getHost());
			status.setPort(config.getPort());
			status.setUrl(config.getUrl());

		} catch (SessionException | SQLException e) {
			e.printStackTrace();
			status.setStatus("KO - " + e.getMessage());
		}

		return status;
	}
}
