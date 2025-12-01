package com.mx.Cuentas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mx.Cuentas.dto.Respuesta;
import com.mx.Cuentas.entidad.Cuenta;
import com.mx.Cuentas.service.CuentaService;

import jakarta.ws.rs.POST;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("cuentas")
@CrossOrigin
public class CuentaController {
	@Autowired
	CuentaService service;
	
	@GetMapping("listar")
	public ResponseEntity<List<Cuenta>>listar(){
		return service.getCuentasAll();
	}
	
	@PostMapping("guardar")
	public Respuesta guardar(@RequestBody Cuenta cuenta) {
		return service.guardar(cuenta);
	}
	
	@PostMapping("editar")
	public Respuesta editar(@RequestBody Cuenta cuenta) {
		return service.editar(cuenta);
	}
	
	@PostMapping("eliminar")
	public Respuesta eliminar(@RequestBody Cuenta cuenta) {
		return service.eliminar(cuenta);
	}
	
	@GetMapping("buscarPorCurp/{curp}")
	public ResponseEntity<List<Cuenta>> buscarPorCurp(@PathVariable("curp")String curp){
		return service.buscarPorCurp(curp);
	}
	
	@GetMapping("buscarPorIdBanco/{id}")
	public ResponseEntity<List<Cuenta>>buscarPorIdBanco(@PathVariable("id")int id){
		return service.buscarPorIdBanco(id);
	}
	
	public String getMethodName(@RequestParam String param) {
		return new String();
	}
	
}
