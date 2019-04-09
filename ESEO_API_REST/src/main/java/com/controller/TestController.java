package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blo.Ville;
import com.dao.ListeVilleDAO;

@RestController
public class TestController {
	@RequestMapping(value="/get", method=RequestMethod.GET)
	@ResponseBody
	public Ville[] get(@RequestParam(required = false, value="filter") String filter, @RequestParam(required = false, value="value") String value) {
		System.out.println("Appel GET");
		System.out.println("value : "+value);
		
		Ville[] get=null;
		if(value==null && filter ==null) {
			ListeVilleDAO villes = new ListeVilleDAO();
			get=villes.listeVilles();
		}else if(value!=null && filter!=null) {
			int filterint =Integer.parseInt(filter);
			ListeVilleDAO villes = new ListeVilleDAO();
			get=villes.listeVillesAvecFiltre(filterint, value);
		}else {
			get=null;
		}
		return get;
	}
	
//String 
//	@RequestMapping(value="/get", method=RequestMethod.GET)
//	@ResponseBody
//	public String get(@RequestParam(required = false, value="filter") String filter, @RequestParam(required = false, value="value") String value) {
//		System.out.println("Appel GET");
//		System.out.println("value : "+value);
//		String type;
//		String result="";
//		if(value==null && filter ==null) {
//			type= "Sans filtre : ";
//			ListeVilleDAO villes = new ListeVilleDAO();
//			Ville[] touteVilles=villes.listeVilles();
//			for(int x=0; x<touteVilles.length;x++) {
//				result+=touteVilles[x].toString()+"<br>";
//			}
//		}else if(value!=null && filter!=null){
//			type= "Avec filtre : ";
//			int filterint =Integer.parseInt(filter);
//			ListeVilleDAO villes = new ListeVilleDAO();
//			Ville[] villesFiltre=villes.listeVillesAvecFiltre(filterint, value);
//			for(int x=0; x<villesFiltre.length;x++) {
//				result+=villesFiltre[x].toString()+"<br>";
//			}
//		}else {
//			type= "Dummy ! :";
//			result="No result";
//		}
//		return type+value+filter+result;
//	}
	
	@RequestMapping(value="/post", method=RequestMethod.POST)
	@ResponseBody
	public boolean post(@RequestParam(required = true, value="codeCommuneInsee") String codeCommuneInsee, 
			@RequestParam(required = true, value="nomCommune") String nomCommune,
			@RequestParam(required = true, value="codePostal") String codePostal,
			@RequestParam(required = true, value="libelleAcheminement") String libelleAcheminement, 
			@RequestParam(required = true, value="Ligne_5") String ligne5,
			@RequestParam(required = true, value="latitude") String latitude, 
			@RequestParam(required = true, value="longitude") String longitude) {
		System.out.println("Appel POST");
		ListeVilleDAO villes = new ListeVilleDAO();
		Ville ville = new Ville();
		ville.setCodeCommuneINSEE(codeCommuneInsee);
		ville.setCodePostal(codePostal);
		ville.setLatitude(latitude);
		ville.setLibelleAcheminement(libelleAcheminement);
		ville.setLigne5(ligne5);
		ville.setLongitude(longitude);
		ville.setNomCommune(nomCommune);
		boolean result=villes.creationVille(ville);
		return result;
	}
	
	@RequestMapping(value="/put", method=RequestMethod.PUT)
	@ResponseBody
	public boolean put(@RequestParam(required = true, value="codeCommuneInsee") String codeCommuneInsee, 
			@RequestParam(required = true, value="filter") String filter, 
			@RequestParam(required = true, value="newValue") String newValue) {
		System.out.println("Appel PUT");
		ListeVilleDAO villes = new ListeVilleDAO();
		int filterint =Integer.parseInt(filter);
		boolean put=villes.modifier(codeCommuneInsee, filterint, newValue);
		return put;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	@ResponseBody
	public boolean delete(@RequestParam(required = true, value="codeCommuneInsee") String codeCommuneInsee) {
		ListeVilleDAO villes = new ListeVilleDAO();
		boolean delete=villes.suppressionVille(codeCommuneInsee);
		System.out.println("Appel DELETE");
		return delete;
	}
}
