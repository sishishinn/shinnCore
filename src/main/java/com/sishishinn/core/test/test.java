package com.sishishinn.core.test;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.sishishinn.core.dao.user.UserDao;
import com.sishishinn.core.entity.user.User;
import com.sishishinn.core.service.user.UserManager;
import com.sishishinn.core.util.EmptyUtil;

public class test {

	public static void main(String[] args) {

		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/spring-applicationContext.xml");
			DataSource dataSouce = (DataSource) ctx.getBean("dataSource");
			System.out.println("数据源："+ dataSouce);
			System.out.println("连接："+ dataSouce.getConnection());

			
			TransactionTemplate transactionTemplate =   (TransactionTemplate) ctx.getBean("transactionTemplate");
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					UserDao userDao = ctx.getBean(UserDao.class);
					List<User> userList =userDao.findByRole_Id("5636408a-c3ad-4dae-a9d3-f99a5d592571");
					for (User user : userList) {
						System.out.println(user.getName()+"role"+user.getRole().getName());
					}
				}
			});
			
			UserManager userManager = ctx.getBean(UserManager.class);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
