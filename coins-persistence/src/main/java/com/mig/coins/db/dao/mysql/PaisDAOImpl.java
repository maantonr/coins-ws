package com.mig.coins.db.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mig.coins.db.dao.AbstractDAOImpl;
import com.mig.coins.db.dao.DAOFactory;
import com.mig.coins.db.dao.IDAO;
import com.mig.coins.db.dao.IDivisaDAO;
import com.mig.coins.db.dao.IPaisDAO;
import com.mig.coins.db.entity.Divisa;
import com.mig.coins.db.entity.Pais;

// PDTE Implementar
public class PaisDAOImpl extends AbstractDAOImpl implements IDAO, IPaisDAO {

	public PaisDAOImpl() {
		super();
	}

	@Override
	public Pais read(Integer idPais) {
		switch (idPais.intValue()) {
		case 1:
			return getSpain();
		case 2:
			return getUsa();
		case 3:
			return getAustria();
		default:
			return null;
		}

	}

	@Override
	public List<Pais> getPaises() {
		final List<Pais> paises = new ArrayList<Pais>(3);
		
		paises.add(getSpain());
		paises.add(getUsa());
		paises.add(getAustria());
		
		return paises;
	}
	
	private Pais getSpain() {
		final Pais Spain = new Pais();
		
		Spain.setId(1);
		Spain.setNombre("España");
		Spain.setSiglas("ESP");

		IDivisaDAO div = DAOFactory.getInstance().getDivisaDAO(getConnection(), getLocale());
		final List<Divisa> divisas = div.getDivisasPais(1);
		Spain.setlDivisas(divisas);
		
		return Spain;
	}

	private Pais getUsa() {
		final Pais EstadosUnidos = new Pais();

		EstadosUnidos.setId(2);
		EstadosUnidos.setNombre("Estados Unidos");
		EstadosUnidos.setSiglas("USA");
		
		IDivisaDAO div = DAOFactory.getInstance().getDivisaDAO(getConnection(), getLocale());
		final List<Divisa> divisas = div.getDivisasPais(2);
		EstadosUnidos.setlDivisas(divisas);
		
		return EstadosUnidos;
	}

	private Pais getAustria() {
		final Pais Austria = new Pais();
		Austria.setId(3);
		Austria.setNombre("Austria");
		Austria.setSiglas("AUS");
		
		IDivisaDAO div = DAOFactory.getInstance().getDivisaDAO(getConnection(), getLocale());
		final List<Divisa> divisas = div.getDivisasPais(3);
		Austria.setlDivisas(divisas);
		
		return Austria;
	}

	@Override
	public long count() throws SQLException {
		long nPaises = 0;
		final String sql = "SELECT count(*) FROM `paises`";

		PreparedStatement stmt=null;
		ResultSet rs = null;

		stmt=getConnection().prepareStatement(sql);
		rs = stmt.executeQuery();
		if (rs.next()) {
			nPaises = rs.getLong(1);
		} 

		if (rs != null) rs.close();
		if (stmt != null) stmt.close(); 

		return nPaises;
	}
}
