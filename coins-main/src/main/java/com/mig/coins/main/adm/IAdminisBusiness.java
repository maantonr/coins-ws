package com.mig.coins.main.adm;

import java.util.List;

import com.mig.coins.db.entity.Pais;
import com.mig.coins.main.IBaseBusiness;

public interface IAdminisBusiness extends IBaseBusiness {

	public Pais getPais(Integer idPais);
	public List<Pais> getListaPaises(List<Integer> divisasIncluidas, List<Integer> divisasExcluidas);
}
