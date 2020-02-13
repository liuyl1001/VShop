package cn.mldn.util.interceptor;

import cn.mldn.util.action.ActionValidator;
import cn.mldn.util.action.GetResourceRules;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class ValidatorInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Object actionObject = invocation.getAction() ;	// 取得当前执行的Action对象
		String rules = GetResourceRules.getRules(actionObject) ;
		if (rules == null) {	// 不需要进行任何的验证，直接放行
			return invocation.invoke() ;
		}// 下面需要执行具体的验证处理
//		System.out.println("*** 【验证规则】" + rules);
		// 取得全部的接收参数
		if (ActionValidator.validate(actionObject, invocation, rules)) {
			return invocation.invoke() ;
		} else {	// 跳转到错误页
			return GetResourceRules.getMethodName() + ".errorPage" ;
		}
	}
}
