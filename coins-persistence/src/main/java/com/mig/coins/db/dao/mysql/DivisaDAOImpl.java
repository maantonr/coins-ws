package com.mig.coins.db.dao.mysql;

import java.util.ArrayList;
import java.util.List;

import com.mig.coins.db.dao.AbstractDAOImpl;
import com.mig.coins.db.dao.IDAO;
import com.mig.coins.db.dao.IDivisaDAO;
import com.mig.coins.db.entity.Divisa;

public class DivisaDAOImpl extends AbstractDAOImpl implements IDAO, IDivisaDAO {

	public final static String COLUMNAS_DIVISA = "`paises`.id AS PAIS_ID, `paises`.pais AS PAIS_PAIS, `paises`.siglas AS PAIS_SIGLAS";

	public DivisaDAOImpl() {
	}

	@Override
	public Divisa read(Integer idDivisa) {
		switch (idDivisa.intValue()) {
		case 1:
			return getEuro();
		case 2:
			return getDolar();
		case 3:
			return getPeseta();
		default:
			return null;
		}
	}


	@Override
	public List<Divisa> getDivisas() {
		final List<Divisa> lDivisas=new ArrayList<Divisa>(3);

		lDivisas.add(getEuro());
		lDivisas.add(getDolar());
		lDivisas.add(getPeseta());

		return lDivisas;
	}

	@Override
	public List<Divisa> getDivisasPais(Integer idPais) {
		List<Divisa> lDivisas=null;
		
		switch (idPais.intValue()) {
		case 1:
			lDivisas = new ArrayList<Divisa>(2);
			lDivisas.add(getEuro());
			lDivisas.add(getPeseta());
			
			break;
		case 2:
			lDivisas = new ArrayList<Divisa>(1);
			lDivisas.add(getDolar());
			
			break;
		case 3:
			lDivisas = new ArrayList<Divisa>(1);
			lDivisas.add(getEuro());
			
			break;
		default:
			break;
		}
		
		return lDivisas;
	}
	
	private Divisa getEuro() {
		final Divisa Euro = new Divisa();
		Euro.setId(1);
		Euro.setNombre("Euro");
		Euro.setSiglas("EUR");
		Euro.setFraccion("Céntimo");
		Euro.setMultiplo(100);
		
		return Euro;
	}
	
	private Divisa getDolar() {
		final Divisa Dolar = new Divisa();
		Dolar.setId(2);
		Dolar.setNombre("Dolar");
		Dolar.setSiglas("USD");
		Dolar.setFraccion("Céntavo");
		Dolar.setMultiplo(100);
		
		return Dolar;
	}

	private Divisa getPeseta() {
		
		final Divisa Peseta = new Divisa();
		Peseta.setId(3);
		Peseta.setNombre("Peseta");
		Peseta.setSiglas("PTA");
		Peseta.setFraccion("Céntimo");
		Peseta.setMultiplo(100);
		
		return Peseta;
	}

	@Override
	public long count() {
		return 3;
	}
}
