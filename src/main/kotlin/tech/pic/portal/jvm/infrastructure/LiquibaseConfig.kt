package tech.pic.portal.jvm.infrastructure

import liquibase.integration.spring.SpringLiquibase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class LiquibaseConfig {

    @Bean
    fun liquibase(dataSource: DataSource): SpringLiquibase {
        val liquibase = SpringLiquibase()
        liquibase.setChangeLog("db/changelog-master.xml")
        liquibase.setDataSource(dataSource)
        return liquibase
    }
}
