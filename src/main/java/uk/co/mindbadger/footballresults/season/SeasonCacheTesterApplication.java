package uk.co.mindbadger.footballresults.season;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import uk.co.mindbadger.footballresultsanalyser.domain.Season;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonImpl;

public class SeasonCacheTesterApplication {

	/*
	 * 
	 * <bean id="seasonCacheFixtureAndTableLoader" class=
	 * "uk.co.mindbadger.footballresults.season.SeasonCacheFixtureAndTableLoader"
	 * > <property name="tableFactory" ref="tableFactory"/> <property
	 * name="tableRowFactory" ref="tableRowFactory"/> <property
	 * name="teamFixtureContextFactory" ref="teamFixtureContextFactory"/>
	 * </bean>
	 * 
	 * <bean id="seasonCacheDivisionLoader"
	 * class="uk.co.mindbadger.footballresults.season.SeasonCacheDivisionLoader"
	 * > <property name="tableFactory" ref="tableFactory"/> <property name="dao"
	 * ref="dao"/> <property name="seasonCacheFixtureAndTableLoader"
	 * ref="seasonCacheFixtureAndTableLoader"/> </bean>
	 * 
	 * <bean id="seasonCacheLoader"
	 * class="uk.co.mindbadger.footballresults.season.SeasonCacheLoader">
	 * <property name="dao" ref="dao"/> <property name="analyserCache"
	 * ref="analyserCache"/> <property name="seasonCacheDivisionLoader"
	 * ref="seasonCacheDivisionLoader"/> </bean>
	 */

	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext(
				".\\src\\main\\webapp\\WEB-INF\\dispatcher-servlet.xml");

		Season season = new SeasonImpl();
		season.setSeasonNumber(2015);

		SeasonCacheLoader loader = (SeasonCacheLoader) context.getBean("seasonCacheLoader");
		loader.loadSeason(season);

		((ConfigurableApplicationContext) context).close();
	}
}
