package cn.mldn.util.action;

import java.lang.reflect.Method;

import org.apache.struts2.ServletActionContext;

/**
 * 得到具体key的验证规则信息，这个类是作为Struts 2.x支持类存在的
 * 
 * @author mldn
 */
public class GetResourceRules {
	/**
	 * 根据指定的对象，以及调用的业务方法取得指定验证规则的信息
	 * 
	 * @param actionObject
	 *            Action类的对象
	 * @return 指定Action调用业务的处理的规则结构
	 * @throws Exception
	 */
	public static String getRules(Object actionObject) throws Exception {
		// 取出业务的处理方法名字，例如：Message!add.action，取出的就是add
		String boMethodName = getMethodName() ;
		// 拼凑出验证的规则的key的名字信息
		String rulesKey = actionObject.getClass().getSimpleName() + "."
				+ boMethodName + ".rules";
		// 有了key的名字之后，还需要取得具体的验证的信息内容，所有的Action都有getText()方法，这样可以避免自己编写资源文件的重复处理操作
		Method textMethod = actionObject.getClass().getMethod("getText",
				String.class, String.class);
		String rulesValue = (String) textMethod.invoke(actionObject, rulesKey,
				null);
		return rulesValue;
	}

	public static String getMethodName() {
		String uri = ServletActionContext.getRequest().getRequestURI();
		if (uri != null) {
			String boMethodName = uri.substring(uri.lastIndexOf("!") + 1,
					uri.lastIndexOf("."));
			return boMethodName;
		}
		return null;
	}
}
