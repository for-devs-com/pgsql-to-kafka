package com.fordevs.springbatchpostgresqltokafka.config;

import org.springframework.context.annotation.Configuration;
/**
 * Configuración de la base de datos.
 * @author Enoc.Velza | for-devs.com
 * @version 1.0
 */
@Configuration
public class DatabaseConfig {
	/**
	 * Configura el DataSource para la base de datos PostgreSQL.
	 *
	 * @return Un DataSource configurado para PostgreSQL.
	 */
	/*@Bean
	@ConfigurationProperties(prefix = "spring.postgresdatasource")
	public DataSource postgresdatasource() {
		return DataSourceBuilder.create().build();
	}*/

	/**
	 * Configura el EntityManagerFactory para la base de datos PostgreSQL.
	 *
	 * @return Un EntityManagerFactory configurado para PostgreSQL.
	 */
	/*@Bean
	public EntityManagerFactory postgresqlEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lem = 
				new LocalContainerEntityManagerFactoryBean();
		
		lem.setDataSource(postgresdatasource());
		lem.setPackagesToScan("com.fordevs.springbatchpostgresqltokafka.entity.postgresql");
		lem.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		lem.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		lem.afterPropertiesSet();
		
		return lem.getObject();
	}*/
}
