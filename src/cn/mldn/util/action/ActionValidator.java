package cn.mldn.util.action;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import cn.mldn.util.ValidatorUtil;

import com.opensymphony.xwork2.ActionInvocation;

public class ActionValidator {
	/**
	 * 实现指定Action的数据验证处理
	 * 
	 * @param actionObject
	 *            触发具体验证的Action程序类
	 * @param invocation
	 *            所有接收的处理的参数
	 * @param rule
	 *            验证规则信息
	 * @return 验证通过返回true，否则返回false
	 */
	public static boolean validate(Object actionObject,
			ActionInvocation invocation, String rule) throws Exception {
		// 为了可以保存具体的错误信息，那么首先一定要取得addFieldError()方法对象
		// public void addFieldError(String fieldName, String errorMessage)
		Method addFieldErrorMethod = actionObject.getClass().getMethod(
				"addFieldError", String.class, String.class);
		Method getTextMethod = actionObject.getClass().getMethod("getText",
				String.class);
		// 为了可以最终判断是否有错误出现，可以找到public Map<String,List<String>>
		// getFieldErrors()方法
		Method getFieldErrorsMethod = actionObject.getClass().getMethod(
				"getFieldErrors");
		// 1、取得所有接收到的参数内容
		Map<String, Object> paramMap = invocation.getInvocationContext()
				.getParameters();
		// 2、将验证规则打开，按照“|”和“:”拆分取出具体的验证参数名称以及验证规则的类型
		// 考虑到日后有可能出现的数组接收问题，假设如果是数组，名称的最后使用“[]”来结尾
		String result[] = rule.split("\\|"); // 按照竖线拆分内容
		// 3、取出每一组的参数以及规则信息
		for (int x = 0; x < result.length; x++) { // 有了具体的规则
			String temp[] = result[x].split(":");
			String paramValue = null; // 参数的内容
			String paramValues[] = null; // 参数的内容

			if (temp[0].endsWith("[]")) { // 数组处理
				try {
					paramValues = (String[]) paramMap.get(temp[0]);
				} catch (Exception e) {
				}
				switch (temp[1]) {
				case "string": {
					if (!ValidatorUtil.isStringArray(paramValues)) {
						String msg = (String) getTextMethod.invoke(
								actionObject, "validate.string.error");
						addFieldErrorMethod.invoke(actionObject, temp[0], msg);
					}
					break;
				}
				case "int": {
					if (!ValidatorUtil.isIntArray(paramValues)) {
						String msg = (String) getTextMethod.invoke(
								actionObject, "validate.int.error");
						addFieldErrorMethod.invoke(actionObject, temp[0], msg);
					}
					break;
				}
				case "double": {
					if (!ValidatorUtil.isDoubleArray(paramValues)) {
						String msg = (String) getTextMethod.invoke(
								actionObject, "validate.double.error");
						addFieldErrorMethod.invoke(actionObject, temp[0], msg);
					}
					break;
				}
				case "date": {
					if (!ValidatorUtil.isDateArray(paramValues)) {
						String msg = (String) getTextMethod.invoke(
								actionObject, "validate.date.error");
						addFieldErrorMethod.invoke(actionObject, temp[0], msg);
					}
					break;
				}
				case "datetime": {
					if (!ValidatorUtil.isDatetimeArray(paramValues)) {
						String msg = (String) getTextMethod.invoke(
								actionObject, "validate.datetime.error");
						addFieldErrorMethod.invoke(actionObject, temp[0], msg);
					}
					break;
				}
				}
			} else { // 普通参数
				try {
					paramValue = ((String[]) paramMap.get(temp[0]))[0];
				} catch (Exception e) {
				}
				// System.out.println(temp[0] + " = " + paramValue);
				switch (temp[1]) {
				case "rand": {
					String rand = (String) ServletActionContext.getRequest().getSession().getAttribute("rand") ;
					try {
						if (!rand.equalsIgnoreCase(paramValue)) { // 表示验证出错
							String msg = (String) getTextMethod.invoke(
									actionObject, "validate.rand.error");
							addFieldErrorMethod.invoke(actionObject, temp[0],
									msg);
						}
					} catch (Exception e) {
						String msg = (String) getTextMethod.invoke(
								actionObject, "validate.rand.error");
						addFieldErrorMethod.invoke(actionObject, temp[0], msg);
					}
					break;
				}
				case "string": {
					if (!ValidatorUtil.isString(paramValue)) {
						String msg = (String) getTextMethod.invoke(
								actionObject, "validate.string.error");
						addFieldErrorMethod.invoke(actionObject, temp[0], msg);
					}
					break;
				}
				case "int": {
					if (!ValidatorUtil.isInt(paramValue)) {
						String msg = (String) getTextMethod.invoke(
								actionObject, "validate.int.error");
						addFieldErrorMethod.invoke(actionObject, temp[0], msg);
					}
					break;
				}
				case "double": {
					if (!ValidatorUtil.isDouble(paramValue)) {
						String msg = (String) getTextMethod.invoke(
								actionObject, "validate.double.error");
						addFieldErrorMethod.invoke(actionObject, temp[0], msg);
					}
					break;
				}
				case "date": {
					if (!ValidatorUtil.isDate(paramValue)) {
						String msg = (String) getTextMethod.invoke(
								actionObject, "validate.date.error");
						addFieldErrorMethod.invoke(actionObject, temp[0], msg);
					}
					break;
				}
				case "datetime": {
					if (!ValidatorUtil.isDatetime(paramValue)) {
						String msg = (String) getTextMethod.invoke(
								actionObject, "validate.datetime.error");
						addFieldErrorMethod.invoke(actionObject, temp[0], msg);
					}
					break;
				}
				}
			}
		}
		Map<String, List<String>> map = (Map<String, List<String>>) getFieldErrorsMethod
				.invoke(actionObject);
//		System.out.println(map);
		return map.size() == 0;
	}
}
