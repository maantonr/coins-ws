package com.mig.coins.db.dao;

import java.sql.SQLException;
import java.util.List;

import com.mig.coins.db.entity.Pais;

public interface IPaisDAO extends IDAO {

	public Pais read(Integer idPais) throws SQLException;
	public List<Pais> getPaises();
}
