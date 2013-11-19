package tianci.pinao.dts.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tianci.pinao.dts.models.User;
import tianci.pinao.dts.services.UserService;

@Controller
public class UsersController {

	@Autowired
	UserService userService;


	// 通过这个到登录页面
	@RequestMapping(value = "/users/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "users/login";
	}

	// 根据用户名 密码查询
	@RequestMapping(value = "/users/index", method = RequestMethod.POST)
	public String creat(Model model, User user, HttpServletRequest request) {
		int res = userService.login(user,request);
		if (res > 0) {
			return findall(model, user, request);
		} else {
			return "users/logout";
		}
	}

	public String findall(Model model, User user, HttpServletRequest request) {
		User a = new User();
		List<User> list = userService.check(a);
		model.addAttribute("UserList", list);
		return "users/index";
	}

	@RequestMapping(value = "/users/new", method = RequestMethod.GET)
	public String new1(Model model) {
		return "users/new";
	}

	@RequestMapping(value = "/users/add", method = RequestMethod.POST)
	public String add1(Model model, User user, HttpServletRequest request) {
		int a = userService.add(user);
		if (a > 0) {
			return findall(model, user, request);
		} else {
			System.out.println(a);
		}
		return null;
	}

	@RequestMapping(value = "/users/del", method = RequestMethod.GET)
	public String delete(Model model, User user, HttpServletRequest request) {
		userService.delete(user);
		return findall(model, user, request);
	}

	// 通过id查询
	@RequestMapping(value = "/users/upda", method = RequestMethod.GET)
	public String update(Model model, User user, HttpServletRequest request) {
		List<User> list = userService.check(user);
		// list。get（0）只能取得一个对象的意思。
		if (list.size() > 0) {
			User user1 = list.get(0);
			model.addAttribute("user1", user1);
		}
		return "/users/update";
	}

	// 修改
	@RequestMapping(value = "/users/update", method = RequestMethod.POST)
	public String updateonly(Model model, User user, HttpServletRequest request) {
		userService.updates(user);
		return findall(model, user, request);
	}

	// 退出
	@RequestMapping(value = "/users/loginout", method = RequestMethod.GET)
	public String loginout() {
		return "/users/loginout";
	}

	// 全部删除
	@RequestMapping(value = "/users/deleteall", method = RequestMethod.GET)
	public String deleteall(Model model, String str, HttpServletRequest request) {
		System.out.println("aaaaaaaaaaaaaa"+str);
		String[] all=str.split(",");
		int fan=0;
		int a=0;
		for (int i = 0; i < all.length; i++) {
			String al = all[i];
			a=Integer.parseInt(al);
			fan=userService.deleteall(a);
		}
		/*if(fan>0){
			List<User> list=userService.findidall(a);
			model.addAttribute("UserList", list);
			return "users/index";
		}
*/
		return "users/index";
	}

}
