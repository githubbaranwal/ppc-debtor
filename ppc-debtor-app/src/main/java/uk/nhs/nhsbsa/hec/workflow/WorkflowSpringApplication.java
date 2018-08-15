package uk.nhs.nhsbsa.hec.workflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

//NOSONAR - can't make constructor private
@SpringBootApplication
public class WorkflowSpringApplication extends SpringBootServletInitializer 
{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder)
	{
		return springApplicationBuilder.sources(WorkflowSpringApplication.class); 
	}
	
	public static void main(String[] args) 
	{
		SpringApplication.run(WorkflowSpringApplication.class, args); // NOSONAR - can't close app context
    }
}
