package com.mig.coins.main.adm;

import java.sql.SQLException;
import java.util.List;

import com.mig.coins.db.entity.Pais;
import com.mig.coins.main.IBaseBusiness;
import com.mig.coins.util.session.SessionException;

public interface IAdminisBusiness extends IBaseBusiness {

	public Pais getPais(Integer idPais) throws SessionException, SQLException;
	public List<Pais> getListaPaises(List<Integer> divisasIncluidas, List<Integer> divisasExcluidas) throws SessionException;
	public Long getNumPaises() throws SessionException, SQLException;
	
	
	public SystemStatus status();
}
