package uk.nhs.nhsbsa.hec.workflow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class TestConfiguration {
	@Bean
	public JpaVendorAdapter jpaVendorAdapter(){
	    HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
	    jpaVendorAdapter.setGenerateDdl(true);
	    jpaVendorAdapter.setShowSql(true);

	    return jpaVendorAdapter;
	}
}
