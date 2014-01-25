package me.thomas.demo.activiti.web.controller;

import javax.servlet.http.HttpSession;

import me.thomas.demo.activiti.utils.Validator;
import me.thomas.demo.activiti.web.entity.User;
import me.thomas.demo.activiti.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin(User user, HttpSession session) {
		User actual = (User) session.getAttribute("user");

		if (Validator.isNull(actual)) {
			return "login/login";
		} else {
			// 如果已登录则直接去后台首页
			return "redirect:/index";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User user, Errors errors, HttpSession session) {
		User actual = userService.getUserByName(user.getName());
		if (Validator.isNull(actual)) {
			errors.rejectValue("name", "error.user.not.exists", "用户名不存在");
			return "login/login";
		}
		if (!actual.isActivity()) {
			errors.rejectValue("activity", "error.user.locked", "用户已被锁定");
			return "login/login";
		}
		if (!actual.getPassword().equals(user.getPassword())) {
			errors.rejectValue("password", "error.user.password.wrong", "密码不正确");
			return "login/login";
		}
		session.setAttribute("user", actual);

		return "redirect:/index";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

	@RequestMapping("/index")
	public String index() {
		return "task/index";
	}

}
