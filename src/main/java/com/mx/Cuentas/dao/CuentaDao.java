package com.mx.Cuentas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.Cuentas.entidad.Cuenta;

public interface CuentaDao extends JpaRepository<Cuenta, Long>{
	List<Cuenta>findByCurpCliente(String curpCliente);
	List<Cuenta> findByIdBanco(int idBanco);
}
