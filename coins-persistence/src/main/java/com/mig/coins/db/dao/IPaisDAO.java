package com.mig.coins.db.dao;

import java.util.List;

import com.mig.coins.db.entity.Pais;

public interface IPaisDAO {

	public Pais read(Integer idPais);
	public List<Pais> getPaises();
}
