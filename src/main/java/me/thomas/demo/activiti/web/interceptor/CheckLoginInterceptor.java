package me.thomas.demo.activiti.web.interceptor;

import me.thomas.demo.activiti.utils.Validator;
import me.thomas.demo.activiti.web.entity.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class CheckLoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String[] noFilterUrls = new String[] { "login", "logout" };
		String uri = request.getRequestURI();
		for (String noFilterUrl : noFilterUrls) {
			if (uri.endsWith(noFilterUrl)) {
				return true;
			}
		}

		User user = (User) request.getSession().getAttribute("user");
		if (Validator.isNull(user)) {
			PrintWriter out = response.getWriter();
			StringBuilder script = new StringBuilder();
			script.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
			script.append("window.top.location.href=\"");
			script.append(request.getContextPath() + "/admin/login");
			script.append("\"</script>");
			out.print(script.toString());
			out.close();
			return false;
		}

		return super.preHandle(request, response, handler);
	}

}
