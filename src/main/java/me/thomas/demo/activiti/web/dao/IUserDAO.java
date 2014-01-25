package me.thomas.demo.activiti.web.dao;

import me.thomas.demo.activiti.web.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserDAO extends CrudRepository<User, Long> {

	List<User> findByName(String name);
}
