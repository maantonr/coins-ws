package com.mig.coins.db.dao;

import java.util.List;

import com.mig.coins.db.entity.Divisa;

public interface IDivisaDAO extends IDAO {

	public Divisa read(Integer idDivisa);
	public List<Divisa> getDivisas();
	public List<Divisa> getDivisasPais(Integer idPais);
}
