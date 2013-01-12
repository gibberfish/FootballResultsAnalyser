package mjf.springtestweb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplicationStart {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-config.xml"});
		
		TestBean bean = context.getBean("testBean", TestBean.class);
		
		System.out.println(bean.getMessage());
	}

}
