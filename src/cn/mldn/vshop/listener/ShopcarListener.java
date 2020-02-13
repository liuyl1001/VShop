package cn.mldn.vshop.listener;

import java.util.HashMap;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
@WebListener
public class ShopcarListener implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		event.getSession().setAttribute("allGoods", new HashMap<Integer,Integer>());
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
	}

}
