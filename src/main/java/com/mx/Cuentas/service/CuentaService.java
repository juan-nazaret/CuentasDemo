package com.mx.Cuentas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mx.Cuentas.dao.CuentaDao;
import com.mx.Cuentas.dto.Respuesta;
import com.mx.Cuentas.entidad.Cuenta;

public class CuentaService {
	private CuentaDao cuentaDao;
	
	public CuentaService(CuentaDao cuentaDao) {
		this.cuentaDao = cuentaDao;
	}
	
	public ResponseEntity<List<Cuenta>> getCuentasAll(){
		if(cuentaDao.findAll().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(cuentaDao.findAll());
	}
	
	public Respuesta guardar(Cuenta cuenta) {
		Respuesta rs = new Respuesta();
		try {
			if(cuentaDao.existsById(cuenta.getNumero())) {
				rs.setMensaje("la cuenta no se creo porque el numero ya existe");
				rs.setSuccess(false);
				rs.setObj(cuenta.getNumero());
				return rs;
			}
			cuentaDao.save(cuenta);
			rs.setMensaje("la cuenta ha sido agregada a la base de datos");
			rs.setSuccess(true);
			rs.setObj(cuenta);
			return rs;
		}catch (Exception e) {
			List<String>error = new ArrayList<>();
			for(StackTraceElement el:e.getStackTrace()) {
				error.add("clase " + el.getClassName());
				error.add("Metodos: "+el.getMethodName());
				error.add("Linea: " + el.getLineNumber());
			}
			rs.setMensaje("Error al guardar intente de nuevo mas tarde");
			rs.setSuccess(false);
			rs.setObj(error);
			return rs;
		}
	}
	public Respuesta editar(Cuenta cuenta) {
		Respuesta rs = new Respuesta();
		try {
			if(cuentaDao.existsById(cuenta.getNumero())) {
				rs.setMensaje("la cueta ha sido editada");
				rs.setSuccess(true);
				rs.setObj(cuenta);
				cuentaDao.save(cuenta);
				return rs;
			}
			rs.setMensaje("La cuenta que tratas de editar no existe");
			rs.setSuccess(false);
			rs.setObj(cuenta);
			return rs;
		}catch(Exception e) {
			List<String>error = new ArrayList<>();
			for(StackTraceElement el:e.getStackTrace()) {
				error.add("clase " + el.getClassName());
				error.add("Metodos: "+el.getMethodName());
				error.add("Linea: " + el.getLineNumber());
			}
			rs.setMensaje("error al editar, intente de nuevo mas tarde");
			rs.setSuccess(false);
			rs.setObj(error);
			return rs;
		}
	}
	
	public Respuesta eliminar(Cuenta cuenta) {
		Respuesta rs= new Respuesta();
		try {
			if(cuentaDao.existsById(cuenta.getNumero())) {
				rs.setObj(cuentaDao.findById(null));
				rs.setMensaje("La cuenta ha sido eliminada");
				rs.setSuccess(true);
				cuentaDao.delete(cuenta);
				return rs;
			}
			rs.setMensaje("la cuenta que tratas de eliminar no existe");
			rs.setSuccess(false);
			rs.setObj(cuenta.getNumero());
			return rs;
			
		}catch (Exception e){
			List<String>error =  new ArrayList<>();
			for(StackTraceElement el: e.getStackTrace()) {
				error.add("Clase: " + el.getClassName());
				error.add("Metodo: "+ el.getMethodName());
				error.add("Linea: " +el.getLineNumber());
			}
			rs.setMensaje("error al eliminar, intente de nuevo mas tarde" );
			rs.setSuccess(false);
			rs.setObj(error);
			return rs;
		}
	}
	public ResponseEntity<Cuenta> getCuenta(long  numero){
		Cuenta cuenta = cuentaDao.findById(numero).orElse(null);
		if(cuenta == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(cuenta);
	}
	
	public ResponseEntity<List<Cuenta>> buscarPorCurp(String curp){
		List<Cuenta> cuentas = cuentaDao.findByCurpCliente(curp);
		if(cuentas.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(cuentas);
	}
	
	public ResponseEntity<List<Cuenta>> buscarPorIdBanco(int idBanco){
		List<Cuenta>cuentas = cuentaDao.findByIdBanco(idBanco);
		if(cuentas.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(cuentas);
	}
	
}
	
