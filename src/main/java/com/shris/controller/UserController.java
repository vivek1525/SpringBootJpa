package com.shris.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shris.domain.IdContainer;
import com.shris.domain.User;
import com.shris.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	// @RequestMapping(value = "/", method = RequestMethod.POST) or
	@PostMapping("/")
	public String addUser(@RequestBody User user) {
		service.save(user);
		return "added successfully";
	}

	/**
	 * 
	 * @return Users
	 * 
	 *         Request URL : localhost:8080/user/getAllUsers
	 */
	// @RequestMapping("/getAllUsers") or
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		List<User> user = service.getAllUsers();
		return user;
	}
	
	/**
	 * 
	 * @param limit
	 * @return
	 * 
	 *    Request URL : localhost:8080/user/getUsersByLimit?limit=2
	 */
	@GetMapping("/getUsersByLimit")
	public List<User> getUsersWithLimit(@RequestParam("limit") int limit) {
		List<User> users = service.getUsersWithLimit(limit);
		return users;
	}

	/**
	 * 
	 * @param idContainer
	 * @return Users
	 * 
	 *         Request URL : localhost:8080/user/getUsersByIdsUsingReqBody Request
	 *         Body : [ { "id": 101 }, { "id":102 } ]
	 * 
	 */
	@PostMapping("/getUsersByIdsUsingReqBody")
	public List<User> getUsersByIds(@RequestBody List<IdContainer> idContainer) {
		List<User> user = service.getUsersByIdsUsingReqBody(idContainer);
		return user;
	}

	/**
	 * 
	 * @param idMap
	 * @return Users
	 * 
	 *         Request URL : localhost:8080/user/getUsersByIdsUsingReqBodyJson
	 *         Request Body : { "ids":[101,102,103] }
	 */
	@PostMapping("/getUsersByIdsUsingReqBodyJson")
	public List<User> getUsersByIdsUsingReqBodyJson(@RequestBody Map<String, List<Integer>> idMap) {
		List<Integer> idList = new ArrayList<Integer>();
		if (idMap.containsKey("ids")) {
			idList.addAll(idMap.get("ids"));
		}
		List<User> user = service.getUsersByIdsUsingReqParam(idList);
		return user;
	}

	/**
	 * 
	 * @param ids
	 * @return Users
	 * 
	 *         Request URL :
	 *         localhost:8080/user/getUsersByIdsUsingReqParam?ids=101,102,103
	 * 
	 */
	@GetMapping("/getUsersByIdsUsingReqParam")
	public List<User> getUsersByIdsTest(@RequestParam List<Integer> ids) {
		List<User> user = service.getUsersByIdsUsingReqParam(ids);
		return user;
	}

	/**
	 * 
	 * @param ids
	 * @return Users
	 * 
	 *         Request URL :
	 *         localhost:8080/user/getUsersByIdsUsingpathvariable/101,102,103
	 * 
	 */
	@GetMapping("/getUsersByIdsUsingpathvariable/{ids}")
	public List<User> getUsersByIdsUsingpathvariable(@PathVariable List<Integer> ids) {
		List<User> user = service.getUsersByIdsUsingReqParam(ids);
		return user;
	}

	/**
	 * 
	 * @param uid
	 * @return User
	 * 
	 *         Request URL : localhost:8080/user/101
	 */
	// @RequestMapping(value="/{uid}", method = RequestMethod.GET) or
	@GetMapping("/{uid}")
	public User getUser(@PathVariable int uid) {
		User user = service.findById(uid);
		return user;
	}

	/**
	 * 
	 * @param name
	 * @return
	 * 
	 *         Request URL : localhost:8080/user/getUser/vivek
	 */
	@GetMapping("/getUser/{name}")
	public List<User> findByName(@PathVariable String name) {
		List<User> user = service.findByName(name);
		return user;
	}
	
	/**
	 * 
	 * @param name
	 * @param limit
	 * @return
	 *  
	 *     Request URL : localhost:8080/user/getUserWithLimit/vivek?limit=2
	 */
	@GetMapping("/getUserWithLimit/{name}")
	public List<User> findByNameWithLimit(@PathVariable String name, @RequestParam int limit) {
		List<User> user = service.findByNameWithLimit(name,limit);
		return user;
	}

	/**
	 * 
	 * @param uid
	 * @param name
	 * @return
	 * 
	 *         Request URL : localhost:8080/user/101?name=vicky
	 */
	// @RequestMapping(value = "/{uid}", method = RequestMethod.PUT) or
	@PutMapping("/{uid}")
	public String updateName(@PathVariable int uid, @RequestParam String name) {
		service.updateName(uid, name);
		return "updated successfully";
	}

	/**
	 * 
	 * @param uid
	 * @return
	 * 
	 *         Request URL : localhost:8080/user/updateName/101?name=vicky
	 */
	@PutMapping("/updateName/{uid}")
	public String updateNameFromRepo(@PathVariable int uid, @RequestParam String name) {
		service.updateNameFromRepo(uid, name);
		return "updated successfully";
	}

	/**
	 * 
	 * @param uids
	 * @return
	 * 
	 *         Request URL :
	 *         localhost:8080/user/updateMultipleFlags?uids=101,102,103
	 */
	@PutMapping("/updateMultipleFlags")
	public String updateMultipleFlags(@RequestParam List<Integer> uids) {
		service.updateMultipleFlags(uids);
		return "updated successfully";
	}

	/**
	 * 
	 * @param idMap
	 * @return
	 * 
	 *         Request URL : localhost:8080/user/updateMultipleFlagsByReqBody
	 *         Request Body : { "ids":[101,102,103] }
	 */
	@PutMapping("/updateMultipleFlagsByReqBody")
	public List<User> updateMultipleFlagsByReqBody(@RequestBody Map<String, List<Integer>> idMap) {
		List<User> users = new ArrayList<>();
		List<Integer> idList = new ArrayList<>();
		if (idMap.containsKey("ids")) {
			idList.addAll(idMap.get("ids"));
		}
		users = service.updateMultipleFlags(idList);
		return users;
	}

	/**
	 * 
	 * @param uid
	 * @return
	 * 
	 *         Request URL : localhost:8080/user/101
	 */
	// @RequestMapping(value = "/{uid}", method = RequestMethod.DELETE) or
	@DeleteMapping("/{uid}")
	public String deleteUser(@PathVariable int uid) {
		service.delete(uid);
		return "deleted successfully";
	}

	/**
	 * 
	 * @param uid
	 * @return
	 * 
	 *         Request URL : localhost:8080/user/delete/101
	 */
	// @RequestMapping(value = "/{uid}", method = RequestMethod.DELETE) or
	@DeleteMapping("/delete/{uid}")
	public String deleteById(@PathVariable int uid) {
		service.deleteById(uid);
		return "deleted successfully";
	}

	/**
	 * 
	 * @param uid
	 * 
	 *            Request URL : localhost:8080/user/deleteUser/101
	 */
	@DeleteMapping("/deleteUser/{uid}")
	public String deleteByUId(@PathVariable int uid) {
		service.deleteByUId(uid);
		return "deleted successfully";
	}

	/**
	 * 
	 * @param idMap
	 * @return Users
	 * 
	 *         Request URL : localhost:8080/user/getUsersByIdsUsingReqBodyJson
	 *         Request Body : { "ids":[101,102,103] }
	 */
	@PostMapping("/getUsersByIdsUsingReqBodyJson1")
	public Response<String, List<User>> getUsersByIds(@RequestBody Map<String, List<Integer>> idMap) {

		Response<String, List<User>> response = new Response<>();
		List<Integer> idList = new ArrayList<Integer>();
		if (idMap.containsKey("ids")) {
			idList.addAll(idMap.get("ids"));
		}
		List<User> user = service.getUsersByIdsUsingReqParam(idList);
		return response;
	}
	
	@GetMapping("/upload")
	public String upload() {
		service.upload();
		return "uploaded succesfully";
	}

}
