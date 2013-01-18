package uk.co.mindbadger.footballresultsanalyser.dao;

import java.util.List;
import java.util.Set;

import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Season;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivision;
import uk.co.mindbadger.footballresultsanalyser.domain.SeasonDivisionTeam;

public interface FootballResultsAnalyserDAO {
    public List<Season> getSeasons ();
    
    public Set<SeasonDivision> getDivisionsForSeason (int seasonNumber);

    public Set<SeasonDivisionTeam> getTeamsForDivisionInSeason(int seasonNumber, int divisionId);

    public List<Fixture> getFixturesForTeamInDivisionInSeason(int seasonNumber, int divisionId, int teamId);
    
    public void startSession ();
    
    public void closeSession ();
}
