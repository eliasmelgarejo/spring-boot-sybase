package com.censer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.censer.domain.OTResumen;
import com.censer.domain.OrdenDTO;
import com.censer.repository.StatusTallerRepository;

@Service
@Transactional
public class StatusTallerService {
	
	@Autowired
	private StatusTallerRepository repo;
		
	
	public int getDiasHabiles(Date inicio, Date fin) {
		return repo.getDiasHabiles(inicio, fin);
	}
	
	@SuppressWarnings("deprecation")
	public int getDiasHabilesEnero() {
		Date inicio = new Date(2021,1,1);
		Date fin = new Date(2021, 1, 31);
		return repo.getDiasHabiles(inicio, fin);
	}
	
	public List<OrdenDTO> findAllSinSalida() {
		ArrayList<Object> lista = repo.getOrdenesAbiertasCasaCentral();
		List<OrdenDTO> listaDTO = new ArrayList<OrdenDTO>();

		for (int i = 0; i < lista.toArray().length; i++) {
			Object[] element = (Object[]) lista.toArray()[i];
			
			OrdenDTO dto = new OrdenDTO();
			dto.setNroorden(element[0].toString());
			dto.setAsesor(element[1].toString());
			dto.setApertura(element[2].toString());
			//dto.setSalida(element[3].toString());
			dto.setDiasentaller(Integer.parseInt(element[4].toString()));
			dto.setMocargadas(Integer.parseInt(element[5].toString()));
			dto.setContrabajos(Integer.parseInt(element[6].toString()));
			dto.setTerminados(Integer.parseInt(element[7].toString()));
			dto.setEstado(element[8].toString());
			dto.setMarcmodever(element[9].toString());
			dto.setChasis(element[10].toString());
			dto.setCliente(element[11].toString());
			if(element[12]==null) {
				dto.setTiposervicio("MECÁNICA");
			}else {
				dto.setTiposervicio(element[12].toString());
			}
			if(element[13]==null) {
				dto.setSeccion("MEC");
			}else {
				dto.setSeccion(element[13].toString());
			}
			if(element[14]==null) {
				dto.setFechaterminado("");
			}
			else{
				dto.setFechaterminado(element[14].toString());
			}
			if(element[15]==null) {
				dto.setHorasterminado("0");
			}
			else{
				dto.setHorasterminado(element[15].toString());
			}
			dto.setSucursal(element[16].toString());
			dto.setMarca(element[17].toString());
			dto.setSituacion(element[18].toString());
			if(element[19]==null) {
				dto.setChapa("");
			}
			else{
				dto.setChapa(element[19].toString());
			}
			if(element[20]==null) {
				dto.setPrioridad("NO");
			}
			else{
				if(element[20].toString().toUpperCase().equals("S")) {
					dto.setPrioridad("SI");
				}else {
					dto.setPrioridad("NO");
				}
			}
			
			listaDTO.add(dto);
		}
		
		return listaDTO;
	}
	
	public List<OTResumen> getResCurrentWeek(){
		ArrayList<Object> lista = repo.getOTResEntSal();
		List<OTResumen> listaDTO = new ArrayList<OTResumen>();
		
		for (int i = 0; i < lista.toArray().length; i++) {
			Object[] element = (Object[]) lista.toArray()[i];			
			
			OTResumen dto = new OTResumen();
			dto.setDay(Integer.parseInt(element[0].toString()));
			dto.setType(element[1].toString());
			dto.setDay_week_name(element[2].toString());
			dto.setCount(element[3].toString());
			
			listaDTO.add(dto);
		}
		
		return listaDTO;
	}

	public boolean isLogin(String username, String password) {
		try {
			int result = repo.getLogin(username, password);
			System.out.println("isLogin return...");
			System.out.println(result);
			if(result>0) return true;			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error en LoginService!!!");
			System.out.println(e.toString());
		}
		return false;
	}
}
