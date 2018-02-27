package com.bdms.web.auth.loginPage.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdms.auth.service.PermissionService;
import com.bdms.auth.shiro.BdmsRealm;



@Controller
public class LoginPageController {
	@Autowired
	private BdmsRealm bdmsRealm;
	/**
	 * 跳转到loginPage页面
	 * @return
	 */
	@RequestMapping(value = "/login/loginPage", method = { RequestMethod.GET })
	public String login() {
		return "auth/login/loginPage";
	}
	
	/**
	  * description:帐户Account认证
	  * @param account
	  * @return
	  * boolean
	  * 2015-12-1 上午10:38:46
	  * by Hongs
	 */
	@RequestMapping(value = "/login/AccountAuthentication", method = { RequestMethod.POST })
	public String login(String username,String password,Model model){
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(username,password);
		String emsg=null;
		try{
			subject.login(token);
		}catch(AuthenticationException e){
			emsg=e.getMessage();
		}
		model.addAttribute("emsg",emsg);
			return "auth/login/loginPage";
	}
	
	/**
	  * description:权限管理
	  * @param account
	  * @return
	  * AuthenticationInfo
	  * 2015-12-1 上午10:43:18
	  * by Lixc
	 */
	@RequestMapping(value = "/login/PermissionAuthentication/{accountname}/{password}", method = { RequestMethod.GET })
	public @ResponseBody
	AuthenticationInfo PermissionAuthentication(@PathVariable(value = "accountname") String accountname,@PathVariable(value = "password") String password) {
		UsernamePasswordToken token=new UsernamePasswordToken(accountname,password);
		Subject subject=SecurityUtils.getSubject();
		subject.login(token);
		return bdmsRealm.PermissionAuthentication(token);
	}
}
