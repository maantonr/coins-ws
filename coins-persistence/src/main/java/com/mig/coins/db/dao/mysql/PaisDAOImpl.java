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

	public final static String COLUMNAS_PAIS = "`paises`.id AS PAIS_ID, `paises`.pais AS PAIS_PAIS, `paises`.siglas AS PAIS_SIGLAS";
	public final static String COLUMNAS_PAIS_DIVISA = COLUMNAS_PAIS + ", " + DivisaDAOImpl.COLUMNAS_DIVISA;
	
	private final static String GET_PAIS_SQL = "SELECT " + COLUMNAS_PAIS_DIVISA + "FROM " +
			"paises " +
			"LEFT JOIN `pais_divisa` ON `pais_divisa`.pais = `paises`.id " +
			"LEFT JOIN `divisas` ON `divisas`.id = `pais_divisa`.divisa ";

	public PaisDAOImpl() {
		super();
	}

	@Override
	public Pais read(Integer idPais) throws SQLException {
		final String where = "(`paises`.id` = ?)";
		final List<Object> args = new ArrayList<Object>(1);
		args.add(idPais);

		List<Pais> found = search(where, args, null);
		if (found.isEmpty()) {
			// PDTE Excepciones
		}
		if (found.size() != 1) {
			// PDTE Excepciones
		}

		return found.get(0);
	}
	
	// PDTE Implementar
	@Override
	public List<Pais> getPaises() {
		final List<Pais> paises = new ArrayList<Pais>(3);
		
		paises.add(getSpain());
		paises.add(getUsa());
		paises.add(getAustria());
		
		return paises;
	}

	// PDTE Implementar
	public List<Pais> getPaisesConDivisa() {

		return null;
	}
	

	// PDTE Implementar
	private List<Pais> search(String where, List<Object> lArgs, String sort) throws SQLException {
		String select = GET_PAIS_SQL;
		
		if ((null != where) && (!where.trim().isEmpty())) {
			select += "WHERE " + where;
		}
		
		if ((null != sort) && (!sort.trim().isEmpty())) {
			// PDTE considerar el orden de las divisas para que la lista salga ordenada
			select += "ORDER BY " + sort;
		}

		PreparedStatement stmt=null;
		ResultSet rs = null;
		try {
			stmt = getPreparedStatement(select, lArgs);
			rs = stmt.executeQuery();
			
			// PDTE Implementar
			// PDTE Recorro el resultset buscando el cambio de idPais, uso el metodo copyFromRow de DivisaDAO para crear el Objeto Divisa
		} catch (SQLException e) {
			// PDTE Exceptions
			e.printStackTrace();
			throw e; 
		} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close(); 
		}
		
		return null;
	}

	// PDTE Implementar
	protected Pais copyFromRow(ResultSet rs) {
		return null;
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

		stmt=getPreparedStatement(sql);
		rs = stmt.executeQuery();
		if (rs.next()) {
			nPaises = rs.getLong(1);
		} 

		// PDTE asegura que se cierra el resultset y el statement
		if (rs != null) rs.close();
		if (stmt != null) stmt.close(); 

		return nPaises;
	}
}
