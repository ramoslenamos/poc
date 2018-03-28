package com.example.demo;

import com.example.demo.Dictionnary.Tables.UserMetier;
import com.example.demo.Dictionnary.Tables.UserMetierImp;
import com.example.demo.Dictionnary.Tables.UserRepository;
import com.example.demo.EudoNet.EudoNetAPI;
import com.example.demo.EudoNet.JsonEntities.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DemoApplication {
@Autowired
private static UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		Optional<UserInfos> userInfos = userRepository.findById(new Long(1));
		System.out.println("LOGIIIIIIIIIIIIIIIIN" + userInfos.get().userLogin);
		try {
			EudoNetAPI api = new EudoNetAPI(userInfos.get());
			// Recherche des étudiants
			System.out.println(api.search(200, buildStudentsSearch()).toString());
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Création d'une recherche d'étudiants.
	 * @return
	 */
	private static CustomSearch buildStudentsSearch() {
		List<Integer> listCols = new ArrayList<>();
		listCols.add(201);
		listCols.add(202);

		Criteria criteria = new Criteria("209", 9, "1982");

		List<WhereCustom> whereCustoms = new ArrayList<>();
		whereCustoms.add(new WhereCustom());

		WhereCustom whereCustom = new WhereCustom(whereCustoms, criteria, 0);

		List<OrderBy> orderBy = new ArrayList<>();
		orderBy.add(new OrderBy(0, 0));

		return new CustomSearch(true, 10, 10, listCols, whereCustom, orderBy);
	}
}
