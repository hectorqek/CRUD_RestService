package com.example.springmvc;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.example.springmvc.model.User;

public class SpringRestTestClient {

	public static final String REST_SERVICE_URI = "http://localhost:8080/DataCreditoExperianSpringCRUDRestService";
	
	/* GET */
	@SuppressWarnings("unchecked")
	private static void listAllUsers(){
		System.out.println("----------- Prueba ListarUsuarios API -----------");
		
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/user/", List.class);
		
		if(usersMap!=null){
			for(LinkedHashMap<String, Object> map : usersMap){
	            System.out.println("User : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("age")+", Salary="+map.get("salary"));;
	        }
		}else{
			System.out.println("---------- Usuario no existe ----------");
		}
	}
	
	/* GET */
	private static void getUser(){
		System.out.println("---------- Prueba getUser API----------");
		RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(REST_SERVICE_URI+"/user/1", User.class);
        System.out.println(user);
	}
	
	/* POST */
    private static void createUser() {
		System.out.println("---------- Prueba crear Usuario API----------");
    	RestTemplate restTemplate = new RestTemplate();
        User user = new User(0,"Diego Parra",31,1200000);
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/user/", user, User.class);
        System.out.println("Posición : "+uri.toASCIIString());
    }

    /* PUT */
    private static void updateUser() {
		System.out.println("---------- Prueba actualizar Usuario API ----------");
        RestTemplate restTemplate = new RestTemplate();
        User user  = new User(1,"John lopez",30, 7500000);
        restTemplate.put(REST_SERVICE_URI+"/user/1", user);
        System.out.println(user);
    }

    /* DELETE */
    private static void deleteUser() {
		System.out.println("---------- Prueba eliminar Usuario API ----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/user/3");
    }


    /* DELETE */
    private static void deleteAllUsers() {
		System.out.println("---------- Prueba eliminar todos usuarios API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/user/");
    }

    public static void main(String args[]){
		listAllUsers();
		getUser();
		createUser();
		listAllUsers();
		updateUser();
		listAllUsers();
		deleteUser();
		listAllUsers();
		deleteAllUsers();
		listAllUsers();
    }
}