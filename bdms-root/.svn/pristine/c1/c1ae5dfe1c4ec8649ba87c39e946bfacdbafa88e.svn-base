package com.bdms.auth.shiro;

import java.util.List;
import java.util.Set;

import com.bdms.auth.service.AccountService;
import com.bdms.auth.service.PermissionService;
import com.bdms.auth.service.RoleService;
import com.bdms.entity.auth.Account;
import com.bdms.entity.auth.Permission;
import com.bdms.entity.auth.Role;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.*;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/* 
 * Description:
 * 		Shiro 账户验证及权限管理
 * 
 * author ： Zhangjc
 * 
 *
 */
public class BdmsRealm extends AuthorizingRealm {

	private static final Logger LOG = LoggerFactory.getLogger(BdmsRealm.class);

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;

	/* 
	 * 授权
	 * (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	 @Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String accountName = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Account account = accountService.findByAccountName(accountName);
		if(account!=null){
			String roleIds=account.getRoleIds();
			//根据帐户中的roleIds字段查找角色列表
			List<Role> roles= roleService.getRolesByRoleIds(roleIds);
			for(int i=0;i<roles.size();i++){
				String permissionIds = roles.get(i).getPermissionIds();
				authorizationInfo.addRole(roles.get(i).getRoleName());
				if(permissionIds!=null){
					//根据角色中的permissionIds查找权限列表
					List<Permission> permissions=permissionService.getPermissionsByPermissionIds(permissionIds);
					for(Permission permission:permissions){
						if(permission.getResource()!=null)
						authorizationInfo.addStringPermission(permission.getResource());
					}
				}  
			}
		}
		/*Set<String> roles = accountService.findRoles(accountName);
		authorizationInfo.setRoles(roles);
		Set<String> permissions = accountService.findRoles(username);
		authorizationInfo.setStringPermissions(permissions);*/
        
		return authorizationInfo;
	} 

	/*
	 * 
	 * 验证方法 (non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org
	 * .apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String accountName=(String) token.getPrincipal();
		String password=new String((char[]) token.getCredentials());
		Account account = accountService.findByAccountName(accountName);
 		 
		 Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()){
			try {
				currentUser.login(token);
				//输出认证对象
				System.out.print(currentUser.getPrincipal());
			} catch (UnknownAccountException uae) {
				LOG.info("没有这个帐户:" + accountName);
			} catch (IncorrectCredentialsException ice) {
				LOG.info("帐户密码不正确");
			} catch (LockedAccountException lae) {
				LOG.info("帐户名不可用");
			} catch (AuthenticationException ae) {
				LOG.info("其他异常");
			}
		}  
		  SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo( account.getAccountName(), account.getPassword(), getName() );
		return authenticationInfo;
	}
	
	
	
	/**
	  * description:权限认证
	  * @param token
	  * @return
	  * AuthenticationInfo
	  * 2016-1-18 下午2:25:57
	  * by Hongs
	 */
	
	public  AuthenticationInfo  PermissionAuthentication(AuthenticationToken token){
		
		String accountName=(String) token.getPrincipal();
		String password=new String((char[]) token.getCredentials());
         //根据帐户名查找帐户
		Account account = accountService.findByAccountName(accountName);
		SimpleAuthenticationInfo authenticationInfo=null;
		if(account!=null && account.getPassword().equals(password)){
			String roleIds=account.getRoleIds();
			//根据帐户中的roleIds字段查找角色列表
			List<Role> roles= roleService.getRolesByRoleIds(roleIds);
			for(int i=0;i<roles.size();i++){
				String permissionIds = roles.get(i).getPermissionIds();
				if(permissionIds!=null){
					//根据角色中的permissionIds查找权限列表
					List<Permission> permissions=permissionService.
							getPermissionsByPermissionIds(permissionIds);
					for(Permission permission:permissions){
						 
						
						 
						//authenticationInfo=new SimpleAuthenticationInfo(permission.getId(),permission.);
			
                        
					}
				}  
			}
		}

		return authenticationInfo;
	}

}
